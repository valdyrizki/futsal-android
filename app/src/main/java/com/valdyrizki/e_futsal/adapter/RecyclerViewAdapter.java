package com.valdyrizki.e_futsal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.valdyrizki.e_futsal.DetailBooking;
import com.valdyrizki.e_futsal.R;
import com.valdyrizki.e_futsal.model.Booking;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    RequestOptions option ;
    private Context mContext ;
    private List<Booking> mData ;

    Koneksi konn = new Koneksi();



    public RecyclerViewAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
        ;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.booking_row_item,parent,false);
        // click listener here
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailBooking.class);
                i.putExtra("id_booking",mData.get(viewHolder.getAdapterPosition()).getId_booking());
                i.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("nama_lapang",mData.get(viewHolder.getAdapterPosition()).getNama_lapang());
                i.putExtra("jenis_lapang",mData.get(viewHolder.getAdapterPosition()).getJenis_lapang());
                i.putExtra("tgl_booking",mData.get(viewHolder.getAdapterPosition()).getTgl_booking());
                i.putExtra("waktu_booking",mData.get(viewHolder.getAdapterPosition()).getWaktu_booking());
                i.putExtra("status_booking",mData.get(viewHolder.getAdapterPosition()).getStatus_booking());
                i.putExtra("harga_lapang",mData.get(viewHolder.getAdapterPosition()).getHarga_lapang());
                i.putExtra("gambar_lapang",mData.get(viewHolder.getAdapterPosition()).getGambar_lapang());

                mContext.startActivity(i);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvname.setText(mData.get(position).getNama_lapang());
        holder.tvjenislapang.setText(mData.get(position).getJenis_lapang());
        holder.tvtanggal.setText(mData.get(position).getTgl_booking()+" - "+mData.get(position).getWaktu_booking());
        holder.tvharga.setText(String.valueOf(mData.get(position).getHarga_lapang()));
        holder.tvstatus.setText(mData.get(position).getStatus_booking());

        // load image from the internet using Glide
        Glide.with(mContext).load(konn.ip()+"storage/lapang/"+mData.get(position).getGambar_lapang()).apply(option).into(holder.img_thumbnail);

        holder.btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Cancel ["+position+"]", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvname,tvjenislapang,tvtanggal,tvharga,tvstatus;
        private ImageView img_thumbnail;
        private ConstraintLayout view_container;
        Button btncancel;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);

            tvname = itemView.findViewById(R.id.tv_nama_lapang);
            tvjenislapang = itemView.findViewById(R.id.tv_jenis_lapang);
            tvtanggal = itemView.findViewById(R.id.tv_tanggal);
            tvharga = itemView.findViewById(R.id.tv_harga2);
            tvstatus = itemView.findViewById(R.id.tv_status);
            btncancel = itemView.findViewById(R.id.BtnTestimoni);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
