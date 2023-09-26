package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URL;

public class my_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);


        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/NormalEveryday"));
        startActivity(i);
//        Intent intent = getIntent();
//        Uri uri = intent.getData();
//        URL webURL = null;
//        if(uri != null){
//            try{
//                webURL = new URL(uri.getScheme(),uri.getHost(),uri.getPath());
//            }catch (Exception e){
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        WebView webView = findViewById(R.id.webview);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(webURL.toString());

    }
}