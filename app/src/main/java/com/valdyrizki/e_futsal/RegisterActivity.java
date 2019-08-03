package com.valdyrizki.e_futsal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valdyrizki.e_futsal.adapter.Koneksi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView tvlogin;
    Button btnregister;
    EditText email, nama, nohp, pass;

    Koneksi kon = new Koneksi();
    private final String JSON_URL = kon.ip()+"api/registerapi";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnregister = (Button)findViewById(R.id.btnRegister);
        tvlogin = (TextView)findViewById(R.id.tvLogin);

        nama = (EditText)findViewById(R.id.edtNama);


        email = (EditText)findViewById(R.id.edtEmail);


        nohp = (EditText)findViewById(R.id.edtNohp);


        pass = (EditText)findViewById(R.id.edtPassword);


        sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        if(nama.getText().toString().length()==0) {
            nama.setError("Username diperlukan!");
        }

        if(nohp.getText().toString().length()==0) {
            nohp.setError("Username diperlukan!");
        }

        if(pass.getText().toString().length()==0) {
            pass.setError("Username diperlukan!");
        }

        if(email.getText().toString().length()==0) {
            email.setError("Username diperlukan!");
        }

        final String nama2 = nama.getText().toString();
        final String pass2 = pass.getText().toString();
        final String nohp2 = nohp.getText().toString();
        final String email2 = email.getText().toString();
        final ProgressDialog dialog = ProgressDialog.show(this, null, "Please Wait");

        StringRequest postRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("hasil").equals("1")){

                                editor.putBoolean("savelogin", true);
                                editor.putString("email", jsonObject.getString("email"));
                                editor.putString("name", jsonObject.getString("name"));
                                editor.putString("nohp", jsonObject.getString("nohp"));
                                editor.putInt("id", jsonObject.getInt("id"));
                                editor.commit();

                                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(i);

                            }else{

                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();

                            }

                        }catch (JSONException e){
                            Toast.makeText(RegisterActivity.this, "ERR", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("nama", nama2);
                params.put("email", email2);
                params.put("nohp", nohp2);
                params.put("password", pass2);

                return params;
            }
        };
//        requestQueue.add(postRequest);
        Volley.newRequestQueue(RegisterActivity.this).add(postRequest);
    }

}
