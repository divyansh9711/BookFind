package com.example.divyanshsingh.bookfind;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Divyansh Singh on 14-12-2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {


    private static final String LOG_TAG = BookLoader.class.getName();
    private String mUrl;
    public BookLoader(Context context,String murl){
        super(context);
        this.mUrl = murl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Book> books = QueryUtils.extractFromJson(mUrl);
        return books;
    }
}
