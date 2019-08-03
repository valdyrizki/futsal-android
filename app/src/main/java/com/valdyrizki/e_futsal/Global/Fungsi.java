package com.valdyrizki.e_futsal.Global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.valdyrizki.e_futsal.MainActivity;
import com.valdyrizki.e_futsal.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Fungsi {
    @SuppressLint("ResourceAsColor")
    public static void pesan_sukses(View view, String pesan){
//        Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

        Snackbar snackbar = Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(R.color.sukses);
        snackbar.show();
    }

    @SuppressLint("ResourceAsColor")
    public static void pesan_error(View view, String pesan){
//        Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

        Snackbar snackbar = Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(R.color.error);
        snackbar.show();


    }

    @SuppressLint("ResourceAsColor")
    public static void pesan_warning(View view, String pesan){
//        Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

        Snackbar snackbar = Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(R.color.warning);
        snackbar.show();
    }

    public String getJenislapang(int value){
    String hasil;

        switch(value) {
            case 1:
                hasil = "Sintetis";
                break;
            case 2:
                hasil = "V-Sport";
                break;
            default:
                hasil = "ERROR";
        }

        return hasil;
    }

    public String getStatusBooking(int value){
        String hasil;

        switch(value) {
            case 1:
                hasil = "Pending";
                break;
            case 2:
                hasil = "Konfirmasi";
                break;
            case 3:
                hasil = "Selesai";
                break;
            case 4:
                hasil = "Cancel";
                break;
            default:
                hasil = "ERROR";
        }

        return hasil;
    }

//    public int getRupiah(int value) {
//        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
//
//        formatRp.setCurrencySymbol("Rp. ");
//        formatRp.setMonetaryDecimalSeparator(',');
//        formatRp.setGroupingSeparator('.');
//
//        kursIndonesia.setDecimalFormatSymbols(formatRp);
//        int hasil =  kursIndonesia.format(value);
//        return hasil;
//    }

}
