package com.example.chiza12rpl012018.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.chiza12rpl012018.LoginActivity;
import com.example.chiza12rpl012018.R;

import org.json.JSONException;
import org.json.JSONObject;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProfileFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileFragment extends Fragment {

    private TextView tvName, tvEmail, tvNoktp, tvNohp, tvAddress;
    private Button btnLogout;
    private String id;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = (View) inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvNoktp = view.findViewById(R.id.tv_noktp);
        tvNohp = view.findViewById(R.id.tv_nohp);
        tvAddress = view.findViewById(R.id.tv_address);
        btnLogout = view.findViewById(R.id.btn_logout);

        Window window = getActivity().getWindow();
        View decorView = window.getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getActivity().getResources().getColor(R.color.blue));

        SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String name = preferences.getString("name", "");
        String noktp = preferences.getString("noktp", "");
        String nohp = preferences.getString("nohp", "");
        String address = preferences.getString("address", "");

        tvEmail.setText(email);
        tvName.setText(name);
        tvNoktp.setText(noktp);
        tvNohp.setText(nohp);
        tvAddress.setText(address);
        System.out.println("test " + email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}