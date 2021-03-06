package com.example.chiza12rpl012018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    private SharedPreferences preferences;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.et_login_email);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView(); //set status background black
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
            getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.blue));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEmpty = false;
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    isEmpty = true;
                    etEmail.setError("Email harus diisi");
                }
                if (password.isEmpty()) {
                    isEmpty = true;
                    etPassword.setError("Password harus diisi");
                }

                if (!isEmpty) {
                    AndroidNetworking.post("http://192.168.43.31/project/api_android/rental_sepeda/login.php")
                            .addBodyParameter("email", etEmail.getText().toString().trim())
                            .addBodyParameter("password", etPassword.getText().toString().trim())
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
                                            String u_id = payload.optString("LOGIN_ID");
                                            String u_name = payload.optString("LOGIN_NAMA");
                                            String u_email = payload.optString("LOGIN_EMAIL");
                                            String u_noktp = payload.optString("LOGIN_NOKTP");
                                            String u_nohp = payload.optString("LOGIN_NOHP");
                                            String u_address = payload.optString("LOGIN_ALAMAT");
                                            String u_role = payload.optString("ROLE");

                                            preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
                                            preferences.edit()
                                                    .putString("id", u_id)
                                                    .putString("name", u_name)
                                                    .putString("email", u_email)
                                                    .putString("noktp", u_noktp)
                                                    .putString("nohp", u_nohp)
                                                    .putString("address", u_address)
                                                    .putString("role", u_role)
                                                    .apply();
                                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
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
            }
        });
    }

    public void btn_tv_register(View View) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}