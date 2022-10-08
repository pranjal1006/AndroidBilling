package com.billingApp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billingApp.util.AdapterIconWithName;
import com.billingApp.util.AdapterOnlyName;
import com.example.ashirvadbilling.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewBillCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewBillCategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> titles;
    List<Integer> images;

    RecyclerView categoryList;
    AdapterOnlyName adapter;

    public NewBillCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewBillCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewBillCategoryFragment newInstance(String param1, String param2) {
        NewBillCategoryFragment fragment = new NewBillCategoryFragment();
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
        return inflater.inflate(R.layout.fragment_new_bill_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryList = view.findViewById(R.id.categoryList);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("First");
        titles.add("Second");
        titles.add("Third");
        titles.add("Second");
        titles.add("Third");
        titles.add("Second");
        titles.add("Third");

        adapter = new AdapterOnlyName(titles, getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);

        categoryList.setLayoutManager(gridLayoutManager);
        categoryList.setAdapter(adapter);

    }
}