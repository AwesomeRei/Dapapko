package com.example.andre.firebasecweather;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;

/**
 * Created by Andre on 12/6/2015.
 */
public class ResepListAdapter extends FirebaseListAdapter<Resep>  {

    public ResepListAdapter(Query ref, Activity activity, int layout){
        super(ref, Resep.class, layout, activity);

    }

    @Override
    protected void populateView(View view,Resep resep)
    {
        String namaResep = resep.getNama_resep();
        String desk = resep.getDeskripsi();
        String gambar2 = resep.getGambar();
        //Bitmap image = resep.getImage();
        String photoData = gambar2.substring(gambar2.indexOf(",") + 1);
        byte[] decodedImage = Base64.decode(photoData, Base64.DEFAULT);
        Bitmap image = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);

        TextView judulText = (TextView)view.findViewById(R.id.judul);
        TextView deskripsi = (TextView)view.findViewById(R.id.deskripsi);
        ImageView gambar = (ImageView)view.findViewById(R.id.imageList);
        judulText.setText(namaResep);
        deskripsi.setText(desk);
        gambar.setImageBitmap(image);

    }
}
