package com.example.chiza12rpl012018.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chiza12rpl012018.Model.CustomersModel;
import com.example.chiza12rpl012018.R;

import java.text.BreakIterator;
import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder> {

    private Context mContext;
    private List<CustomersModel> mData;

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
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customers, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CustomersViewHolder holder = new CustomersViewHolder(v);
        // set the Context here
        mContext = parent.getContext();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomersViewHolder holder, int position) {

        holder.profile.setImageResource(mData.get(position).getProfile());
//        holder.input_noktp.setText(mData.get(position).getName());
        holder.name.setText(mData.get(position).getName());
//        holder.email.setText(mData.get(position).getEmail());
//        holder.nohp.setText(mData.get(position).getNohp());
        holder.location.setText(mData.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CustomersViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile;
        private TextView name;
        private TextView location;
        private Button btnedit, btnDelete;
        private EditText input_name;

        public CustomersViewHolder( View view) {
            super(view);
            profile = (ImageView) view.findViewById(R.id.img_profile);
            name = (TextView)view.findViewById(R.id.txt_name);
            location = (TextView)view.findViewById(R.id.txt_location);
            btnedit = view.findViewById(R.id.btn_edit);
            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View dlgView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_edit_customer, null);
                    final Dialog dialog = new Dialog(v.getContext(), android.R.style.Theme_Material_Dialog);
                    input_name = (EditText)dlgView.findViewById(R.id.input_nama);

                    input_name.setText(mData.get(getAdapterPosition()).getName());

                    dialog.setContentView(dlgView);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            btnDelete = view.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dlgView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_delete_customer, null);
                    final Dialog dialog = new Dialog(v.getContext(), android.R.style.Theme_Material_Dialog);

                    dialog.setContentView(dlgView);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
        }
    }
}
