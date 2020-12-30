package com.example.shoping.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoping.Adapter.ItemCartAdapter;
import com.example.shoping.UI.DB;
import com.example.shoping.Model.Item;
import com.example.shoping.R;

import java.util.ArrayList;


public class CartFragment extends Fragment {
   static RecyclerView RV_Cart;
   static DB db;
   static ItemCartAdapter itemCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DB(getContext());
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        RV_Cart = view.findViewById(R.id.RV_Cart);
        ShowData(getContext());
        RV_Cart.setHasFixedSize(true);
        RV_Cart.setLayoutManager(new GridLayoutManager(getContext(),2));
        return view;
    }

  static  public void  ShowData(Context context){
        ArrayList<Item> items = db.getAllData();
        itemCartAdapter = new ItemCartAdapter(context, items);
        RV_Cart.setAdapter(itemCartAdapter);
    }
}
