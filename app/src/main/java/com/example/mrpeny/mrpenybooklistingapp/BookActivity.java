package com.example.mrpeny.mrpenybooklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {
    private static final String KEYWORDS_KEY = "KEYWORDS";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        editText = ((EditText) findViewById(R.id.edit_text_view));

        // Adding EditorActionListener to EditText so that user can press search on keyboard
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                    performSearch();
                return true;
            }
        });
    }

    /* Triggered by Search Button click */
    public void onSearchButtonClick(View view) {
        performSearch();
    }

    /* Triggers search logic by starting BookListActivity intent with the user's keywords */
    private void performSearch() {
        String userKeywords = editText.getText().toString();

        if (userKeywords.isEmpty()) {
            Toast toast = Toast.makeText(this, R.string.enter_keywords, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Intent intent = new Intent(this, BookListActivity.class);
            intent.putExtra(KEYWORDS_KEY, userKeywords);
            startActivity(intent);
        }
    }
}