package com.springbatch.app.batchImporter;

public class BookRecord {
    private int bookId;
    private String title;
    private String author;
    private double rating;
    private String description;
    private String language;
    private String isbn;
    private String book_format;
    private String edition;
    private int pages;
    private String publisher;
    private String publish_date;
    private String first_publish_date;
    private double liked_percent;
    private double price;

    // Getters and setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getBookFormat() { return book_format; }
    public void setBookFormat(String book_format) { this.book_format = book_format; }

    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getPublishDate() { return publish_date; }
    public void setPublishDate(String publish_date) { this.publish_date = publish_date; }

    public String getFirstPublishDate() { return first_publish_date; }
    public void setFirstPublishDate(String first_publish_date) { this.first_publish_date = first_publish_date; }

    public double getLikedPercent() { return liked_percent; }
    public void setLikedPercent(double liked_percent) { this.liked_percent = liked_percent; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
