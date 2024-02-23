package com.gela.clipsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class searchActivity extends AppCompatActivity {

    WebView webView;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get a hold of main layout to get to the search box
        View mainLayout = getLayoutInflater().inflate(R.layout.activity_main, null);
        searchBox = mainLayout.findViewById(R.id.searchBox);

        webView = findViewById(R.id.searchWebView);
        webView.setWebViewClient(new WebViewClient());

        /// TODO Security concerns about malicious injections here so we should trim the input
        /// TODO What if whatever is in the clipboard is a media and not text? Ignore in that case?
        String query = searchBox.getText().toString().trim();
        if (!query.isEmpty()) {
            webView.loadUrl("https://www.duckduckgo.com/" + query);
        }
    }
}