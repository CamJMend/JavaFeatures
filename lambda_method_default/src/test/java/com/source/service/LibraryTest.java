package com.source.service;

import com.source.model.Book;
import com.source.model.EBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    
    @BeforeEach
    void setUp() {
        library = new Library();
        // Add some test books
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.addBook(new Book("1984", "George Orwell", 1949));
        library.addBook(new Book("Animal Farm", "George Orwell", 1945));
        library.addBook(new EBook("Digital Fortress", "Dan Brown", 2000, 2.5, "EPUB"));
    }
    
    @Test
    void testGetBooks() {
        assertEquals(5, library.getBooks().size(), "Library should have 5 books");
    }
    
    @Test
    void testFindBooksByAuthor() {
        List<Book> orwellBooks = library.findBooksByAuthor("George Orwell");
        assertEquals(2, orwellBooks.size(), "Should find 2 books by George Orwell");
        
        List<Book> brownBooks = library.findBooksByAuthor("Dan Brown");
        assertEquals(1, brownBooks.size(), "Should find 1 book by Dan Brown");
        
        List<Book> nonExistentAuthorBooks = library.findBooksByAuthor("Non Existent Author");
        assertEquals(0, nonExistentAuthorBooks.size(), "Should find 0 books by non-existent author");
    }
    
    @Test
    void testSortBooksByTitle() {
        List<Book> sortedBooks = library.sortBooksByTitle();
        assertEquals("1984", sortedBooks.get(0).getTitle(), "First book should be '1984'");
        assertEquals("To Kill a Mockingbird", sortedBooks.get(4).getTitle(), "Last book should be 'To Kill a Mockingbird'");
    }
    
    @Test
    void testFindBooksPublishedBefore() {
        List<Book> oldBooks = library.findBooksPublishedBefore(1950);
        assertEquals(3, oldBooks.size(), "Should find 3 books published before 1950");
    }
    
    @Test
    void testFindBooksByTitleContaining() {
        List<Book> booksWithThe = library.findBooksByTitleContaining("The");
        assertEquals(2, booksWithThe.size(), "Should find 2 books with 'The' in the title");
    }
    
    @Test
    void testSortBooksByYearAscending() {
        List<Book> sortedBooks = library.sortBooksByYearAscending();
        assertEquals(1925, sortedBooks.get(0).getYear(), "First book should be from 1925");
        assertEquals(2000, sortedBooks.get(4).getYear(), "Last book should be from 2000");
    }
    
    @Test
    void testSortBooksByYearDescending() {
        List<Book> sortedBooks = library.sortBooksByYearDescending();
        assertEquals(2000, sortedBooks.get(0).getYear(), "First book should be from 2000");
        assertEquals(1925, sortedBooks.get(4).getYear(), "Last book should be from 1925");
    }
}