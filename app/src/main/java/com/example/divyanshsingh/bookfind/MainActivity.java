package com.example.divyanshsingh.bookfind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button search;
    public static final String sampleurl = "https://www.googleapis.com/books/v1/volumes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =  (EditText) findViewById(R.id.book_title);
        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                String bookName = editText.getText().toString();
                String url = buildUri(bookName);
                intent.putExtra("BOOK_NAME",url);
                editText.setText(null);
                startActivity(intent);
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handle = false;
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    Intent intent = new Intent(MainActivity.this, BookActivity.class);
                    String bookName = editText.getText().toString();
                    String url = buildUri(bookName);
                    intent.putExtra("BOOK_NAME",url);
                    editText.setText(null);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
    private String buildUri(String bookName){
        Uri baseUri =  Uri.parse(sampleurl);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q",bookName);
        uriBuilder.appendQueryParameter("maxResults","20");
        return uriBuilder.toString();
    }
}
