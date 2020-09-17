package com.example.chiza12rpl012018.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chiza12rpl012018.LoginActivity;
import com.example.chiza12rpl012018.MainActivity;
import com.example.chiza12rpl012018.Model.CustomersModel;
import com.example.chiza12rpl012018.R;
import com.example.chiza12rpl012018.UserActivity;

import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder> {

    private Context mContext;
    private List<CustomersModel> mData;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public CustomersAdapter(Context mContext, List<CustomersModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        view = LayoutInflater.from(mContext).inflate(R.layout.list_customers, parent, false);
//        return holder;
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customers, parent, false);
        // set the view's size, margins, paddings and layout parameters
        final CustomersViewHolder holder = new CustomersViewHolder(v);
//        holder.itemUsers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "Test click " + String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            }
//        });
        // set the Context here
        mContext = parent.getContext();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final CustomersViewHolder holder, int position) {
        final CustomersModel model = mData.get(position);

        holder.profile.setImageResource(mData.get(position).getProfile());
        holder.name.setText(mData.get(position).getName());
        holder.address.setText(mData.get(position).getAddress());
        holder.itemUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click " + String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), UserActivity.class);
                intent.putExtra("u_id", model.getId());
                v.getContext().startActivity(intent);
                ((FragmentActivity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CustomersViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemUsers;
        private ImageView profile;
        private TextView name;
        private TextView address;
        private Button btnedit, btnDelete, btnYes;
        private EditText etName, etEmail, etNoktp, etNohp, etAddress;

        public CustomersViewHolder(View view) {
            super(view);
            itemUsers = (LinearLayout) view.findViewById(R.id.item_users);
            profile = (ImageView) view.findViewById(R.id.img_profile);
            name = (TextView) view.findViewById(R.id.txt_name);
            address = (TextView) view.findViewById(R.id.txt_location);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(CustomersModel data);
    }
}
