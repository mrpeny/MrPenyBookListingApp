package com.example.mrpeny.mrpenybooklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }

    public void searchBook(View view) {
        new BookAsyncTask().execute("https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1");
    }

    public void updateUi(String response) {
        TextView resultTextView = (TextView) findViewById(R.id.result_textview);
        resultTextView.setText(response);
    }


    class BookAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... requestUrl) {
            HttpHandler httpHandler = new HttpHandler();

            // TODO: Handle more arguments exception
            return httpHandler.fetchBookData(requestUrl[0]);
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            updateUi(jsonResponse);
        }
    }
}