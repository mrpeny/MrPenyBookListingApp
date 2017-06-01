package com.example.mrpeny.mrpenybooklistingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MrPeny on 2017. 05. 31..
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleTextView = (TextView) itemView.findViewById(R.id.book_title);
            this.authorsTextView = (TextView) itemView.findViewById(R.id.book_authors);
        }
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new BookAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder viewHolder, int position) {
        if (bookList == null) {
            return;
        }
        Book book = bookList.get(position);

        viewHolder.titleTextView.setText(book.getTitle());

        List<String> authorsList = book.getAuthors();
        StringBuilder authors = new StringBuilder();
        if (authorsList.isEmpty()) {
            authors.append("No author");
        } else {
            authors.append(authorsList.get(0));
            for (int i = 1; i < authorsList.size(); i++) {
                authors.append(", ").append(authorsList.get(i));
            }
        }
        viewHolder.authorsTextView.setText(authors);
    }

    @Override
    public int getItemCount() {
        if (bookList == null) {
            return 0;
        }
        return bookList.size();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
