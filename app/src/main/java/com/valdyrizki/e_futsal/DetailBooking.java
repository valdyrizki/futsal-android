package com.valdyrizki.e_futsal;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.valdyrizki.e_futsal.adapter.Koneksi;

public class DetailBooking extends AppCompatActivity {
    Koneksi konn = new Koneksi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        getSupportActionBar().hide();

        String name = getIntent().getExtras().getString("Detail Lapang");

        TextView namalapang = (TextView)findViewById(R.id.dt_tvnama);
        TextView jenislapang = (TextView)findViewById(R.id.dt_tvjenis);
        TextView tglbooking = (TextView)findViewById(R.id.dt_tvtgl);
        TextView waktubooking = (TextView)findViewById(R.id.dt_tvwaktu);
        TextView statusbooking = (TextView)findViewById(R.id.dt_tvstatus);
        TextView hargalapang = (TextView)findViewById(R.id.dt_tvharga);
        ImageView gambarlapang = findViewById(R.id.dt_thumbnail);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(name);

        Bundle bundle = getIntent().getExtras();
        namalapang.setText(bundle.getString("nama_lapang"));
        jenislapang.setText(bundle.getString("jenis_lapang"));
        tglbooking.setText(bundle.getString("tgl_booking"));
        waktubooking.setText(bundle.getString("waktu_booking"));
        statusbooking.setText(bundle.getString("status_booking"));
        hargalapang.setText(String.valueOf(bundle.getInt("harga_lapang")));

        //GLIDE
        RequestOptions option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                ;
        Glide.with(this).load(konn.ip()+"storage/lapang/"+bundle.getInt("gambar_lapang")).apply(option).into(gambarlapang);

    }
}
