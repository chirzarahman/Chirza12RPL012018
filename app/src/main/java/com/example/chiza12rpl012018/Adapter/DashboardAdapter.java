package com.example.chiza12rpl012018.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.image.setImageResource(mData.get(position).getImage());
        holder.title.setText(mData.get(position).getTitle());
        holder.description.setText(mData.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView description;

        public Holder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.img_icon);
            title = (TextView)view.findViewById(R.id.txt_title);
            description = (TextView)view.findViewById(R.id.txt_description);
        }
    }
}
