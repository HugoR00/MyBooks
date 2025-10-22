package com.example.mybooks.entity;

public class BookEntity {

    private Integer id;
    private String title;
    private String author;
    private boolean favorite;
    private String genre;

    public BookEntity(Integer id, String title, String author, boolean favorite, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.favorite = favorite;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
