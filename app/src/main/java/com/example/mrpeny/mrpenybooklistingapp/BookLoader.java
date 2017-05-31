package com.example.mrpeny.mrpenybooklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrPeny on 2017. 05. 31..
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String query;

    public BookLoader(Context context, String query) {
        super(context);
        this.query = query;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> bookList;

        String jsonResponse = HttpHandler.fetchBookData(query);

        // TODO: Handle more arguments exception
        bookList = QueryUtils.extractBooks(jsonResponse);

        return bookList;
    }
}
