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
        // Los nuevos libros solicitados
        library.addBook(new Book("Turtles All the Way Down", "John Green", 2017));
        library.addBook(new Book("Sapiens", "Yuval Noah", 2011));
        library.addBook(new Book("The Land of Stories", "Chris Colfer", 2015));
        library.addBook(new Book("The Midnight Library", "Matt Haig", 2020));
        library.addBook(new EBook("They Both Die at the End", "Adam Silvera", 2017, 3.2, "EPUB"));
    }
    
    @Test
    void testGetBooks() {
        assertEquals(5, library.getBooks().size(), "Library should have 5 books");
    }
    
    @Test
    void testFindBooksByAuthor() {
        List<Book> greenBooks = library.findBooksByAuthor("John Green");
        assertEquals(1, greenBooks.size(), "Should find 1 book by John Green");
        
        List<Book> silveraBooks = library.findBooksByAuthor("Adam Silvera");
        assertEquals(1, silveraBooks.size(), "Should find 1 book by Adam Silvera");
        
        List<Book> nonExistentAuthorBooks = library.findBooksByAuthor("Non Existent Author");
        assertEquals(0, nonExistentAuthorBooks.size(), "Should find 0 books by non-existent author");
    }
    
    @Test
    void testSortBooksByTitle() {
        List<Book> sortedBooks = library.sortBooksByTitle();
        assertEquals("Sapiens", sortedBooks.get(0).getTitle(), "First book should be 'Sapiens'");
        assertEquals("Turtles All the Way Down", sortedBooks.get(4).getTitle(), "Last book should be 'Turtles All the Way Down'");
    }
    
    @Test
    void testFindBooksPublishedBefore() {
        List<Book> oldBooks = library.findBooksPublishedBefore(2015);
        assertEquals(1, oldBooks.size(), "Should find 1 book published before 2015");
    }
    
    @Test
    void testFindBooksByTitleContaining() {
        List<Book> booksWithThe = library.findBooksByTitleContaining("The");
        assertEquals(4, booksWithThe.size(), "Should find 4 books with 'The' in the title");
    }
    
    @Test
    void testSortBooksByYearAscending() {
        List<Book> sortedBooks = library.sortBooksByYearAscending();
        assertEquals(2011, sortedBooks.get(0).getYear(), "First book should be from 2011");
        assertEquals(2020, sortedBooks.get(4).getYear(), "Last book should be from 2020");
    }
    
    @Test
    void testSortBooksByYearDescending() {
        List<Book> sortedBooks = library.sortBooksByYearDescending();
        assertEquals(2020, sortedBooks.get(0).getYear(), "First book should be from 2020");
        assertEquals(2011, sortedBooks.get(4).getYear(), "Last book should be from 2011");
    }
}