package com.billingApp.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.billingApp.util.AdapterListItem;
import com.billingApp.util.BillItem;
import com.example.ashirvadbilling.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewBillFragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewBillFragmentMain extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<BillItem> items;

    RecyclerView itemList;
    RecyclerView taxesList;
    TextView totalQty;
    TextView totalAmt;
    AdapterListItem adapterListItem;
    FloatingActionButton addItem;
    FloatingActionButton scanCode;

    public NewBillFragmentMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewBillFragmentMain.
     */
    // TODO: Rename and change types and number of parameters
    public static NewBillFragmentMain newInstance(String param1, String param2) {
        NewBillFragmentMain fragment = new NewBillFragmentMain();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_bill_main, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        addItem = view.findViewById(R.id.addItem);
        scanCode = view.findViewById(R.id.scanCode);
        itemList = view.findViewById(R.id.itemList);
        taxesList = view.findViewById(R.id.taxes);
        totalQty = view.findViewById(R.id.quantity);
        totalAmt = view.findViewById(R.id.amount);

        addItem.setOnClickListener(v -> navController.navigate(R.id.action_newBillFragmentMain_to_newBillCategoryFragment));
        scanCode.setOnClickListener(v -> navController.navigate(R.id.action_newBillFragmentMain_to_scanFragment));

        items = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            IntStream.rangeClosed(1, 60).forEach(i -> {
                items.add(new BillItem(i, "th element long abcd long abcd long abcd long abcd", i*13+7%17, 11.5*i));
            });
        }
        int count = 0;
        double amount = 0.0;

        for(int i=1;i<items.size();i++) {
            BillItem item = items.get(i);
            count+=item.getQuantity();
            amount+=item.getAmount();
        }

        totalQty.setText(count+"");
        totalAmt.setText(String.format("%.2f", amount));

        adapterListItem = new AdapterListItem(items, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        itemList.setLayoutManager(linearLayoutManager);
        itemList.setAdapter(adapterListItem);

    }
}
