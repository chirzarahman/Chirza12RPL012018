package com.example.chiza12rpl012018.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.chiza12rpl012018.Adapter.CustomersAdapter;
import com.example.chiza12rpl012018.Model.CustomersModel;
import com.example.chiza12rpl012018.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomersFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<CustomersModel> models = new ArrayList<>();

    public CustomersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = (View) inflater.inflate(R.layout.fragment_customers, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
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

    private void getData() {
        AndroidNetworking.get("http://192.168.43.31/project/rental_sepeda/show_user.php")
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
                                JSONArray customers = data.getJSONArray("data");
                                for (int i = 0; i < customers.length(); i++) {
                                    JSONObject payload = customers.getJSONObject(i);
                                    String u_id = payload.optString("ID");
                                    String u_name = payload.optString("NAMA");
                                    String u_email = payload.optString("EMAIL");
                                    String u_noktp = payload.optString("NOKTP");
                                    String u_nohp = payload.optString("NOHP");
                                    String u_address = payload.optString("ALAMAT");
                                    String u_role = payload.optString("ROLE");

                                    CustomersModel cum = new CustomersModel();
                                    cum.setProfile(R.drawable.logo);
                                    cum.setId(u_id);
                                    cum.setName(u_name);
                                    cum.setEmail(u_email);
                                    cum.setNoktp(u_noktp);
                                    cum.setNohp(u_nohp);
                                    cum.setAddress(u_address);

                                    models.add(cum);
                                }
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            CustomersAdapter customersAdapter = new CustomersAdapter(getContext(), models);
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