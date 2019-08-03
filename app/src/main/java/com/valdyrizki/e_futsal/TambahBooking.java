package com.valdyrizki.e_futsal;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valdyrizki.e_futsal.adapter.DatePickerFragment;
import com.valdyrizki.e_futsal.adapter.Koneksi;
import com.valdyrizki.e_futsal.model.Booking;
import com.valdyrizki.e_futsal.model.Lapang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TambahBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText tanggal;
    private Spinner waktu, lapang;
    private Button booking;

    ArrayList<String> arrayjam = new ArrayList<String>();
    ArrayList<String> arraylapang = new ArrayList<String>();

    Koneksi kon = new Koneksi();
    private final String JSON_URL = kon.ip()+"api/listlapang";
    private final String JSON_URL2 = kon.ip()+"api/addbookingapi";

    private JsonArrayRequest request;
    private RequestQueue requestQueue;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_booking);

        sharedPreferences = getSharedPreferences("loginmember", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setSpinnerLapang();
        setSpinnerWaktu();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tanggal = (EditText)findViewById(R.id.edt_tglbooking);
        if(tanggal.getText().toString().length()==0) {
            tanggal.setError("tanggal diperlukan!");
        }

        //Code supaya ga muncul keyboard input
        tanggal.setInputType(InputType.TYPE_NULL);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        booking = (Button)findViewById(R.id.btn_Booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmbhbooking();
            }
        });

    }

    private void tmbhbooking() {
        if (lapang.getSelectedItem().toString().trim().equals("--- PILIH LAPANG ---")) {
            Toast.makeText(TambahBooking.this, "Lapang harus dipilih", Toast.LENGTH_SHORT).show();
        }

        if(tanggal.getText().toString().length()==0) {
            tanggal.setError("tanggal diperlukan!");
        }

        final String lapang2 = lapang.getSelectedItem().toString();
        final String tanggal2 = tanggal.getText().toString();
        final String waktu2 = waktu.getSelectedItem().toString();
        final int id2 = sharedPreferences.getInt("id", 0);
        final String name2 = sharedPreferences.getString("name", null);
        final String nohp2 = sharedPreferences.getString("nohp", null);

        final ProgressDialog dialog = ProgressDialog.show(this, null, "Please Wait");

        StringRequest postRequest = new StringRequest(Request.Method.POST, JSON_URL2,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        // response
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("hasil").equals("1")){
                                Toast.makeText(TambahBooking.this, "Berhasil booking", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(TambahBooking.this,HistoryBooking.class);
                                startActivity(i);
                            }else if(jsonObject.getString("hasil").equals("2")){

                                Toast.makeText(TambahBooking.this, "Lapang sudah dibooking, coba dilapang dan waktu lain", Toast.LENGTH_SHORT).show();

                            }else{

                                Toast.makeText(TambahBooking.this, "Error tak terduga "+response, Toast.LENGTH_SHORT).show();

                            }

                        }catch (JSONException e){
                            Toast.makeText(TambahBooking.this, "ERR " +response, Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        dialog.dismiss();
                        Toast.makeText(TambahBooking.this, "Error "+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id", String.valueOf(id2));
                params.put("name", name2);
                params.put("nohp", nohp2);
                params.put("nama_lapang", lapang2);
                params.put("tgl_booking", tanggal2);
                params.put("waktu_booking", waktu2);

                return params;
            }
        };
//        requestQueue.add(postRequest);
        Volley.newRequestQueue(TambahBooking.this).add(postRequest);
    }

    private void setSpinnerWaktu() {
        arrayjam.add("08:00:00");
        arrayjam.add("09:00:00");
        arrayjam.add("10:00:00");
        arrayjam.add("11:00:00");
        arrayjam.add("12:00:00");
        arrayjam.add("13:00:00");
        arrayjam.add("14:00:00");
        arrayjam.add("15:00:00");
        arrayjam.add("16:00:00");
        arrayjam.add("17:00:00");
        arrayjam.add("18:00:00");
        arrayjam.add("19:00:00");
        arrayjam.add("20:00:00");
        arrayjam.add("21:00:00");
        arrayjam.add("22:00:00");
        arrayjam.add("23:00:00");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayjam);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        waktu = (Spinner)findViewById(R.id.edt_waktubooking);

        waktu.setAdapter(adapter);
    }


    private void setSpinnerLapang() {
        arraylapang.add("--- PILIH LAPANG ---");
        final ProgressDialog dialog = ProgressDialog.show(this, null, "Please Wait");
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();
                JSONObject jsonObject = null;
                for(int i = 0 ; i < response.length(); i++){


                    try {
                        jsonObject = response.getJSONObject(i);
//                        arraylapang.add(jsonObject.getString("nama_lapang")+" - "+jsonObject.getString("jenis_lapang")+" : "+jsonObject.getInt("harga_lapang"));
                            arraylapang.add(jsonObject.getString("nama_lapang"));
//                        HashMap<Integer,String>

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(TambahBooking.this, "Ada yg gagal", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        });

        requestQueue = Volley.newRequestQueue(TambahBooking.this);
        requestQueue.add(request);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraylapang);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lapang = (Spinner)findViewById(R.id.CmbLapang);

        lapang.setAdapter(adapter2);



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateformat = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(c.getTime());
        tanggal.setText(strDate);
    }
}
