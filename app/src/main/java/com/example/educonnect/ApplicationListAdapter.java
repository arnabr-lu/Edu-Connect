package com.example.educonnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListAdapter.MyViewHolder>{
    Context context;
    ArrayList<ApplicationData> data;

    public ApplicationListAdapter(Context context, ArrayList<ApplicationData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ApplicationListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.application_list_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationListAdapter.MyViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            content = itemView.findViewById(R.id.itemContent);
        }
    }
}
