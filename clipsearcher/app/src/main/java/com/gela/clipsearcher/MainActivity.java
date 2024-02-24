package com.gela.clipsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = findViewById(R.id.search_box);
        searchButton = findViewById(R.id.search_button);

        searchButton.setEnabled(false);

        // Get the content of the clipboard and pass it to the text field
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboard.getPrimaryClip();
        Log.d("ClipData", "Clipboard data: " + clipData);
        if (clipData != null && clipData.getItemCount() > 0) {
            CharSequence pasteData = clipData.getItemAt(0).getText();
            if (pasteData != null) {
                searchBox.setText(pasteData.toString());
            }
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchBox.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, searchActivity.class);
                // Pass the query to search activity so it can be appended to DDG url
                intent.putExtra("query", query);
                startActivity(intent);
            }
        });

        // Listener to disable and enable the search button depending on the text input
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence input, int start, int count, int after) {
                //  This is not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence input, int start, int before, int count) {
                // Check if EditText is empty or not
                // Enable the search button if EditText is not empty
                // Disable the search button if EditText is empty
                searchButton.setEnabled(input.toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable input) {
                // This is not needed for this implementation
            }
        });
    }
}