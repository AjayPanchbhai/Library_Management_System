package com.university.Library_Management_System.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.university.Library_Management_System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardId;

    private Integer no_of_books_issue;

    @Enumerated(value = EnumType.STRING)
    private CardStatus card_status;

    @JoinColumn
    @OneToOne
    @JsonManagedReference
    private Student student;

    @JoinColumn
    @OneToMany
    @JsonManagedReference
    private List<Book> books;

    public LibraryCard() {
        this.card_status = CardStatus.NEW;
        this.no_of_books_issue = 0;
    }
}
