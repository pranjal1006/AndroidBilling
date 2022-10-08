package com.billingApp.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.billingApp.database.DatabaseOperations;
import com.example.ashirvadbilling.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CameraOperations {

    private InputImage image;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private final int REQUEST_CODE_CAMERA_PERMISSION = 1001;
    private boolean isCameraOpActive = false;
    private boolean isTextExtractionActive = false;
    private Runnable textExtraction;
    private Handler handler;
    private boolean flashMode = false;
    private AtomicBoolean isProcessActive = new AtomicBoolean();

    public void startTextExtraction(Context context, PreviewView previewView, DatabaseOperations dbOps) {
        handler = new Handler();
        isTextExtractionActive = true;
        textExtraction = new Runnable() {
            @Override
            public void run() {
                if(!isProcessActive.get() && previewView.getBitmap() != null) {
                    isProcessActive.set(true);
                    Bitmap bitmap = previewView.getBitmap();
                    image = InputImage.fromBitmap(bitmap, previewView.getDisplay().getRotation());
                    processText(dbOps);
                }
                handler.post(this);
            }
        };

        handler.post(textExtraction);
    }

    public void stopTextExtraction() {
        if(isTextExtractionActive) {
            handler.removeCallbacks(textExtraction);
            isTextExtractionActive = false;
        }
    }

    public boolean checkPermissions(Context context, Activity activity){

        if(ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.CAMERA"}, REQUEST_CODE_CAMERA_PERMISSION);
        else
            return true;
        return false;

    }

    public void startCamera(Context context, PreviewView previewView, FloatingActionButton flashButton) {

        if(!isCameraOpActive) {
            isCameraOpActive = true;

            cameraProviderFuture = ProcessCameraProvider.getInstance(context);

            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider, previewView, context, flashButton);
                } catch (ExecutionException | InterruptedException e) {
                }
            }, ContextCompat.getMainExecutor(context));
        }

    }

    public void stopCamera() {
        try {
            if(isCameraOpActive) {
                cameraProviderFuture.get().unbindAll();
                isCameraOpActive = false;
            }
        }catch (Exception e) {
            //TODO
        }
    }

    public void processText(DatabaseOperations dbOps){
        TextRecognizer recognizer = TextRecognition.getClient();
        Task<Text> result =
                recognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onSuccess(Text visionText) {
                                boolean flag = false;
                                String ext = visionText.getText();
                                Log.i("Extracted text-",ext);
                                String regex2 = "(?<!\\d)\\d{8}(?!\\d)";
                                Pattern pattern = Pattern.compile(regex2);
                                Matcher matcher = pattern.matcher(ext);
                                while(matcher.find()) {
                                    String key = matcher.group();
                                    String res = dbOps.findItemByPartNo(Long.parseLong(key));
                                    if(res!=null) {
                                        Log.i("Item found-", res);
                                        flag = true;
                                        break;
                                    }
                                }
                                if(!flag) {
                                    regex2 = "(?<!\\d)\\d{7}(?!\\d)";
                                    pattern = Pattern.compile(regex2);
                                    matcher = pattern.matcher(ext);
                                    while (matcher.find()) {
                                        String key = matcher.group();
                                        String res = dbOps.findItemByItemCode(Long.parseLong(key));
                                        if (res != null) {
                                            Log.i("Item found-", res);
                                            break;
                                        }
                                    }
                                }
                                isProcessActive.set(false);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        isProcessActive.set(false);
                                    }
                                });

    }

    public void bindPreview(@NonNull ProcessCameraProvider cameraProvider, PreviewView previewView, Context context, FloatingActionButton flashButton) {

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)context, cameraSelector, preview);
        if(camera.getCameraInfo().hasFlashUnit()) {
            flashButton.setOnClickListener(v -> {
                flashMode = !flashMode;
                camera.getCameraControl().enableTorch(flashMode);
                if(flashMode)
                    flashButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_flash_off_24));
                else
                    flashButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_flash_on_24));
            });
        }
        else
            flashButton.setVisibility(View.INVISIBLE);
    }
}