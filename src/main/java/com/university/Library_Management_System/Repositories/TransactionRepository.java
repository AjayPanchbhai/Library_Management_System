package com.university.Library_Management_System.Repositories;

import com.university.Library_Management_System.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.RouteMatcher;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query(value = "SELECT * FROM transaction WHERE book_book_id = ?1 AND card_card_id = ?2", nativeQuery = true)
    Transaction findByBookIdAndCardId(int bookId, int cardId);

}
