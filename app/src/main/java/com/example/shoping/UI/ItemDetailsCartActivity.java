package com.example.shoping.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shoping.Fragments.CartFragment;
import com.example.shoping.R;

public class ItemDetailsCartActivity extends AppCompatActivity {
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_cart);
        db = new DB(ItemDetailsCartActivity.this);
        final String name = getIntent().getStringExtra("name");
        findViewById(R.id.btnDeleteCatalog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.Delete(name);
                Toast.makeText(ItemDetailsCartActivity.this , "Delete " + name , Toast.LENGTH_SHORT).show();
                CartFragment.ShowData(ItemDetailsCartActivity.this);
            }
        });


    findViewById(R.id.btnBuyCatalog).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri =  Uri.parse("https://www.paypal.com/signin?returnUri=https%3A%2F%2Fwww.paypal.com%2Fmyaccount%2Ftransfer&state=%2Fhomepage");
            Intent intent = new Intent(Intent.ACTION_VIEW ,uri);
            startActivity(intent);
        }
    });

    }


}
