package com.university.Library_Management_System.Services;

import com.university.Library_Management_System.Enum.TransactionStatus;
import com.university.Library_Management_System.Models.Book;
import com.university.Library_Management_System.Models.LibraryCard;
import com.university.Library_Management_System.Models.Transaction;
import com.university.Library_Management_System.Repositories.BookRepository;
import com.university.Library_Management_System.Repositories.CardRepository;
import com.university.Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    public Transaction issueBook(int bookId, int cardId) throws  Exception {
        Book book = bookRepository.findById(bookId).orElse(null);
        LibraryCard card = cardRepository.findById(cardId).orElse(null);

        if(book == null) {
            throw new Exception("Book Not Found of ID - " + bookId);
        }

        if(book.getIsIssued()) {
            throw new Exception("This Book already issued");
        }

        if(card == null) {
            throw new Exception("Card Not Found of ID - " + cardId);
        }

        if(!String.valueOf(card.getCard_status()).equalsIgnoreCase("ACTIVE")) {
            throw new Exception("Card it DEACTIVATED!, Please Activate the Card");
        }

        if(card.getNo_of_books_issue() >= 3) {
            throw new Exception("Card Book issue limit Reached Can't issue more books");
        }

        book.setIsIssued(true);
        card.setNo_of_books_issue(card.getNo_of_books_issue() + 1);

        bookRepository.save(book);
        cardRepository.save(card);

        Transaction transaction = new Transaction();

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setBook(book);
        transaction.setCard(card);

        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction findByCard(int bookId, int cardId) {
        return (Transaction) transactionRepository.findByBookIdAndCardId(bookId, cardId);
    }

//    public Transaction returnBook(int bookId, int cardId) throws Exception {
//
//        Transaction transaction =
//        Book book = bookRepository.findById(bookId).orElse(null);
//        LibraryCard card = cardRepository.findById(cardId).orElse(null);
//
//        assert card != null;
//        if(String.valueOf(card.getCard_status()).equalsIgnoreCase("DEACTIVATED")) {
//            throw new Exception("Card it DEACTIVATED!, Please Activate the Card");
//        }
//
//        assert book != null;
//        book.setIsIssued(false);
//        card.setNo_of_books_issue(Math.max(card.getNo_of_books_issue() - 1, 0));
//
//        bookRepository.save(book);
//        cardRepository.save(card);
//
//        Transaction transaction = new Transaction();
//
//        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
//        transaction.setBook(book);
//        transaction.setCard(card);
//
//        transactionRepository.save(transaction);
//        return transaction;
//    }
}
