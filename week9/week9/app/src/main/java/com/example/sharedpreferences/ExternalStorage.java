package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
public class ExternalStorage extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 100, STORAGE_REQUEST = 200,
                IMAGEPICK_GALLERY_REQUEST = 300, IMAGE_PICKCAMERA_REQUEST = 400;
    Button camera, storage;
    Uri imageuri;
    ImageView img;
    String[] cameraPermission, storagePermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cameraPermission = new String[]
                {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]
                {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        storage = findViewById(R.id.storage);
        camera = findViewById(R.id.camera);
        img = findViewById(R.id.imageView);
        img.setVisibility(View.VISIBLE);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkCamreraPermission()){
                    requestCameraPermission();
                }else {
                    pickFromCamera();
                }
            }
        });
        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkStoragePermission()){
                    requestStoragePermission();
                }else{
                    pickFromGallery();
                }
            }
        });

    }
    public Boolean checkCamreraPermission(){
        return ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                ==(PackageManager.PERMISSION_GRANTED);
    }

    private Boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_DENIED);

    }
    private void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(cameraPermission, CAMERA_REQUEST);
        }
    }
    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(storagePermission, STORAGE_REQUEST);
        }
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST:
                if(grantResults.length > 0){
                    boolean camera_accepted =grantResults[0] ==PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted =grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(camera_accepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this, "Enable Camera Permission", Toast.LENGTH_SHORT).show();
                    }
                }
            break;
            case STORAGE_REQUEST:
                if(grantResults.length>0){
                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(writeStorageaccepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Enable Storage Permission", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
    private void pickFromCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description");
        imageuri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent camerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camerIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        startActivityForResult(camerIntent, IMAGE_PICKCAMERA_REQUEST);

    }
    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGEPICK_GALLERY_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == IMAGE_PICKCAMERA_REQUEST){
                uploadProfileCoverPhoto(imageuri);
            }if(requestCode == IMAGEPICK_GALLERY_REQUEST){
                if (data != null) {
                    imageuri = data.getData();
                    uploadProfileCoverPhoto(imageuri);
                }
            }

        }
    }

    public void uploadProfileCoverPhoto(final Uri uri){
        img.setImageURI(uri);
    }

}