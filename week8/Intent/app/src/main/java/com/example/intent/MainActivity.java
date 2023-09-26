package com.example.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button ,btnweb;
    private TextView text1,text2,fullname;
    private EditText getname;

    private static final int MY_REQUEST_CODE = 2399;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        getname = findViewById(R.id.getname);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        btnweb = findViewById(R.id.btnweb);
        btnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, my_page.class);
                startActivity(intent);
            }
        });
    }

    public void sendMessage(){
        String fullname = getname.getText().toString();
        String message = "Hello, Please say hello me";
        Intent intent = new Intent(this,GreetingActivity.class);

        intent.putExtra("fullname",fullname);
        intent.putExtra("message",message);
        startActivityForResult(intent,MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE){
            String feedback = data.getStringExtra("feedback");
            text2.setText(feedback);
        }else {
            text2.setText("Error");
        }
    }
}