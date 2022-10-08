package com.billingApp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashirvadbilling.R;

import java.util.List;

public class AdapterOnlyName extends RecyclerView.Adapter<AdapterOnlyName.ViewHolder> {

    List<String> titles;
    LayoutInflater inflater;

    public AdapterOnlyName(List<String> titles, Context context) {
        this.titles = titles;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_only_name, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.iconName.setText(titles.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView iconName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconName = itemView.findViewById(R.id.iconName);
        }
    }
}
