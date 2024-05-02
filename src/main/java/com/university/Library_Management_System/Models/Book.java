package com.university.Library_Management_System.Models;

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

    private Integer book_id;
    private String name;
    private Integer no_of_pages;
    private Double price;
    private Double rating;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @JoinColumn
    @ManyToOne
    private Author author;

    public Book() {
        this.no_of_pages = 0;
        this.rating = 0.0;
    }
}

