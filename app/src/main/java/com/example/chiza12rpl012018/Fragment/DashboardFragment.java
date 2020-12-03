package com.example.chiza12rpl012018.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.chiza12rpl012018.Adapter.CustomersAdapter;
import com.example.chiza12rpl012018.Adapter.DashboardAdapter;
import com.example.chiza12rpl012018.AddbikeActivity;
import com.example.chiza12rpl012018.LoginActivity;
import com.example.chiza12rpl012018.Model.CustomersModel;
import com.example.chiza12rpl012018.Model.DashboardModel;
import com.example.chiza12rpl012018.R;
import com.example.chiza12rpl012018.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<DashboardModel> models = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnAddbike;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = (View) inflater.inflate(R.layout.fragment_dashboard, container, false);

        Window window = getActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getActivity().getResources().getColor(R.color.white));

        btnAddbike = (Button) view.findViewById(R.id.btn_addbike);
        btnAddbike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddbikeActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.refreshlist);
        swipeRefreshLayout.setRefreshing(false);
//        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
//        DashboardAdapter dashboardAdapter = new DashboardAdapter(getContext(), models);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(dashboardAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                models.clear();
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        getData();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getData() {
        AndroidNetworking.get("http://192.168.43.31/project/api_android/rental_sepeda/list_bikes.php")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String status = response.getString("status");
                            String message = response.getString("message");
                            if (status.equals("success")) {
                                JSONObject data = response.getJSONObject("payload");
                                JSONArray bikes = data.getJSONArray("data");
                                for (int i = 0; i < bikes.length(); i++) {
                                    JSONObject payload = bikes.getJSONObject(i);
                                    String b_id = payload.optString("ID");
                                    String b_kode = payload.optString("KODE");
                                    String b_merk = payload.optString("MERK");
                                    String b_warna = payload.optString("WARNA");
                                    String b_hargasewa = payload.optString("HARGASEWA");
                                    String b_image = payload.optString("IMAGE");

                                    DashboardModel cum = new DashboardModel();
                                    cum.setId(b_id);
                                    cum.setKode(b_kode);
                                    cum.setMerk(b_merk);
                                    cum.setWarna(b_warna);
                                    cum.setHargasewa(b_hargasewa);
                                    cum.setImage(b_image);

                                    models.add(cum);
                                }
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            DashboardAdapter dashboardAdapter = new DashboardAdapter(getContext(), models);
                            recyclerView.setAdapter(dashboardAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("test", String.valueOf(response));
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("anError", error.getLocalizedMessage());
                    }
                });
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