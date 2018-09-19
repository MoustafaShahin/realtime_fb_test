package com.example.moustafashahin.realtime_fb_test.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moustafashahin.realtime_fb_test.ItemClickListener;
import com.example.moustafashahin.realtime_fb_test.R;
import java.util.ArrayList;
public class Adapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView body;
        ItemClickListener itemClickListener;
        public Adapter(View row) {
            super(row);

            title = row.findViewById(R.id.title);
            body = row.findViewById(R.id.body);
            row.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
/*
    public Adapter(MainActivity mainActivity, ArrayList<Post> ff) {
        f =ff;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(f.get(position).getTitle());
        holder.body.setText(f.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return f.size();
    }


}
*/