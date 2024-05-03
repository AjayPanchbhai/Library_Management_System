package com.university.Library_Management_System.Controllers;

import com.university.Library_Management_System.Models.Transaction;
import com.university.Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId") int bookId, @RequestParam("cardId") int cardId) {
        try {
            Transaction transaction = transactionService.issueBook(bookId, cardId);
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to issue book\n" + "Error : " +e.getMessage());
        }
    }

    @GetMapping("/getByCard")
    public ResponseEntity getByCard(@RequestParam("bookId") int bookId, @RequestParam("cardId") int cardId) {
        try {
            Transaction transaction = transactionService.findByCard(bookId, cardId);
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
