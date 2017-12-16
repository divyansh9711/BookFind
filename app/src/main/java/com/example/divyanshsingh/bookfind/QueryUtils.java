package com.example.divyanshsingh.bookfind;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Divyansh Singh on 14-12-2017.
 */

public class QueryUtils {

    private QueryUtils() {
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("LOG_TAG", "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000 /* milliseconds */);
            urlConnection.setConnectTimeout(5000 /* milliseconds */);

            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else {
                Log.e("LOG_TAG", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("LOG_TAG", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Book> extractFromJson(String urlRequest){

        if(TextUtils.isEmpty(urlRequest)){
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();
        URL url = createUrl(urlRequest);
        String jsonResponse = null;

        try{
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            JSONObject report = new JSONObject(jsonResponse);
            JSONArray totalBooks = report.getJSONArray("items");
            String rating = null;
            for(int i = 0 ; i < totalBooks.length(); i++){
                int temp123 = totalBooks.length();
                JSONObject book = totalBooks.getJSONObject(i);
                JSONObject properties = book.getJSONObject("volumeInfo");
                String title = properties.getString("title");
                JSONArray authors = properties.getJSONArray("authors");
                String author = authors.getString(0);
                String price ;
                try {
                     rating = properties.getString("averageRating");
                }
                catch (JSONException e){
                    e.printStackTrace();
                    rating = "N/A";
                }

                JSONObject info = book.getJSONObject("saleInfo");
                String available = info.getString("saleability");
                if(available.equals("FOR_SALE")){
                    JSONObject prices = info.getJSONObject("listPrice");
                    price = prices.getString("amount") + " " + prices.getString("currencyCode");
                }
                else{
                    price = "N/A";
                }

                Book temp = new Book(title,author,price,rating);
                books.add(temp);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return books;
    }


}

