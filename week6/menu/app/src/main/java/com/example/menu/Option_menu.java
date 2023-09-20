package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Option_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.context_menu){
            Intent intent = new Intent(Option_menu.this,Context_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Context_menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.option_menu){
            Intent intent = new Intent(Option_menu.this,Option_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Option_menu", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.popup_menu) {
            Intent intent = new Intent(Option_menu.this,Popup_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Popup_menu", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);

    }
}