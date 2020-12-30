package com.example.shoping.UI;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shoping.Model.Item;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    static final String nameDB = "data.db";

    public DB(@Nullable Context context) {
        super(context, nameDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table cart (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , price TEXT " +
                ", description TEXT , marka TEXT , image TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(sqLiteDatabase);
    }


    public boolean insertData(String name, String price, String description, String marka, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("description", description);
        contentValues.put("marka", marka);
        contentValues.put("image", image);
        Long result = db.insert("cart", null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public ArrayList getAllData() {
        ArrayList<Item> arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from cart", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String id = res.getString(res.getColumnIndex("id"));
            String name = res.getString(res.getColumnIndex("name"));
            String price = res.getString(res.getColumnIndex("price"));
            String description = res.getString(res.getColumnIndex("description"));
            String marka = res.getString(res.getColumnIndex("marka"));
            String image = res.getString(res.getColumnIndex("image"));
            Item item = new Item(name ,marka , description , price ,image , id);
            arrayList.add(item);
            res.moveToNext();
        }
        return arrayList;
    }

    public Integer Delete(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("cart","name = ?" ,new String[]{name});

    }
}
