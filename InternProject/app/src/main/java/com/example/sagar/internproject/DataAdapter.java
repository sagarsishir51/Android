package com.example.sagar.internproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 11/6/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.dataHolder> {
    List<userInfo> info;
    dataClickListener clickListener;
    public interface dataClickListener {
        void onDataClick(int position);
    }
    public class dataHolder extends RecyclerView.ViewHolder{
        TextView nameHolder;

        public dataHolder(View itemView, final dataClickListener clickListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onDataClick(getAdapterPosition());
                    }
                }
            });
        }
    }
    public DataAdapter(dataClickListener listener){
        this.info=new ArrayList<userInfo>();
        clickListener=listener;
    }
    @Override
    public dataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_view_record, parent, false);
        return new dataHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(dataHolder holder, int position) {
        userInfo infoData = info.get(position);
        holder.nameHolder.setText(infoData.getName());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public void setUser(@NonNull List<userInfo> info) {
        this.info = info;
        notifyDataSetChanged();
    }

    public userInfo getUser(int position) {
        return this.info.get(position);
    }

}
