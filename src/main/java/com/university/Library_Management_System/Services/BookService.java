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
    public Book getBookById(int book_id) {
        return bookRepository.findById(book_id).orElse(null);
    }

    // get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // update Book
    public Book updateBook(int book_id, Book book) {
        Book book1 = this.getBookById(book_id);
        if(book1 != null) {
            if(book.getName() != null) book1.setName(book.getName());
            if(book.getPrice() != null) book1.setPrice(book.getPrice());
            if(book.getRating() != null) book1.setRating(book.getRating());
            if(book.getGenre() != null) book1.setGenre(book.getGenre());
            if(book.getAuthor() != null) book1.setAuthor(book.getAuthor());

            bookRepository.save(book1);
        }

        return this.getBookById(book_id);
    }

    // delete book
    public Book deleteBook(int book_id) {
        Book book = this.getBookById(book_id);
        if(book != null) bookRepository.delete(book);
        return book;
    }

    // associate Book to Card
    public String associateBookAndCard(int book_id, int author_id) throws Exception {
        Book book = this.getBookById(book_id);
        Author author = authorService.getAuthor(author_id);

        if(book == null) {
            throw new Exception("message : Book is Not Found of ID - " + book_id);
        }

        if(author == null) {
            throw new Exception("message : Author is Not Found of ID - " + author_id);
        }

        author.setNo_of_books(author.getNo_of_books() + 1);
        book.setAuthor(author);

        authorRepository.save(author);
        bookRepository.save(book);

        return "Book " + book_id + " associate with Author " + author_id + " successfully";
    }
}
