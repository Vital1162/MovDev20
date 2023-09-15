package com.example.essentials_of_developing_fix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    public  EditText name, phone;
    public  RadioGroup radioGroup, radioGroup2;
    public  LinearLayout linearLayout;
    public  TextView textField;
    public  RadioButton radioButton1,radioButton2;
    public  Button exit,sms;
    public  CheckBox Veggies,Mushroom,Pepperoni;
    static String result ="";

    FrameLayout frameConstraint,frameTable,frameRelative;

    public void setInvisible(){
        frameConstraint.setVisibility(View.INVISIBLE);
        frameTable.setVisibility(View.INVISIBLE);
        frameRelative.setVisibility(View.INVISIBLE);
    }
    @Override
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
            setInvisible();
            frameConstraint.setVisibility(View.VISIBLE);
            Toast.makeText(this, "ConstraintLayout clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.table) {
            setInvisible();
            frameTable.setVisibility(View.VISIBLE);
            Toast.makeText(this, "TableLayout clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.frameRelative) {
            setInvisible();
            frameRelative.setVisibility(View.VISIBLE);
            Toast.makeText(this, "RelativeLayout clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        Pepperoni = findViewById(R.id.Pepperoni);
        Mushroom = findViewById(R.id.Mushroom);
        Veggies = findViewById(R.id.Veggies);
        exit = findViewById(R.id.exit);
        sms = findViewById(R.id.sms);
        textField = findViewById(R.id.textField);
        frameConstraint = findViewById(R.id.frameConstraint);
        frameTable = findViewById(R.id.frameTable);
        frameRelative = findViewById(R.id.frameRelative);
        frameConstraint.setVisibility(View.VISIBLE);
        frameTable.setVisibility(View.INVISIBLE);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name !=  null
                        && phone != null &&
                        radioGroup.getCheckedRadioButtonId() != -1&&
                        radioGroup2.getCheckedRadioButtonId() != -1 ){

                    int select1 = radioGroup.getCheckedRadioButtonId();
                    int select2 = radioGroup2.getCheckedRadioButtonId();
                    radioButton1 = findViewById(select1);
                    radioButton2 = findViewById(select2);
                    result += radioButton1.getText().toString() + " and " + radioButton2.getText().toString() +" with ";
                    if(Pepperoni.isChecked()){
                        result += Pepperoni.getText().toString()+" ";
                    }
                    if(Mushroom.isChecked()){
                        result += Mushroom.getText().toString()+" ";
                    }
                    if(Veggies.isChecked()){
                        result += Veggies.getText().toString()+" ";
                    }
                    textField.setText(result +" for " +name.getText().toString());
                }else{
                    Toast.makeText(getBaseContext(),"Make sure fill the form",Toast.LENGTH_LONG).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }
}