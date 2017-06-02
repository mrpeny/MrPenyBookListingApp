package com.example.mrpeny.mrpenybooklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for parsing JSON object from string
 */

class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    /**
     * Parses a JSON String to a List of Books
     *
     * @param jsonString the JSON object in String
     * @return a List of Book objects parsed from the jsonString
     */
    static List<Book> extractBooks(String jsonString) {
        List<Book> bookList = new ArrayList<>();

        try {
            JSONObject rootJson = new JSONObject(jsonString);
            JSONArray bookItems = rootJson.getJSONArray("items");
            for (int i = 0; i < bookItems.length(); i++) {
                JSONObject book = bookItems.getJSONObject(i);
                JSONObject bookInfo = book.getJSONObject("volumeInfo");

                // Extracting title from the book info
                String title = bookInfo.getString("title");

                List<String> authorList = new ArrayList<>();
                // Extracting authors from the book info if any
                if (bookInfo.has("authors")) {
                    JSONArray authors = bookInfo.getJSONArray("authors");
                    for (int j = 0; j < authors.length(); j++) {
                        authorList.add(authors.getString(j));
                    }
                }

                // Extracting infoLink
                String infoLink = bookInfo.getString("infoLink");
                bookList.add(new Book(title, authorList, infoLink));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON parsing error: " + e.getMessage());
        }
        return bookList;
    }
}
