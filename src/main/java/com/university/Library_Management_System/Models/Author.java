package com.university.Library_Management_System.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @JoinColumn
    @OneToMany
    @JsonBackReference
    private List<Book> books;

    @Column(unique=true, length=150, nullable=false)
    @NotBlank(message = "Email is Required. Shouldn't be Empty!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email format is invalid!")
    private String email;

    public Author() {
        this.no_of_books = 0;
    }
}
