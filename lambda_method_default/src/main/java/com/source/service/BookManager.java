package com.source.service;

import com.source.model.Book;
import java.util.List;

public interface BookManager {
    void addBook(Book book);
    List<Book> getBooks();
    List<Book> findBooksByAuthor(String author);
    List<Book> sortBooksByTitle();
    
    default void printAllBooks() {
        getBooks().forEach(System.out::println);
    }
}