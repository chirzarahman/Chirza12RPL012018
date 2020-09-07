package com.example.chiza12rpl012018.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chiza12rpl012018.Model.CustomersModel;
import com.example.chiza12rpl012018.R;

import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.Holder> {

    Context mContext;
    List<CustomersModel> mData;

    public CustomersAdapter(Context mContext, List<CustomersModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.list_customers, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.profile.setImageResource(mData.get(position).getProfile());
        holder.name.setText(mData.get(position).getName());
        holder.location.setText(mData.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private ImageView profile;
        private TextView name;
        private TextView location;

        public Holder(View view) {
            super(view);

            profile = (ImageView) view.findViewById(R.id.img_profile);
            name = (TextView)view.findViewById(R.id.txt_name);
            location = (TextView)view.findViewById(R.id.txt_location);
        }
    }
}
