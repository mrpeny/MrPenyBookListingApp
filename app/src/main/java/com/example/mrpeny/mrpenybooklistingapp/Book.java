package com.example.mrpeny.mrpenybooklistingapp;

import java.util.List;

/**
 * Contains a representation of Google Volume, Book
 */

public class Book {
    private String title;
    private List<String> authors;

    public Book(String title, List<String> authors) {
        this.title = title;
        this.authors = authors;
    }

    String getTitle() {
        return title;
    }

    List<String> getAuthors() {
        return authors;
    }
}
