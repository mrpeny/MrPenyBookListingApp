package com.example.mrpeny.mrpenybooklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Responsible for background thread, network an parsing operations
 */

class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String query;

    BookLoader(Context context, String query) {
        super(context);
        this.query = query;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        // If there is now query to fetch then return null
        if (query == null) {
            return null;
        }

        String jsonResponse = HttpHandler.fetchBookData(query);
        List<Book> bookList = QueryUtils.extractBooks(jsonResponse);

        return bookList;
    }
}
