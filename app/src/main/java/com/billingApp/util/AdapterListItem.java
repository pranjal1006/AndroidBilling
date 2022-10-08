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

public class AdapterListItem extends RecyclerView.Adapter<AdapterListItem.ViewHolder>{

    List<BillItem> itemList;
    LayoutInflater inflater;

    public AdapterListItem(List<BillItem> itemList, Context context) {
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_list_item, parent, false);

        return new AdapterListItem.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillItem billItem = itemList.get(position);
        holder.sn.setText(billItem.getSn().toString());
        holder.itemName.setText(billItem.getItemName());
        holder.quantity.setText(billItem.getQuantity().toString());
        holder.rate.setText(String.format("%.2f", billItem.getRate()));
        holder.amount.setText(String.format("%.2f", billItem.getAmount()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sn;
        TextView itemName;
        TextView quantity;
        TextView rate;
        TextView amount;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            sn = itemView.findViewById(R.id.sn);
            itemName = itemView.findViewById(R.id.itemNameText);
            quantity = itemView.findViewById(R.id.quantity);
            rate = itemView.findViewById(R.id.rate);
            amount = itemView.findViewById(R.id.amount);

        }
    }
}
