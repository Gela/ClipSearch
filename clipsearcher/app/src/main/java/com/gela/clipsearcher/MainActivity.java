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

        // The search button is disabled by default.
        // It is only enabled if there is something in the clipboard
        // or if the user types something in the search box.
        searchButton.setEnabled(false);

        // Check the clipboard to see if there is anything there
        // and paste it in the input field.
        // If the clipboard is empty, we do nothing.
        setTextFromClipboard();

        setSearchButtonClickListener();

        setSearchBoxTextWatcher();
    }

    /**
     * This method does not accept or return any values.
     * It simply exists to abstract away the clipboard copy-paste logic.
     * In this function we check to see if there is anything stored in the
     * clipboard, and if that's the case, paste that value in the search field.
     *
     * Note: This does not currently work as intended.
     */
    private void setTextFromClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData != null && clipData.getItemCount() > 0) {
            CharSequence pasteData = clipData.getItemAt(0).getText();
            if (pasteData != null) {
                searchBox.setText(pasteData.toString());
            }
        }
    }

    /**
     * This method does not accept or return any values.
     * It simply exists to abstract away logic which awaits the user clicking
     * on the Search button.
     * As soon as the user clicks on search button, we create a new Intent which passes the query
     * aka the input string to the SearchActivity class to perform a search on DuckDuckGo.
     *
     * I opted to trim the input to remove any white spaces, another consideration here
     * would be to parse and trim the input for any potentially malicious code which could
     * be injected this way. However, because we are simply passing this to DDG which I'm fairly
     * positive already has a process to sanitize input first, so anything beyond removing the white space
     * is likely redundant.
     */
    private void setSearchButtonClickListener() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchBox.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, searchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
            }
        });
    }

    /**
     * This method does not accept or return any values.
     * It simply exists to abstract away logic which awaits the user entering any
     * input in the search field to enable the search button.
     *
     * This is a dynamic function and if the input field becomes empty at any point,
     * we will deactivate the search button again.
     */
    private void setSearchBoxTextWatcher() {
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence input, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence input, int start, int before, int count) {
                searchButton.setEnabled(input.toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable input) {
                // Not needed for this implementation
            }
        });
    }
}