package com.example.mrpeny.mrpenybooklistingapp;

import java.util.List;

/**
 * Created by MrPeny on 2017. 05. 31..
 */

public class Book {
    private String bookTitle;
    private List<String> authors;

    public Book(String bookTitle, List<String> authors) {
        this.bookTitle = bookTitle;
        this.authors = authors;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
