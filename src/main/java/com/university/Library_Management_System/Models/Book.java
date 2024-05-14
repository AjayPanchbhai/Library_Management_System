package com.university.Library_Management_System.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.university.Library_Management_System.Enum.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer bookId;

    private String name;
    private Integer no_of_pages;
    private Double price;
    private Double rating;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @JoinColumn
    @ManyToOne
    @JsonManagedReference
    private Author author;

    private Integer bookTotalCount;

    private Integer issuedBookCount;

    public Book() {
        this.no_of_pages = 0;
        this.rating = 0.0;
        this.bookTotalCount = 0;
        this.issuedBookCount = 0;
    }
}

