package com.valdyrizki.e_futsal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valdyrizki.e_futsal.adapter.Koneksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Koneksi kon = new Koneksi();
    private final String JSON_URL = kon.ip()+"api/loginapi";

    EditText username,password;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("savelogin",false) == true){
            try{
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, "Error padahal dah login", Toast.LENGTH_SHORT).show();
            }
        }else {

            username = (EditText) findViewById(R.id.tvUsername);


            password = (EditText) findViewById(R.id.tvPassword);


            Button login = (Button) findViewById(R.id.btnLogin);
            TextView tvlogin = (TextView) findViewById(R.id.tvLogin);

            sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
            editor = sharedPreferences.edit();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });

            tvlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            });

            TextView reg = (TextView) findViewById(R.id.tv_register);
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(i);
                }
            });

        }
    }

    private void login() {
        if(username.getText().toString().length()==0) {
            username.setError("Username diperlukan!");
        }

        if(password.getText().toString().length()==0){
            password.setError("password gaboleh kosong!");
        }

        final String email2 = username.getText().toString();
        final String pass2 = password.getText().toString();

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
                                editor.putString("password", jsonObject.getString("password"));
                                editor.putString("name", jsonObject.getString("name"));
                                editor.putString("nohp", jsonObject.getString("nohp"));
                                editor.putInt("id", jsonObject.getInt("id"));
                                editor.commit();

                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);

                            }else{

                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();

                            }

                        }catch (JSONException e){
                            Toast.makeText(LoginActivity.this, "ERR", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email2);
                params.put("password", pass2);

                return params;
            }
        };
//        requestQueue.add(postRequest);
        Volley.newRequestQueue(LoginActivity.this).add(postRequest);
    }

}
