package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

public class Popup_menu extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(Popup_menu.this,button);
                popupMenu.getMenuInflater().inflate(R.menu.home_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.context_menu){
                            Intent intent = new Intent(Popup_menu.this,Context_menu.class);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Context_menu", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        else if(item.getItemId() == R.id.option_menu){
                            Intent intent = new Intent(Popup_menu.this,Option_menu.class);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Option_menu", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (item.getItemId() == R.id.popup_menu) {
                            Intent intent = new Intent(Popup_menu.this,Popup_menu.class);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Popup_menu", Toast.LENGTH_SHORT).show();

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }


}