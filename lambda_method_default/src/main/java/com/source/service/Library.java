package com.source.service;

import com.source.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements BookManager {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> sortBooksByTitle() {
        return books.stream()
                .sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
                .collect(Collectors.toList());
    }
    
    // Additional methods
    
    // 1. Sort books by year (ascending)
    public List<Book> sortBooksByYearAscending() {
        return books.stream()
                .sorted((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()))
                .collect(Collectors.toList());
    }
    
    // 1. Sort books by year (descending)
    public List<Book> sortBooksByYearDescending() {
        return books.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getYear(), b1.getYear()))
                .collect(Collectors.toList());
    }
    
    // 3. Additional filtering methods
    // Find books published before a specific year
    public List<Book> findBooksPublishedBefore(int year) {
        return books.stream()
                .filter(book -> book.getYear() < year)
                .collect(Collectors.toList());
    }
    
    // Find books with titles containing a specific substring
    public List<Book> findBooksByTitleContaining(String substring) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(substring.toLowerCase()))
                .collect(Collectors.toList());
    }
}