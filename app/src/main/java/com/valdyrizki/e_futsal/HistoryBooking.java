package com.valdyrizki.e_futsal;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.valdyrizki.e_futsal.adapter.Koneksi;
import com.valdyrizki.e_futsal.adapter.RecyclerViewAdapter;
import com.valdyrizki.e_futsal.model.Booking;
import com.valdyrizki.e_futsal.Global.Fungsi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HistoryBooking extends AppCompatActivity {
    Koneksi kon = new Koneksi();
    private final String JSON_URL = kon.ip()+"api/historybooking";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Booking> lstBooking;
    private RecyclerView recyclerView;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Fungsi f =new Fungsi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booking);

        lstBooking = new ArrayList<>();

        sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest() {
        final ProgressDialog dialog = ProgressDialog.show(this, null, "Please Wait");

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();
                JSONObject jsonObject = null;
                for(int i = 0 ; i < response.length(); i++){

                    try {

                        jsonObject = response.getJSONObject(i);
                        if (jsonObject.getInt("id") == sharedPreferences.getInt("id", 0)){
                            Booking bo= new Booking();
                            bo.setName(jsonObject.getString("name"));
                            bo.setNama_lapang(jsonObject.getString("nama_lapang"));
                            bo.setJenis_lapang(f.getJenislapang(jsonObject.getInt("jenis_lapang")));
                            bo.setTgl_booking(jsonObject.getString("tgl_booking"));
                            bo.setWaktu_booking(jsonObject.getString("waktu_booking"));
                            bo.setHarga_lapang(jsonObject.getInt("harga_lapang"));
                            bo.setStatus_booking(f.getStatusBooking(jsonObject.getInt("status_booking")));
                            bo.setGambar_lapang(jsonObject.getString("gambar_lapang"));

                            lstBooking.add(bo);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(HistoryBooking.this, "Ada yg gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                setuprecyclerview(lstBooking);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        });

        requestQueue = Volley.newRequestQueue(HistoryBooking    .this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Booking> lstBooking){
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
