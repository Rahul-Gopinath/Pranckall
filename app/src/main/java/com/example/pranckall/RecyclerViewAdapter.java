package com.example.pranckall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private String[] name;
    private String[] phone;

    public RecyclerViewAdapter(String[] name, String[] phone){
        this.name = name;
        this.phone = phone;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item_layout, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        String n = name[position];
        String p = phone[position];
        holder.tvName.setText(n);
        holder.tvPhone.setText(p);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgIcon;
        private TextView tvName, tvPhone;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = itemView.findViewById(R.id.img_icon);
            tvName = itemView.findViewById(R.id.txt_name);
            tvPhone = itemView.findViewById(R.id.txt_phone);
        }
    }

}
