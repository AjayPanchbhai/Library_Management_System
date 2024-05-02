package com.university.Library_Management_System.Models;

import com.university.Library_Management_System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@Getter
@Setter

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int card_id;

    private Integer no_of_books_issue;

    @Enumerated(value = EnumType.STRING)
    private CardStatus card_status;

    @JoinColumn
    @OneToOne
    private Student student;

    public LibraryCard() {
        this.card_status = CardStatus.NEW;
        this.no_of_books_issue = 0;
    }
}
