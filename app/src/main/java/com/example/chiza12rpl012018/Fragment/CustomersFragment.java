package com.example.chiza12rpl012018.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.chiza12rpl012018.Adapter.CustomersAdapter;
import com.example.chiza12rpl012018.Adapter.DashboardAdapter;
import com.example.chiza12rpl012018.LoginActivity;
import com.example.chiza12rpl012018.MainActivity;
import com.example.chiza12rpl012018.Model.CustomersModel;
import com.example.chiza12rpl012018.Model.DashboardModel;
import com.example.chiza12rpl012018.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link CustomersFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class CustomersFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<CustomersModel> models = new ArrayList<>();
    private Button btnEdit;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public CustomersFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CustomersFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CustomersFragment newInstance(String param1, String param2) {
//        CustomersFragment fragment = new CustomersFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = (View) inflater.inflate(R.layout.fragment_customers, container, false);

        btnEdit = view.findViewById(R.id.btn_edit);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        getData();

//        CustomersAdapter customersAdapter = new CustomersAdapter(getContext(), models);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(customersAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View dlgView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_customer, null);
//                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Material_Dialog);
//                dialog.setContentView(dlgView);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
    }

    private void getData(){

        AndroidNetworking.get("http://192.168.43.31/project/rental_sepeda/users.php")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String status = response.getString("Status");
                            String message = response.getString("Message");
                            if (status.equals("success")) {
                                JSONObject data = response.getJSONObject("Data");
                                JSONArray customers = data.getJSONArray("customers");
                                for (int i = 0; i<customers.length(); i++){
                                    JSONObject payload = customers.getJSONObject(i);
                                    String u_id = payload.optString("id");
                                    String u_nama = payload.optString("Nama");
                                    String u_email = payload.optString("Email");
                                    String u_noktp = payload.optString("No.KTP");
                                    String u_nohp = payload.optString("No.Hp");
                                    String u_alamat = payload.optString("Alamat");
                                    String role = payload.optString("Role User");
                                    System.out.println(models.size() + " anj");

                                    CustomersModel cum = new CustomersModel();
                                    cum.setProfile(R.drawable.logo);
                                    cum.setName(u_nama);
                                    cum.setLocation(u_alamat);

                                    models.add(cum);
                                }
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            CustomersAdapter customersAdapter = new CustomersAdapter(getContext(),models);
                            recyclerView.setAdapter(customersAdapter);
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
}