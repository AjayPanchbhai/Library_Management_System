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

import java.util.Calendar;
import java.util.Date;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    public Transaction getByBookAndCard(int bookId, int cardId) {
        return transactionRepository.findByBookIdAndCardId(bookId, cardId);
    }

    public Transaction findByCardAndBookAndIssued(int bookId, int cardId) {
        return transactionRepository.findByBookIdAndCardIdAndIssued(bookId, cardId);
    }

    public Transaction issueBook(int bookId, int cardId) throws  Exception {
        Book book = bookRepository.findById(bookId).orElse(null);

        if(book == null) {
            throw new Exception("Book Not Found of ID - " + bookId);
        }

        if(book.getIssuedBookCount() + 1 > book.getBookTotalCount()) {
            throw new Exception("No more Books are Available in Library of bookId - " + bookId);
        }

        LibraryCard card = cardRepository.findById(cardId).orElse(null);

        if(card == null) {
            throw new Exception("Card Not Found of ID - " + cardId);
        }

        if(!String.valueOf(card.getCard_status()).equalsIgnoreCase("ACTIVE")) {
            throw new Exception("Card it DEACTIVATED!, Please Activate the Card");
        }

        if(card.getNo_of_books_issue() >= 3) {
            throw new Exception("Card Book issue limit Reached Can't issue more books");
        }

        Transaction transaction1 = this.findByCardAndBookAndIssued(bookId, cardId);
        if(transaction1 != null) {
            throw new Exception("This Book already issued for the cardId " + cardId + " Can't Issue same book more than One");
        }

        book.setIssuedBookCount(book.getIssuedBookCount() + 1);
        card.setNo_of_books_issue(card.getNo_of_books_issue() + 1);

        bookRepository.save(book);
        cardRepository.save(card);

        Transaction transaction = new Transaction();

        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        transaction.setBook(book);
        transaction.setCard(card);

        transactionRepository.save(transaction);
        return transaction;
    }


    public Transaction returnBook(int bookId, int cardId) throws Exception {
        Transaction transaction = this.findByCardAndBookAndIssued(bookId, cardId);

        if(transaction == null) {
            throw new Exception("Transaction Not Found With bookId " + bookId + " cardId " + cardId + " which is Issued");
        }

        Book book = bookRepository.findById(bookId).orElse(null);
        LibraryCard card = cardRepository.findById(cardId).orElse(null);

        if(book == null) {
            throw new Exception("Book Not Found of ID - " + bookId);
        }

        if(book.getIssuedBookCount() <= 0) {
            throw new Exception("This Book is Not issued before");
        }

        if(card == null) {
            throw new Exception("Card Not Found of ID - " + cardId);
        }

        if(!String.valueOf(card.getCard_status()).equalsIgnoreCase("ACTIVE")) {
            throw new Exception("Card it DEACTIVATED!, Please Activate the Card");
        }

        book.setIssuedBookCount(Math.max(0, book.getIssuedBookCount() - 1));
        card.setNo_of_books_issue(Math.max(card.getNo_of_books_issue() - 1, 0));

        bookRepository.save(book);
        cardRepository.save(card);

        transaction.setTransactionStatus(TransactionStatus.RETURNED);
        transaction.setReturnDate(new Date());

        Date issueDate = transaction.getIssueDate();
        Date returnDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.DAY_OF_YEAR, 15);
        Date newDate = calendar.getTime();
        System.out.println(newDate);

        int comparison = newDate.compareTo(returnDate);
        if(comparison < 0) {
            long diffInMillis = Math.abs(newDate.getTime() - returnDate.getTime());
            long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);

            transaction.setFineAmount((double)diffInDays * 100);
        }

        transactionRepository.save(transaction);
        return transaction;
    }
}
