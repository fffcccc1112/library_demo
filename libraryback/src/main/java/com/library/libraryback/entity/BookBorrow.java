package com.library.libraryback.entity;

public class BookBorrow {
    String username;
    int bookid;

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "BookBorrow{" +
                "bookid=" + bookid +
                ", username='" + username + '\'' +
                '}';
    }
}
