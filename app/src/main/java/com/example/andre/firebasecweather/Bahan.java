package com.example.andre.firebasecweather;

/**
 * Created by Andre on 12/14/2015.
 */
public class Bahan {
    String item;
    int value;

    Bahan(String name, int value){
        this.item = name;
        this.value= value;
    }

    public String getItem() {
        return item;
    }

    public int getValue() {
        return value;
    }
}
