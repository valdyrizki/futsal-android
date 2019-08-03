package com.valdyrizki.e_futsal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static com.valdyrizki.e_futsal.Global.Fungsi.*;



public class MainActivity extends AppCompatActivity {

   TextView nama,email,nohp;

    String ceklogin;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("savelogin",false) == false){
            try{
                Toast.makeText(this, "Anda belum login silahkan login dulu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, "Anda belum login silahkan login dulu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }else {

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = findViewById(R.id.fab);
            FloatingActionButton fab2 = findViewById(R.id.fab2);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HistoryBooking.class);
                startActivity(intent);
//                    pesan_sukses(view, "Lihat History Booking");

                }
            });

            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TambahBooking.class);
                startActivity(intent);
//                    pesan_sukses(view, "Tambah Booking");

                }
            });


            nama = (TextView) findViewById(R.id.tv_nama);
            email = (TextView) findViewById(R.id.tv_email);
            nohp = (TextView) findViewById(R.id.tv_nohp);

            nama.setText(sharedPreferences.getString("name", null));
            nohp.setText(sharedPreferences.getString("nohp", null));
            email.setText(sharedPreferences.getString("email", null));

        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.item_logout) {
            SharedPreferences preferences = getSharedPreferences("loginmember", MODE_PRIVATE);
            preferences.edit().clear().commit();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }else if(id == R.id.item_profil){
            Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
