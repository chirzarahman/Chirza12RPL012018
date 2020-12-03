package com.example.chiza12rpl012018.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chiza12rpl012018.Model.DashboardModel;
import com.example.chiza12rpl012018.R;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.Holder> {

    Context mContext;
    List<DashboardModel> mData;

    public DashboardAdapter(Context mContext, List<DashboardModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.listview, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

//        holder.image.setText(mData.get(position).getImage());
//        holder.kode.setText(mData.get(position).getKode());
        holder.merk.setText(mData.get(position).getMerk());
//        holder.warna.setText(mData.get(position).getWarna());
        holder.hargasewa.setText(mData.get(position).getHargasewa());
        String imageUrl = "http://192.168.43.31/project/api_android/rental_sepeda/upload/" + mData.get(position).getImage();
        System.out.println(imageUrl);
        Glide
                .with(this.mContext)
                .load(imageUrl)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView merk;
        private TextView hargasewa;

        public Holder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.img);
            merk = (TextView)view.findViewById(R.id.txt_merk);
            hargasewa = (TextView)view.findViewById(R.id.txt_harga);
        }
    }
}
