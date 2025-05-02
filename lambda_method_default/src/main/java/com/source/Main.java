package com.source;

import com.source.model.Book;
import com.source.model.EBook;
import com.source.service.Library;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.addBook(new Book("1984", "George Orwell", 1949));
        library.addBook(new Book("Animal Farm", "George Orwell", 1945));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));
        
        library.addBook(new EBook("Digital Fortress", "Dan Brown", 2000, 2.5, "EPUB"));
        library.addBook(new EBook("Steve Jobs", "Walter Isaacson", 2011, 5.7, "PDF"));
        
        System.out.println("All books in the library:");
        library.printAllBooks();
        
        String authorToFind = "George Orwell";
        System.out.println("\nBooks by " + authorToFind + ":");
        List<Book> booksByAuthor = library.findBooksByAuthor(authorToFind);
        booksByAuthor.forEach(System.out::println);
        
        System.out.println("\nBooks sorted by title:");
        List<Book> sortedBooksByTitle = library.sortBooksByTitle();
        sortedBooksByTitle.forEach(System.out::println);
        
        // Extra activities
        
        // 1. Sort books by year (ascending)
        System.out.println("\nBooks sorted by year (ascending):");
        List<Book> sortedByYearAsc = library.sortBooksByYearAscending();
        sortedByYearAsc.forEach(System.out::println);
        
        // 1. Sort books by year (descending)
        System.out.println("\nBooks sorted by year (descending):");
        List<Book> sortedByYearDesc = library.sortBooksByYearDescending();
        sortedByYearDesc.forEach(System.out::println);
        
        // 3. Find books published before a specific year
        int yearFilter = 1950;
        System.out.println("\nBooks published before " + yearFilter + ":");
        List<Book> booksBeforeYear = library.findBooksPublishedBefore(yearFilter);
        booksBeforeYear.forEach(System.out::println);
        
        // 3. Find books with titles containing a specific substring
        String titleSubstring = "the";
        System.out.println("\nBooks with titles containing '" + titleSubstring + "':");
        List<Book> booksWithTitle = library.findBooksByTitleContaining(titleSubstring);
        booksWithTitle.forEach(System.out::println);
        
        // Demonstrate user interaction menu
        interactiveMenu(library);
    }
    
    // 4. Create a user interaction menu
    private static void interactiveMenu(Library library) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("\n\n--- Library Management System ---");
        
        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Add a new book");
            System.out.println("2. Find books by author");
            System.out.println("3. Sort books by title");
            System.out.println("4. View all books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    addNewBook(scanner, library);
                    break;
                case 2:
                    findBooksByAuthor(scanner, library);
                    break;
                case 3:
                    System.out.println("\nBooks sorted by title:");
                    library.sortBooksByTitle().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("\nAll books in the library:");
                    library.printAllBooks();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        
        scanner.close();
    }
    
    private static void addNewBook(Scanner scanner, Library library) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        
        int year = 0;
        boolean validYear = false;
        while (!validYear) {
            System.out.print("Enter publication year: ");
            try {
                year = scanner.nextInt();
                validYear = true;
            } catch (Exception e) {
                System.out.println("Invalid year. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        
        System.out.print("Is this an e-book? (y/n): ");
        String isEbook = scanner.nextLine().toLowerCase();
        
        if (isEbook.equals("y") || isEbook.equals("yes")) {
            double fileSize = 0;
            boolean validSize = false;
            while (!validSize) {
                System.out.print("Enter file size in MB: ");
                try {
                    fileSize = scanner.nextDouble();
                    validSize = true;
                } catch (Exception e) {
                    System.out.println("Invalid file size. Please enter a valid number.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
            
            System.out.print("Enter format (e.g., PDF, EPUB): ");
            String format = scanner.nextLine();
            
            library.addBook(new EBook(title, author, year, fileSize, format));
            System.out.println("E-book added successfully!");
        } else {
            library.addBook(new Book(title, author, year));
            System.out.println("Book added successfully!");
        }
    }
    
    private static void findBooksByAuthor(Scanner scanner, Library library) {
        System.out.print("Enter author name to search for: ");
        String authorToFind = scanner.nextLine();
        
        List<Book> books = library.findBooksByAuthor(authorToFind);
        
        if (books.isEmpty()) {
            System.out.println("No books found by author: " + authorToFind);
        } else {
            System.out.println("\nBooks by " + authorToFind + ":");
            books.forEach(System.out::println);
        }
    }
}