package com.example.andre.firebasecweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.shaded.fasterxml.jackson.databind.node.IntNode;

public class BelanjaActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.andre.firebasecweather";
    private Firebase mRef;
    Resep data;
    String item;
    Button done;
    ListView listView;
    String[] listBahan;
    String test;
    Bahan[] lb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        done = (Button)findViewById(R.id.done);
        item = intent.getStringExtra("judul");
        listBahan = intent.getStringArrayExtra("listbahan");
        lb = new Bahan[listBahan.length];

        for(int i=0;i<listBahan.length;i++){
            lb[i] = new Bahan(listBahan[i],0);
        }
        done.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                tohome();
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        listView = (ListView)findViewById(R.id.listView);
        BahanListAdapter adapter = new BahanListAdapter(this, lb);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.bahan_list,R.id.textView,listBahan);
        listView.setAdapter(adapter);

    }
    public void tohome(){
        Intent intent = new Intent(this,ShowFullResep.class);
        intent.putExtra("judul",item);
        startActivity(intent);
    }

}
