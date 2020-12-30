package com.example.shoping.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ItemDetailsActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    TextView tv_name, tv_price, tv_discription, tv_marka;
    ImageView item_image;
    String name = "";
    DB db;
    String imageName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        tv_name = findViewById(R.id.tv_item_name_details);
        tv_discription = findViewById(R.id.tv_item_details);
        tv_marka = findViewById(R.id.nameOfmarka);
        tv_price = findViewById(R.id.tv_price_details);
        item_image = findViewById(R.id.img_details);
        db = new DB(this);


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        readDataIteam();

        findViewById(R.id.btnAddCatalog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("add" , tv_name.getText().toString());
                Boolean result = db.insertData(tv_name.getText().toString(),  tv_price.getText().toString() ,tv_discription.getText().toString() ,tv_marka.getText().toString(),imageName);
                if (result == true){
                    Toast.makeText(ItemDetailsActivity.this , "Add Successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ItemDetailsActivity.this , "Add Failure", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void readDataIteam() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        if (name.equals(queryDocumentSnapshot.getString("Name").toString())) {

                            tv_name.setText(queryDocumentSnapshot.get("Name").toString());
                            tv_marka.setText(queryDocumentSnapshot.get("Marka").toString());
                            tv_discription.setText(queryDocumentSnapshot.get("Description").toString());
                            tv_price.setText(queryDocumentSnapshot.get("Price").toString());
                            imageName = queryDocumentSnapshot.get("image").toString();
                            if (queryDocumentSnapshot.get("image").toString().equals("default")) {

                            } else {
                                Glide.with(ItemDetailsActivity.this).
                                        load(Uri.parse(queryDocumentSnapshot.get("image").toString()))
                                        .into(item_image);
                            }
                        }
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
