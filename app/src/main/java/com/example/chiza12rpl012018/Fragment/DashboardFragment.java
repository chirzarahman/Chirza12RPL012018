package com.example.chiza12rpl012018.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.chiza12rpl012018.Adapter.DashboardAdapter;
import com.example.chiza12rpl012018.LoginActivity;
import com.example.chiza12rpl012018.Model.DashboardModel;
import com.example.chiza12rpl012018.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<DashboardModel> models;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = (View) inflater.inflate(R.layout.fragment_dashboard, container, false);

        Window window = getActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getActivity().getResources().getColor(R.color.white));

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        DashboardAdapter dashboardAdapter = new DashboardAdapter(getContext(), models);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(dashboardAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        models = new ArrayList<>();
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
        models.add(new DashboardModel(R.drawable.logo, "Polygon", "Standart"));
    }
//    public static DashboardFragment newInstance(String param1, String param2) {
//        DashboardFragment fragment = new DashboardFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dashboard, container, false);
//    }
}