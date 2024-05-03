package com.university.Library_Management_System.Models;

import com.university.Library_Management_System.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date issueDate;

    private LocalDate returnDate;

    private Double fineAmount;

    @JoinColumn
    @ManyToOne
    private LibraryCard card;

    @JoinColumn
    @ManyToOne
    private Book book;

    public Transaction() {
        this.fineAmount = 0.0;
        this.returnDate = LocalDate.now().plusDays(15);
    }
}
