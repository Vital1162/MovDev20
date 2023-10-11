package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    Button update,delete,insert,list;
    EditText editText;
    TextView textView;
    private static Uri newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

    }

    public void InsertData(View view){
        ContentValues contentValues = new ContentValues();
        String[] values = editText.getText().toString().split("\n");

        for(int i = 0;i<values.length;i++){
            contentValues.put("name",values[i]);
            newUser = getContentResolver().insert(MyContentProvider.CONTENT_URI,contentValues);
        }
        editText.setText("");
    }

    public void UpdateData(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update");


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(10, 10, 10, 10);
        final EditText editText_box = new EditText(this);
        editText_box.setHint("Enter");
        layout.addView(editText_box);
        builder.setView(layout);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nameToUpdate = editText.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.COLUMN_NAME, editText_box.getText().toString());

                String selection = DBHelper.COLUMN_NAME + "=?";
                String[] selectionArgs = { nameToUpdate };

                int rowsUpdated = getContentResolver().update(MyContentProvider.CONTENT_URI, contentValues, selection, selectionArgs);

                if (rowsUpdated > 0) {
                    Toast.makeText(getBaseContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "No matching data found to update", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            }
        });
        builder.show();
    }

    public void ListData(View view){
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null,
                null, null, null);

        StringBuilder namesStringBuilder = new StringBuilder(); // Create a StringBuilder to accumulate names

        String[] valuesFind = editText.getText().toString().split("\n");
        for(int i = 0;i<valuesFind.length;i++){
            if(namesStringBuilder.toString().contains(valuesFind[i])){
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range")
                        String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                        namesStringBuilder.append("Your Text: ").append(name).append("\n"); // Append each name to the StringBuilder
                    }
                    cursor.close();
                }
            }
        }

        editText.setText("");
        textView.setText(namesStringBuilder.toString());
    }

    public void DeleteData(View view) {

        String nameToDelete = editText.getText().toString();
        String[] dataTodelete = nameToDelete.split("\n");
        for(int i = 0;i<dataTodelete.length;i++){
            // Define the selection and selectionArgs to specify the record to delete.
            String selection = DBHelper.COLUMN_NAME + "=?";
            String[] selectionArgs = { dataTodelete[i] };

            int rowsDeleted = getContentResolver().delete(MyContentProvider.CONTENT_URI, selection, selectionArgs);

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No matching data found to delete", Toast.LENGTH_SHORT).show();
            }
        }

        editText.setText("");
    }
}