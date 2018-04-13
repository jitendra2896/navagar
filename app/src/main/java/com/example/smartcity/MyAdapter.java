package com.example.smartcity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    ArrayList<String> s;
    public MyAdapter(ArrayList<String> s){
        this.s = s;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(s.get(position));
    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.nodes);
        }
    }
}
