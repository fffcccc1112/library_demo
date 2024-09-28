package com.library.libraryback.entity;

public class Book {
    private int bookid;
    private String title;
    private String author;
    private String cover;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return title;
    }

    public void setBookname(String bookname) {
        this.title = bookname;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookid=" + bookid +
                ", bookname='" + title + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
