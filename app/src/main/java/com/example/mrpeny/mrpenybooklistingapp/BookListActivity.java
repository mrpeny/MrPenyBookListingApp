package com.example.mrpeny.mrpenybooklistingapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages fetching data from Google API with Loader, populates RecyclerView with results
 * checks NetworkInfo
 */

public class BookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{
    private static final String LOG_TAG = BookListActivity.class.getSimpleName();
    private static final int BOOKLOADER_ID = 0;
    private static final String KEYWORDS_KEY = "KEYWORDS";
    String keywords;
    private List<Book> bookList = new ArrayList<>();
    private List<Book> bookListStorage = new ArrayList<>();
    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;
    private TextView emptyStateTextView;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Intent intent = getIntent();
        // Extracting keywords user typed in
        keywords = intent.getStringExtra(KEYWORDS_KEY);

        // Setting up RecyclerView and its LayoutManager and Adapter
        bookRecyclerView = (RecyclerView) findViewById(R.id.book_recycler_view);
        LinearLayoutManager bookLayoutManager = new LinearLayoutManager(this);
        bookRecyclerView.setLayoutManager(bookLayoutManager);
        bookAdapter = new BookAdapter(bookList);
        bookRecyclerView.setAdapter(bookAdapter);
        bookRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        emptyStateTextView = (TextView) findViewById(R.id.empty_text_view);
        progressBar = findViewById(R.id.progress_bar);

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Checking Internet connection and handling cases
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            // Starting Loader thread for network and parsing
            loaderManager.initLoader(BOOKLOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.internet_connection_error);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, this.keywords);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> newBookList) {
        progressBar.setVisibility(View.GONE);
        bookAdapter.setBookList(null);

        if (newBookList != null && !newBookList.isEmpty()) {
            // If there is new data then update the list
            bookAdapter.setBookList(newBookList);
            bookAdapter.notifyDataSetChanged();
            bookListStorage = new ArrayList<>(newBookList);
        } else {
            emptyStateTextView.setText(getString(R.string.no_books_error, keywords));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapter.setBookList(null);
    }
}
