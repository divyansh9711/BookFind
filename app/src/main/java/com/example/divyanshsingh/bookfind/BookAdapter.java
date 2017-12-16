package com.example.divyanshsingh.bookfind;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Divyansh Singh on 14-12-2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context,@NonNull List<Book> resource) {
        super(context, 0,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_pattern,parent,false);

        }

        Book currentBook = getItem(position);

        TextView bookName = (TextView) listItemView.findViewById(R.id.title);
        bookName.setText(currentBook.getBookName());

        TextView bookRating = (TextView) listItemView.findViewById(R.id.rating);
        bookRating.setText(currentBook.getRating());

        TextView bookPrice = (TextView) listItemView.findViewById(R.id.price);
        bookPrice.setText(currentBook.getBookPrice());

        TextView author = (TextView) listItemView.findViewById(R.id.author_name);
        author.setText(currentBook.getBookAuthor());

        return listItemView;
    }
}
