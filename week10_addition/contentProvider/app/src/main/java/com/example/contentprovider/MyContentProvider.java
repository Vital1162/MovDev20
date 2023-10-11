package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MyContentProvider extends ContentProvider {
    private static final String AUTH = "com.user.contentprovider";
    private static final String PATH = "user";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTH + "/" + PATH);


    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DBHelper dbHelper = new DBHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings,
                        @Nullable String s, @Nullable String[] strings1,
                        @Nullable String s1) {

        return database.query(DBHelper.TABLE_USERS,strings,s,strings1,null,null,s1);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id = database.insert(DBHelper.TABLE_USERS, null, contentValues);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(PATH + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int rowsDeleted = database.delete(DBHelper.TABLE_USERS, s, strings);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int rowsUpdate = database.update(DBHelper.TABLE_USERS,contentValues,s,strings);
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdate;
    }


}
