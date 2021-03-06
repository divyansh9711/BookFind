package com.example.divyanshsingh.bookfind;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Divyansh Singh on 16-12-2017.
 */

public class BookDescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        Intent intent = getIntent();
        final Book book = (Book) intent.getSerializableExtra("BOOK");

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(book.getBookName());

        TextView authorName = (TextView) findViewById(R.id.aurhor_name);
        authorName.setText(book.getBookAuthor());

        TextView publisher = (TextView) findViewById(R.id.publisher);
        publisher.setText(book.getPublisher());

        TextView publishDate = (TextView) findViewById(R.id.publish_date);
        publishDate.setText(book.getPublishedDate());

        TextView rating = (TextView) findViewById(R.id.rating);
        rating.setText(book.getRating());

        TextView price = (TextView) findViewById(R.id.price);
        price.setText(book.getBookPrice());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(book.getDiscription());

        Button info = (Button) findViewById(R.id.info_link);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getWebPage()));
                startActivity(intent1);
            }
        });

    }
}
