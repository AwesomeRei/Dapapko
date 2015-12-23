package com.example.andre.firebasecweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Andre on 12/5/2015.
 */
public class Resep {
    private String nama_resep;
    private String deskripsi;
    private String gambar;
    private String cara;
    private String bahan;

    public Resep(){
    }
    public Resep(String nama_resep, String deskripsi,String gambar,String bahan, String cara){
        this.nama_resep = nama_resep;
        this.deskripsi = deskripsi;
        this.gambar=gambar;
        this.cara = cara;
        this.bahan = bahan;
    }
    public String getNama_resep() {
        return nama_resep;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public String getBahan() {
        return bahan;
    }

    public String getCara() {
        return cara;
    }

    /*public Bitmap getImage() {
        String photoData = gambar.substring(gambar.indexOf(",") + 1);
        byte[] decodedImage = Base64.decode(photoData, Base64.DEFAULT);
        Bitmap image = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);
        return image;
    }*/
}
