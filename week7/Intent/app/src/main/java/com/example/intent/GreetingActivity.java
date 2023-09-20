package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {
    private Button button;
    private TextView text1,text2;
    String fullname;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        text2 = findViewById(R.id.text2);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = this.getIntent();
        fullname = intent.getStringExtra("fullname");
        message = intent.getStringExtra("message");
        text2.setText(message);

    }

    @Override
    public void finish() {
        Intent data = new Intent();
        String feedback = "OK,Hello " + this.fullname +" .How are you?";
        data.putExtra("feedback",feedback);
        setResult(Activity.RESULT_OK,data);
        super.finish();
    }
}