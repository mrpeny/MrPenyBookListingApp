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

    public void startVolumeSearch(View view) {
        String userQuery = ((EditText) findViewById(R.id.edit_text_view)).getText().toString();
        new BookAsyncTask().execute(userQuery);
    }

    public void updateUi(String response) {
        TextView resultTextView = (TextView) findViewById(R.id.result_textview);
        resultTextView.setText(response);
        QueryUtils.extractBooks(response);
    }

    class BookAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... queries) {
            HttpHandler httpHandler = new HttpHandler();

            // TODO: Handle more arguments exception
            return httpHandler.fetchBookData(queries[0]);
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            updateUi(jsonResponse);

        }
    }
}