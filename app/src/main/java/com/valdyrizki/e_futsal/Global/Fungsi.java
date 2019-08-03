package com.valdyrizki.e_futsal.Global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.valdyrizki.e_futsal.MainActivity;
import com.valdyrizki.e_futsal.R;

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

}
