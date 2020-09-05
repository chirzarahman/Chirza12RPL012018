package com.example.chiza12rpl012018;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText etEmail, etNama, etNoktp, etNohp, etAlamat, etConfirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnL_register);
        etNama = findViewById(R.id.input_nama);
        etEmail = findViewById(R.id.input_email);
        etNoktp = findViewById(R.id.input_noktp);
        etNohp = findViewById(R.id.input_nohp);
        etAlamat = findViewById(R.id.input_alamat);
        etConfirmpass = findViewById(R.id.input_confirmpassword);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView(); //set status background black
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
            getWindow().setStatusBarColor(ContextCompat.getColor(RegisterActivity.this, R.color.blue));
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String noktp = etNoktp.getText().toString();
                String nohp = etNohp.getText().toString();
                String alamat = etAlamat.getText().toString();
                String confirmpassword = etConfirmpass.getText().toString();

                HashMap<String, String> body = new HashMap<>();
                body.put("nama", nama);
                body.put("email", email);
                body.put("noktp", noktp);
                body.put("nohp", nohp);
                body.put("alamat", alamat);
                body.put("roleuser", "2");
                body.put("password", confirmpassword);

                AndroidNetworking.post("http://192.168.43.31/project/rental_sepeda/register.php")
                        .addBodyParameter(body)
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                Log.d("ABR", "respon : " + response);
                                String status = response.optString("status");
                                String message = response.optString("message");
                                if (status.equalsIgnoreCase("success")) {
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    finishAffinity();
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onError(ANError error) {
                                // handle error
                                Log.d("anError", error.getLocalizedMessage());
                            }
                        });
            }
        });
    }
    public void login (View View){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}