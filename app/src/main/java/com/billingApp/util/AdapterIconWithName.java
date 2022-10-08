package com.billingApp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashirvadbilling.R;

import java.util.List;

public class AdapterIconWithName extends RecyclerView.Adapter<AdapterIconWithName.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;

    public AdapterIconWithName(List<String> titles, List<Integer> images, Context context) {
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_icon_with_name, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.iconName.setText(titles.get(position));
        holder.icon.setImageResource(images.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView iconName;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            iconName = itemView.findViewById(R.id.iconName);
        }
    }
}
