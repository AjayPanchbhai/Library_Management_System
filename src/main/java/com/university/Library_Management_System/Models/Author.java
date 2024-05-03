package com.university.Library_Management_System.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int authorId;

    private String name;
    private Integer age;
    private Integer no_of_books;

    @Column(unique=true, length=150, nullable=false)
    private String email;

    public Author() {
        this.no_of_books = 0;
    }

}
