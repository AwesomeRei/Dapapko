package com.example.andre.firebasecweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MasakActivity extends AppCompatActivity {
    Intent intent;
    private String judul;
    private String[] stepMasak;
    private int urutan;
    TextView step;
    Button next;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        judul = intent.getStringExtra("judul");
        stepMasak =  intent.getStringArrayExtra("stepmasak");
        urutan = intent.getIntExtra("urutan", 0);

        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.back);
        /*
        if (stepMasak[urutan+1] == "null"){
            next.setVisibility(View.GONE);
        }
        else if (stepMasak[urutan+1] != "null"){
            next.setVisibility(View.VISIBLE);
        }
        else if( urutan == 0){
            System.out.println();
            back.setVisibility(View.GONE);
        }else if( urutan != 0){
            back.setVisibility(View.VISIBLE);
        }*/
        next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });

        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        step = (TextView) findViewById(R.id.step);
        step.setText(stepMasak[urutan]);
    }

    public void next(View view){
        Intent nextStep;
        if(urutan == stepMasak.length-1){
            nextStep = new Intent(this,ShowFullResep.class);
            urutan = 0;
        }
        else {
            nextStep = new Intent(this,MasakActivity.class);
            urutan +=1;
        }
        nextStep.putExtra("judul",judul);
        nextStep.putExtra("stepmasak", stepMasak);
        nextStep.putExtra("urutan", urutan);
        startActivity(nextStep);
    }

    public  void back(View view){
        Intent back;
        if(urutan == 0){
            back = new Intent(this,ShowFullResep.class);
            urutan = 0;
        }
        else{
            back = new Intent(this,MasakActivity.class);
            urutan -=1;
        }
        back.putExtra("judul",judul);
        back.putExtra("stepmasak",stepMasak);
        back.putExtra("urutan", urutan);
        startActivity(back);
    }

}
