package com.example.andre.firebasecweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TambahBahan extends AppCompatActivity {

    Button next;
    Button tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahbahan);

        next = (Button)findViewById(R.id.next);
        tambah = (Button)findViewById(R.id.tambah);

        next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                selesai();
            }
        });

        tambah.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });
    }
    public void tambah(){
        Intent intent = new Intent(this,TambahBahan.class);
        //intent.putExtra();
        startActivity(intent);
    }
    public void selesai(){
        Intent intent = new Intent(this,InputActivity.class);
        //intent.putExtra();
        startActivity(intent);
    }
}
