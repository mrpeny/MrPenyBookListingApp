package com.example.mrpeny.mrpenybooklistingapp;

import android.app.LoaderManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }

    public void startVolumeSearch(View view) {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public android.content.Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        String userQuery = ((EditText) findViewById(R.id.edit_text_view)).getText().toString();
        return new BookLoader(this, userQuery);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Book>> loader, List<Book> data) {
        TextView resultTextView = (TextView) findViewById(R.id.result_textview);
        StringBuilder stringBuilder = new StringBuilder();

        for (Book book : data) {
            stringBuilder.append(book.getBookTitle()).append("\n");
        }

        resultTextView.setText(stringBuilder.toString());
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Book>> loader) {

    }
}