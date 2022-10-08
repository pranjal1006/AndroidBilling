package com.billingApp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.billingApp.database.DatabaseOperations;
import com.billingApp.main.MainActivity;
import com.billingApp.util.CameraOperations;
import com.example.ashirvadbilling.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton flashToggle;
    Button next;
    PreviewView previewView;
    RecyclerView items;
    CameraOperations cameraOperations = new CameraOperations();
    DatabaseOperations databaseOperations;

    MainActivity mainActivity;

    public ScanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScanFragment newInstance(String param1, String param2) {
        ScanFragment fragment = new ScanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mainActivity = (MainActivity) getActivity();
        databaseOperations = mainActivity.getDatabaseOperations();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        flashToggle = view.findViewById(R.id.flashToggle);
        next = view.findViewById(R.id.next);
        previewView = view.findViewById(R.id.previewView);
        items = view.findViewById(R.id.items);



        next.setOnClickListener(v -> navController.navigate(R.id.action_scanFragment_to_checkOutFragment));


        //TODO if permission is not granted, preview does not start
        if(cameraOperations.checkPermissions(getContext(), getActivity())) {
            previewView.setVisibility(View.VISIBLE);
            cameraOperations.startCamera(getContext(), previewView, flashToggle);
            cameraOperations.startTextExtraction(getContext(), previewView, databaseOperations);
        }

        flashToggle.setOnClickListener(v -> navController.navigate(R.id.action_newBillFragmentMain_to_newBillCategoryFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cameraOperations.stopTextExtraction();
        cameraOperations.stopCamera();
    }
}