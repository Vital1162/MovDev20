package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Name,Class,Code;
    Button update,delete,list,insert;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.Name);
        Class = findViewById(R.id.Name);
        Code = findViewById(R.id.Code);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        list = findViewById(R.id.list);
        insert = findViewById(R.id.insert);
        listView = findViewById(R.id.listview);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        sqLiteDatabase = openOrCreateDatabase("manager.db",MODE_PRIVATE,null);
        try{
            String sql = "CREATE TABLE infor_tbl(name TEXT,code TEXT primary key,class TEXT)";
            sqLiteDatabase.execSQL(sql);
        }catch (Exception e){

        }
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String code = Code.getText().toString();
                String myclass = Class.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("code",code);
                values.put("class",myclass);
                String msg = "";
                if(sqLiteDatabase.insert("infor_tbl",null,values)==-1){
                    msg = "Fail to Insert";
                }else {
                    msg ="Insert successfully";
                }
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = Code.getText().toString();
                int n = sqLiteDatabase.delete("infor_tbl","code = ?",new String[]{code});
                String msg = "";
                if(n==0){
                    msg = "Nothing to delete";
                }
                else{
                    msg = n + " is delete";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = Code.getText().toString();
                String myclass = Class.getText().toString();
                String name = Name.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",name);
                contentValues.put("class",myclass);
                int n = sqLiteDatabase.update("infor_tbl",contentValues,"code = ?",new String[]{code});
                String msg = "";
                if(n==0){
                    msg = "Nothing to update";
                }
                else{
                    msg = n+ " is updated";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();


            }

        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                Cursor cursor = sqLiteDatabase.query("infor_tbl",
                        null,null, null,
                        null,null,null);
                cursor.moveToNext();
                String data = "";
                while (cursor.isAfterLast()==false){
                    data = cursor.getString(0) + "-" + cursor.getString(1)
                    +"-"+cursor.getString(2);
                    cursor.moveToNext();
                    arrayList.add(data);
                }
                cursor.close();
                arrayAdapter.notifyDataSetChanged();

            }
        });

    }
}