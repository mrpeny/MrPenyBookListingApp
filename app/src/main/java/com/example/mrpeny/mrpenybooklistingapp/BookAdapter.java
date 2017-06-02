package com.example.mrpeny.mrpenybooklistingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter used to create and hold recyclable List Item Views
 */

class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> bookList;


    BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        final BookAdapter.ViewHolder viewHolder = new BookAdapter.ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bookItemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
                String bookInfoLink = bookList.get(viewHolder.getAdapterPosition()).getInfoLink();
                Uri bookInfoUri;
                bookInfoUri = Uri.parse(bookInfoLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, bookInfoUri);
                if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    v.getContext().startActivity(intent);
                }

            }
        });
        return viewHolder;
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
        // if there are no authors associated with the Book
        if (authorsList.isEmpty()) {
            authors.append("No author");
        } else {
            // Get the first author from the list
            authors.append(authorsList.get(0));
            // Appending the rest of authors to the first author from the list
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

    void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorsTextView;

        ViewHolder(View itemView) {
            super(itemView);
            this.titleTextView = (TextView) itemView.findViewById(R.id.book_title);
            this.authorsTextView = (TextView) itemView.findViewById(R.id.book_authors);
        }
    }
}
