package com.university.Library_Management_System.Services;

import com.university.Library_Management_System.Models.Author;
import com.university.Library_Management_System.Models.Book;
import com.university.Library_Management_System.Repositories.AuthorRepository;
import com.university.Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    // add book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // get book
    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    // get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // update Book
    public Book updateBook(int bookId, Book book) {
        Book book1 = this.getBookById(bookId);
        if(book1 != null) {
            if(book.getName() != null) book1.setName(book.getName());
            if(book.getPrice() != null) book1.setPrice(book.getPrice());
            if(book.getRating() != null) book1.setRating(Math.min(Math.max(book.getRating(), 0.0), 5.0));
            if(book.getGenre() != null) book1.setGenre(book.getGenre());
            if(book.getAuthor() != null) book1.setAuthor(book.getAuthor());
            if(book.getBookTotalCount() != null) book1.setBookTotalCount(Math.max(0,book1.getBookTotalCount() + book.getBookTotalCount()));

            bookRepository.save(book1);
        }

        return this.getBookById(bookId);
    }

    // delete book
    public Book deleteBook(int bookId) {
        Book book = this.getBookById(bookId);
        if(book != null) bookRepository.delete(book);
        return book;
    }

    // associate Book to Card
    public String associateBookAndAuthor(int bookId, int authorId) throws Exception {
        Book book = this.getBookById(bookId);
        Author author = authorService.getAuthor(authorId);

        if(book == null) {
            throw new Exception("message : Book is Not Found of ID - " + bookId);
        }

        if(author == null) {
            throw new Exception("message : Author is Not Found of ID - " + authorId);
        }

        author.setNo_of_books(author.getNo_of_books() + 1);
        author.getBooks().add(book);
        book.setAuthor(author);

        authorRepository.save(author);
        bookRepository.save(book);

        return "Book " + bookId + " associate with Author " + authorId + " successfully";
    }
}
