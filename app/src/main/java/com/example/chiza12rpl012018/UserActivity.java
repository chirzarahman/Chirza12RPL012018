package com.example.chiza12rpl012018;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.chiza12rpl012018.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    private TextView etName, etNoktp, etNohp, etAddress;
    private Button btnEdit, btnDelete;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnDelete = findViewById(R.id.btn_delete);
        etName = findViewById(R.id.et_name);
        etNoktp = findViewById(R.id.et_noktp);
        etNohp = findViewById(R.id.et_nohp);
        etAddress = findViewById(R.id.et_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getData();

        btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String noktp = etNoktp.getText().toString();
                String nohp = etNohp.getText().toString();
                String address = etAddress.getText().toString();
                String u_id = getIntent().getStringExtra("u_id");
                editData(u_id, name, noktp, nohp, address);
                Toast.makeText(UserActivity.this, "edit", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    private void getData() {
        String id = getIntent().getStringExtra("u_id");
        AndroidNetworking.post("http://192.168.43.31/project/api_android/rental_sepeda/detail_user.php")
                .addBodyParameter("id", id)
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
                                JSONObject payload = response.getJSONObject("payload");
                                String u_id = payload.optString("ID");
                                String u_name = payload.optString("NAMA");
                                String u_email = payload.optString("EMAIL");
                                String u_noktp = payload.optString("NOKTP");
                                String u_nohp = payload.optString("NOHP");
                                String u_address = payload.optString("ALAMAT");
                                String u_role = payload.optString("ROLE");

                                etName.setText(u_name);
                                etNoktp.setText(u_noktp);
                                etNohp.setText(u_nohp);
                                etAddress.setText(u_address);
                                Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
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

    public void editData(String id, String name, String noktp, String nohp, String address) {
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        final String id_auth = sharedPreferences.getString("id", "");
        HashMap<String, String> body = new HashMap<>();
        body.put("id_auth", id_auth);
        body.put("id", id);
        body.put("name", name);
        body.put("noktp", noktp);
        body.put("nohp", nohp);
        body.put("address", address);

        AndroidNetworking.post("http://192.168.43.31/project/api_android/rental_sepeda/edit_user.php")
                .addBodyParameter(body)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("ABR", "respon : " + response);
                        String status = response.optString("status");
                        String message = response.optString("message");
                        if (status.equalsIgnoreCase("success")) {
                            Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(UserActivity.this, "Kesalahan Internal", Toast.LENGTH_SHORT).show();
                        Log.d("Soy", "onError: " + anError.getErrorBody());
                        Log.d("Soy", "onError: " + anError.getLocalizedMessage());
                        Log.d("Soy", "onError: " + anError.getErrorDetail());
                        Log.d("Soy", "onError: " + anError.getResponse());
                        Log.d("Soy", "onError: " + anError.getErrorCode());
                    }
                });
    }

    private void deleteData() {
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String id_auth = sharedPreferences.getString("id", "");
        String id = getIntent().getStringExtra("u_id");
        AndroidNetworking.post("http://192.168.43.31/project/api_android/rental_sepeda/delete_user.php")
                .addBodyParameter("id", id)
                .addBodyParameter("id_auth", id_auth)
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
                                Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}