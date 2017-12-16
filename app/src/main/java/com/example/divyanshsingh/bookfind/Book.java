package com.example.divyanshsingh.bookfind;

import java.io.Serializable;

/**
 * Created by Divyansh Singh on 14-12-2017.
 */

public class Book implements Serializable {
    private String bookName;
    private String bookAuthor;
    private String bookPrice;
    private String rating;
    private String publisher;
    private String publishedDate;
    private String discription;
    private String webPage;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public Book(String bookName, String bookAuthor, String bookPrice, String rating, String publisher, String publishedDate, String discription, String webPage) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.rating = rating;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.discription = discription;
        this.webPage = webPage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
