package com.example.shoping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoping.UI.ItemDetailsCartActivity;
import com.example.shoping.Model.Item;
import com.example.shoping.R;

import java.util.ArrayList;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {
    Context context;
    ArrayList<Item> itemList;

    public ItemCartAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ItemCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.Card_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ItemDetailsCartActivity.class);
                intent.putExtra("name" , item.getName());
                context.startActivity(intent);
            }
        });
        holder.nameOfitem.setText(item.getName());
        holder.nameOfMarkra.setText(item.getMarka());
        holder.priceNum.setText(item.getPrice());
        if (item.getImage().equals("default")) {

        } else {
            Glide.with(context).
                    load(item.getImage())
                    .into(holder.Item_img);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Item_img;
        TextView nameOfMarkra, nameOfitem, priceNum;
        LinearLayout Card_Item ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Card_Item = itemView.findViewById(R.id.Card_Item);
            nameOfMarkra = itemView.findViewById(R.id.nameOfMarkra);
            nameOfitem = itemView.findViewById(R.id.nameOfitem);
            priceNum = itemView.findViewById(R.id.priceNum);
            Item_img = itemView.findViewById(R.id.Item_img);

        }
    }
}
