package com.example.shoping.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoping.Model.Item;
import com.example.shoping.Adapter.ProductAdapter;
import com.example.shoping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    ArrayList<Item> arrayItem;
    RecyclerView RV_Sale , RV_New;
    ProductAdapter productAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RV_Sale =  view.findViewById(R.id.RV_Sale);
        RV_New = view.findViewById(R.id.RV_New);
        RV_Sale.setHasFixedSize(true);
        RV_Sale.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        RV_New.setHasFixedSize(true);
        RV_New.setLayoutManager(new GridLayoutManager(getContext(),2));
        firebaseFirestore =  FirebaseFirestore.getInstance();
        arrayItem = new ArrayList<>();
        getData();
        return view;
    }

    private void getData(){
        firebaseFirestore.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                       String name = document.get("Name").toString();
                       String marka =   document.get("Marka").toString();
                       String latItem =  document.get("latItem").toString();
                       Log.d("latItem" ,name + " : " +  latItem);
                       String longItem = document.get("longItem").toString();
                       String description= document.get("Description").toString();
                       String Price = document.get("Price").toString();
                       String image =  document.get("image").toString();

                        Item item = new Item(name, marka ,latItem , longItem , description , Price , image);
                            arrayItem.add(item);
                         productAdapter = new ProductAdapter(getContext(), arrayItem);
                        RV_New.setAdapter(productAdapter);
                        RV_Sale.setAdapter(productAdapter);
                    }


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

}
