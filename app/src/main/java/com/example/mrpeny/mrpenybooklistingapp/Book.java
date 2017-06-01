package com.example.mrpeny.mrpenybooklistingapp;

import java.util.List;

/**
 * Created by MrPeny on 2017. 05. 31..
 */

public class Book {
    private String title;
    private List<String> authors;

    public Book(String title, List<String> authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
