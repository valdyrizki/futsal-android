package com.valdyrizki.e_futsal;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfilActivity extends AppCompatActivity {
    TextView nama,email,nohp,id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nama = (TextView)findViewById(R.id.tv_profilnama);
        email = (TextView)findViewById(R.id.tv_profilemail);
        nohp = (TextView)findViewById(R.id.tv_profilnohp);

        sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        nama.setText(sharedPreferences.getString("name",null));
        nohp.setText(sharedPreferences.getString("nohp",null));
        email.setText(sharedPreferences.getString("email",null));


    }
}
