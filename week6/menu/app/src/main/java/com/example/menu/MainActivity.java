package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Intent intent = new Intent(MainActivity.this,Context_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Context_menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.option_menu){
            Intent intent = new Intent(MainActivity.this,Option_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Option_menu", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.popup_menu) {
            Intent intent = new Intent(MainActivity.this,Popup_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Popup_menu", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);

    }
}