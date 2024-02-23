package com.gela.clipsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class searchActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        webView = findViewById(R.id.searchWebView);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://www.google.com/");
    }
}