package com.example.mrpeny.mrpenybooklistingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {
    private static final String LOG_TAG = BookActivity.class.getSimpleName();

    private static final String KEYWORDS_KEY = "KEYWORDS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }

    public void onSearchButtonClick(View view) {
        //Toast.makeText(this, R.string.enter_keywords,Toast.LENGTH_SHORT).show();
        String userKeywords = ((EditText) findViewById(R.id.edit_text_view)).getText().toString();

        if (userKeywords.isEmpty()) {
            Toast.makeText(this, R.string.enter_keywords,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Keywords entered",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BookListActivity.class);
            intent.putExtra(KEYWORDS_KEY, userKeywords);
            startActivity(intent);
        }

    }
}