package com.library.libraryback.entity;


import java.time.LocalDateTime;

public class Book {
//    @TableId(type = IdType.AUTO)
    private int bookid;
    private String title;
    private String author;
    private String cover;
    private LocalDateTime outtime;
    private String borrow;
    private boolean exist;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

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

    public String getBorrow() {
        return borrow;
    }

    public void setBorrow(String borrow) {
        this.borrow = borrow;
    }

    public LocalDateTime getOuttime() {
        return outtime;
    }

    public void setOuttime(LocalDateTime outtime) {
        this.outtime = outtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookid=" + bookid +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", outtime=" + outtime +
                ", borrow='" + borrow + '\'' +
                ", exist=" + exist +
                '}';
    }
}
