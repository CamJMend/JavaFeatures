package com.source.model;

public class EBook extends Book {
    private double fileSizeMB;
    private String format;
    
    public EBook(String title, String author, int year, double fileSizeMB, String format) {
        super(title, author, year);
        this.fileSizeMB = fileSizeMB;
        this.format = format;
    }
    
    // Getters y Setters
    public double getFileSizeMB() {
        return fileSizeMB;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    @Override
    public String toString() {
        return "EBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", fileSizeMB=" + fileSizeMB +
                ", format='" + format + '\'' +
                '}';
    }
}