package com.example.donation_fix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Constraint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks here
        int id = item.getItemId();
        // Perform action based on item ID
        if(id ==  R.id.constraint){
            Intent intent = new Intent(this, Constraint.class);
            startActivity(intent);
            Toast.makeText(this, "ConstraintLayout clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.table) {
            Intent intent = new Intent(this, Table.class);
            startActivity(intent);
            Toast.makeText(this, "TableLayout clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.relative) {
            Intent intent = new Intent(this, Relative.class);
            startActivity(intent);
            Toast.makeText(this, "RelativeLayout clicked", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.linear) {
            Intent intent = new Intent(this, Linear.class);
            startActivity(intent);
            Toast.makeText(this, "LinearLayout clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}