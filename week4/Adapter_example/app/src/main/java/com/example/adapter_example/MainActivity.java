package com.example.adapter_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //giá trị tham chiếu
    String[] toDo = {"Code","Eat","Sleep","Learn"};
    Spinner spinner;
    TextView select ,selectgird;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_item,toDo);
        ListView listView = findViewById(R.id.todo_list);
        listView.setAdapter(adapter);

        //Spinner
        spinner = findViewById(R.id.spinner);
        select = findViewById(R.id.selection);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select.setText(toDo[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                select.setText("");
            }
        });

        adapter.setDropDownViewResource(R.layout.dropdown);
        spinner.setAdapter(adapter);

        //GridView
        selectgird = findViewById(R.id.selection);
        GridView gridView = findViewById(R.id.grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectgird.setText(toDo[i]);
            }
        });

        //AutoCompleteTextView
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoComplete);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                select.setText(autoCompleteTextView.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}