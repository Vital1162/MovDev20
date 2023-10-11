package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button save, apply, saveis;
    Switch aSwitch;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apply = findViewById(R.id.apply);
        save = findViewById(R.id.save);
        editText = findViewById(R.id.edit_text);
        aSwitch = findViewById(R.id.switch_btn);
        textView = findViewById(R.id.textView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set textView to editView values
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

//        saveis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"");
//        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
        textView.setText(text);
        aSwitch.setChecked(switchOnOff);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT,textView.getText().toString());
//        editor.putBoolean(SWITCH1,aSwitch.isChecked());

        editor.apply();
        Toast.makeText(this, "Saving data and operation success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.sharedPreferences){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.internalStorage){
            Intent intent = new Intent(this,InternalStorage.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.externalStorage) {
            Intent intent = new Intent(this,ExternalStorage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}