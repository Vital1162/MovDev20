package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Context_menu extends AppCompatActivity {
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view = (TextView) findViewById(R.id.textView);
        registerForContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose the menu you want");
        menu.add(0,v.getId(),0,"Option");
        menu.add(0, v.getId(), 0, "Context");
        menu.add(0, v.getId(), 0, "Popup");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Context")) {
            // Handle selection for "Context" option
            startActivity(new Intent(Context_menu.this, Context_menu.class));
            Toast.makeText(this, "Context_menu", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getTitle().equals("Option")) {
            // Handle selection for "Option" option
            startActivity(new Intent(Context_menu.this, Option_menu.class));
            Toast.makeText(this, "Option_menu", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getTitle().equals("Popup")) {
            // Handle selection for "Popup" option
            startActivity(new Intent(Context_menu.this, Popup_menu.class));
            Toast.makeText(this, "Popup_menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.context_menu){
            Intent intent = new Intent(Context_menu.this,Context_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Context_menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.option_menu){
            Intent intent = new Intent(Context_menu.this,Option_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Option_menu", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.popup_menu) {
            Intent intent = new Intent(Context_menu.this,Popup_menu.class);
            startActivity(intent);
            Toast.makeText(this, "Popup_menu", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);

    }

}