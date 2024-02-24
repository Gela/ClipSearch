package com.gela.clipsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class searchActivity extends AppCompatActivity {

    WebView webView;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeViews();
        loadSearchResults();
    }

    private void initializeViews() {
        webView = findViewById(R.id.searchWebView);
        webView.setWebViewClient(new WebViewClient());
    }

    /**
     * This method exists to abstract away logic which receives the
     * input string from the main activity and appends that to the DDG URL
     * and then loads the URL to show the search results.
     */
    private void loadSearchResults() {
        String query = getIntent().getStringExtra("query");
        if (query != null && !query.isEmpty()) {
            String searchUrl = "https://www.duckduckgo.com/?q=" + query;
            webView.loadUrl(searchUrl);
        }
    }
}