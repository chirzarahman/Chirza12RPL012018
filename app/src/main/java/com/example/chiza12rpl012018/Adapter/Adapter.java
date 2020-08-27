package com.example.chiza12rpl012018.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chiza12rpl012018.Holder.Holder;
import com.example.chiza12rpl012018.Model.Model;
import com.example.chiza12rpl012018.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {

    Context context;
    ArrayList<Model> models;

    public Adapter (Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.mImg.setImageResource(models.get(position).getImg());
        holder.mTxtName.setText(models.get(position).getTxtname());
        holder.mTxtDesc.setText(models.get(position).getTxtdesc());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
