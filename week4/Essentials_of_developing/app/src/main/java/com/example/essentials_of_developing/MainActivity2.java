package com.example.essentials_of_developing;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText name, phone;
    private RadioGroup radioGroup, radioGroup2;
    private LinearLayout linearLayout;
    private EditText textField;
    private RadioButton radioButton1,radioButton2;
    private Button exit,sms;

    private CheckBox Veggies,Mushroom,Pepperoni;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);

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
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText() !=  null
                        && phone.getText() != null
                        && radioGroup.isActivated()
                        && radioGroup2.isActivated()){

                    int select1 = radioGroup.getCheckedRadioButtonId();
                    int select2 = radioGroup2.getCheckedRadioButtonId();
                    radioButton1 = findViewById(select1);
                    radioButton2 = findViewById(select2);

                    if(Pepperoni.isChecked()){
                        result += Pepperoni.getText().toString();
                    }
                    if(Mushroom.isChecked()){
                        result += Mushroom.getText().toString();
                    }
                    if(Veggies.isChecked()){
                        result += Veggies.getText().toString();
                    }
                    textField.setText(radioButton1 +" with "+radioButton2 + " and " + result);

                }else{
                    return;
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