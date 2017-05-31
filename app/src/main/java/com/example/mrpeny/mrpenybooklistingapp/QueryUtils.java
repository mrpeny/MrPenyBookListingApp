package com.example.mrpeny.mrpenybooklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrPeny on 2017. 05. 31..
 */

public class QueryUtils {
    private static final String TAG = QueryUtils.class.getSimpleName();

    public static List<Book> extractBooks(String jsonString) {
        List<Book> bookList = new ArrayList<>();

        try {
            JSONObject rootJson = new JSONObject(jsonString);
            JSONArray bookItems = rootJson.getJSONArray("items");
            for (int i = 0; i < bookItems.length(); i++) {
                JSONObject book = bookItems.getJSONObject(i);
                JSONObject bookInfo = book.getJSONObject("volumeInfo");
                String title = bookInfo.getString("title");
                List<String> authorList = new ArrayList<>();

                if (bookInfo.has("authors")) {
                    JSONArray authors = bookInfo.getJSONArray("authors");
                    for (int j = 0; j < authors.length(); j++) {
                        authorList.add(authors.getString(j));
                    }
                }
                bookList.add(new Book(title, authorList));
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON parsing error: " + e.getMessage());
        }

        return bookList;
    }
}
