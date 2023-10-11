package com.example.serivice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button start,end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startButton);
        end = findViewById(R.id.stopButton);
        start.setOnClickListener( this );
        end.setOnClickListener( this );
    }


    @Override
    public void onClick(View view) {
        if (view == start){
            startService(new Intent(this,MyService.class));
            Toast.makeText(this,"Start counting",Toast.LENGTH_SHORT).show();
        }else{
            stopService(new Intent(this,MyService.class));
            Toast.makeText(this,"End count",Toast.LENGTH_SHORT).show();
        }
    }
}