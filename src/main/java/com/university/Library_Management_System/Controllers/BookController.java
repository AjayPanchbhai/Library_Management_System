package com.university.Library_Management_System.Controllers;

import com.university.Library_Management_System.Models.Book;
import com.university.Library_Management_System.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book book1 = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to add Book\n" + "Error : " + e.getMessage());
        }
    }

    @GetMapping("/getBook")
    public ResponseEntity<?> getBook(@RequestParam("bookId") int bookId) {
        try {
            Book book = bookService.getBookById(bookId);
            if(book != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(book);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Book Not Found of ID - " + bookId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Fetch Book\n" + "Error : " + e.getMessage());
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            if(!books.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(books);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : No Book Found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Fetch Book\n" + "Error : " + e.getMessage());
        }
    }

    @PatchMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestParam("bookId") int bookId, @RequestBody Book book) {
        try {
            Book book1 = bookService.updateBook(bookId, book);
            if(book1 != null) {
                return ResponseEntity.status(HttpStatus.OK).body(book1);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Book is Not Found of ID - " + bookId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to update book\n" + "Error : " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam("bookId") int bookId) {
        try {
            Book book = bookService.deleteBook(bookId);
            if(book != null)
                return ResponseEntity.status(HttpStatus.OK).body(book);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("message : Book is Not Found of ID : " + bookId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("message : Failed to delete Book" + "\nError : " + e.getMessage());
        }
    }

    @PutMapping("/associateBookAndAuthor")
    public ResponseEntity<?> associateBookAndAuthor(@RequestParam("bookId") int bookId, @RequestParam("authorId") int authorId) {
        try {
            String res = bookService.associateBookAndAuthor(bookId, authorId);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
