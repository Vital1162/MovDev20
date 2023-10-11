package com.example.contentprovider;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        return true;
//    }
    public void onClickAddDetails(View view) {

        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        values.put(MyContentProvider.name, ((EditText) findViewById(R.id.textName)).getText().toString());
        // inserting into database through content URI
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        // displaying a toast message
        Toast.makeText(getBaseContext(), "Insert new data", Toast.LENGTH_LONG).show();
    }

    public void onClickDelete(View view) {
        // Replace 'recordIdToDelete' with the actual ID of the record you want to delete
        long recordIdToDelete = 1; // Change this to the ID you want to delete

        Uri recordUri = Uri.withAppendedPath(MyContentProvider.CONTENT_URI, String.valueOf(recordIdToDelete));

        int rowsDeleted = getContentResolver().delete(recordUri, null, null);

        if (rowsDeleted > 0) {
            Toast.makeText(getBaseContext(), "Record Deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "No Record Deleted", Toast.LENGTH_LONG).show();
        }


    }

    @SuppressLint("Range")
    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView= (TextView) findViewById(R.id.res);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "."+ cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText(null);
            Toast.makeText(this,"No data was found",Toast.LENGTH_LONG).show();
        }
    }
}
