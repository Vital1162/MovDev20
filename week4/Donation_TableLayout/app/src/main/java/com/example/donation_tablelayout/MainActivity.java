package com.example.donation_tablelayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private EditText amountText;
    private TextView amountTotal;

    private int TotalDonate = 0;
    private boolean targetAchieved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // to object
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        donateButton = findViewById(R.id.donateButton);
        paymentMethod = findViewById(R.id.paymentMethod);
        progressBar = findViewById(R.id.progressBar);
        amountPicker = findViewById(R.id.amountPicker);
        amountText = findViewById(R.id.paymentAmount);
        amountTotal = findViewById(R.id.totalSoFar);
        //set up to NumberPick
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
        amountTotal.setText("$0");

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String method = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ? "PayPal" : "Direct";

                int donatedAmount =  amountPicker.getValue();
                if (donatedAmount == 0)
                {
                    String text = amountText.getText().toString();
                    if (!text.equals(""))
                        donatedAmount = Integer.parseInt(text);
                }

                if (TotalDonate < 10000)
                {
                    TotalDonate  = TotalDonate + donatedAmount;
                    progressBar.setProgress(TotalDonate);
                    amountTotal.setText("$" + TotalDonate);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Enough money", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}