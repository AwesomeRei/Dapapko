package com.example.andre.firebasecweather;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

public class ShowFullResep extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.andre.firebasecweather";
    TextView judul;
    TextView deskripsi;
    ImageView gambar;
    private Firebase mRef;
    Resep data;
    String item;
    String[] listbahan;
    String[] stepMasak;
    int i = 0;
    Button home;
    Button belanja;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_resep);
        Firebase.setAndroidContext(this);

        mRef = new Firebase("https://dpp.firebaseio.com/Recipe");
        Intent intent = getIntent();
        item = intent.getStringExtra("judul");
        belanja = (Button)findViewById(R.id.belanja);

        home = (Button) findViewById(R.id.listresep);
        belanja.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                belanja(v);
            }
        });

        home.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                listresep(v);
            }
        });

        Query query = mRef.orderByChild("nama_resep").equalTo(item);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                judul = (TextView) findViewById(R.id.x);
                deskripsi = (TextView) findViewById(R.id.deskripsi);
                gambar = (ImageView) findViewById(R.id.imageView);

                data = dataSnapshot.getValue(Resep.class);
                judul.setText(data.getNama_resep());
                deskripsi.setText(data.getDeskripsi());

                String photoData = data.getGambar().substring(data.getGambar().indexOf(",") + 1);
                byte[] decodedImage = Base64.decode(photoData, Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);
                gambar.setImageBitmap(image);
                listbahan = data.getBahan().split("\n");
                stepMasak = data.getCara().split("\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    protected void onStart(){
        super.onStart();
    }
    public void belanja(View view){
        Intent inBelanja = new Intent(this,BelanjaActivity.class);
        inBelanja.putExtra("judul", item);
        inBelanja.putExtra("listbahan", listbahan);
        startActivity(inBelanja);
    }
    public void masak(View view){
        Intent inMasak = new Intent(this,MasakActivity.class);
        inMasak.putExtra("judul", item);
        inMasak.putExtra("stepmasak", stepMasak);
        inMasak.putExtra("urutan",i);
        startActivity(inMasak);
    }
    public void listresep(View view){
        Intent inHome = new Intent(this,MainActivity.class);
        startActivity(inHome);
    }


}
