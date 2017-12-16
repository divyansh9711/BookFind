package com.example.divyanshsingh.bookfind;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyansh Singh on 14-12-2017.
 */

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static String sampleurl = "https://www.googleapis.com/books/v1/volumes?q=alchemist&maxResults=6";
    public BookAdapter bookAdapter;
    public static  String url;

    private TextView  emptyView;
    private ProgressBar spinner ;

    private ConnectivityManager cm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);



        sampleurl = getIntent().getStringExtra("BOOK_NAME");
        ArrayList<Book> books = new ArrayList<>();
        ListView booksListView = (ListView) findViewById(R.id.list);
        bookAdapter = new BookAdapter(this, books);
        booksListView.setAdapter(bookAdapter);

        emptyView = (TextView) findViewById(R.id.empty_view);
        booksListView.setEmptyView(emptyView);
        spinner = (ProgressBar) findViewById(R.id.spin);
        spinner.setVisibility(View.VISIBLE);

        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this,sampleurl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        bookAdapter.clear();
        if(books != null && !books.isEmpty()){
            bookAdapter.addAll(books);
        }
        emptyView.setText("No books found ");
        spinner.setVisibility(View.INVISIBLE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(!isConnected){
            emptyView.setText("No internet connection");
        }
    }


    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapter.clear();
    }
}
