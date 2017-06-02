package com.example.mrpeny.mrpenybooklistingapp;

import java.util.List;

/**
 * Contains a representation of Google Volume, Book
 */

public class Book {
    private String title;
    private List<String> authors;
    private String infoLink;

    public Book(String title, List<String> authors, String infoLink) {
        this.title = title;
        this.authors = authors;
        this.infoLink = infoLink;
    }

    String getTitle() {
        return title;
    }

    List<String> getAuthors() {
        return authors;
    }

    String getInfoLink() {
        return infoLink;
    }
}
