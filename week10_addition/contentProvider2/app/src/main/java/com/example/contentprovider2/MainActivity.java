package com.example.contentprovider2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button loadbtn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadbtn = findViewById(R.id.loadbtn);
        textView = findViewById(R.id.textView);
        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = getContentResolver().query(Uri.parse("content://com.user.contentprovider/user"),
                        null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    StringBuilder strBuild = new StringBuilder();
                    int nameColumnIndex = cursor.getColumnIndex("name"); // Get the index of the "name" column
                    do {
                        String name = cursor.getString(nameColumnIndex); // Retrieve the name value
                        strBuild.append("Name: ").append(name).append("\n");
                    } while (cursor.moveToNext());
                    cursor.close(); // Close the cursor when done
                    textView.setText(strBuild.toString());
                } else {
                    Toast.makeText(getBaseContext(), "No data was found", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}