package com.example.shoping.Model;

public class Item {

    String name;
    String marka;
    String latItem;
    String longItem;
    String description;
    String price;
    String image;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item(String name, String marka,String description, String price, String image, String id) {
        this.name = name;
        this.marka = marka;
        this.description = description;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public Item() {
    }

    public Item(String name, String marka, String latItem, String longItem, String description, String price ,String image) {
        this.name = name;
        this.marka = marka;
        this.latItem = latItem;
        this.longItem = longItem;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getLatItem() {
        return latItem;
    }

    public void setLatItem(String latItem) {
        this.latItem = latItem;
    }

    public String getLongItem() {
        return longItem;
    }

    public void setLongItem(String longItem) {
        this.longItem = longItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
