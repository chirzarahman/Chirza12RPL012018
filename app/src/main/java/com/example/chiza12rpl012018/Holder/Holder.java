package com.example.chiza12rpl012018.Holder;

import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chiza12rpl012018.R;

public class Holder extends RecyclerView.ViewHolder {

    public ImageView mImg;
    public TextView mTxtName, mTxtDesc;

    public Holder(@NonNull View itemView) {
        super(itemView);

        this.mImg = itemView.findViewById(R.id.image);
        this.mTxtName = itemView.findViewById(R.id.txtName);
        this.mTxtDesc = itemView.findViewById(R.id.txtDesc);
    }
}
