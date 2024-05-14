package com.university.Library_Management_System.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer studentId;

    @NotBlank(message = "Student Name is Required.Shouldn't be Empty!")
    private String name;

    @Column(unique=true, length=150, nullable=false)
    @NotBlank(message = "Email is Required. Shouldn't be Empty!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email format is invalid!")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 40, message = "Age must not exceed 40")
    private Integer age;

    private String branch;
    private String address;

    @JoinColumn
    @OneToOne
    @JsonBackReference
    private LibraryCard card;

    public Student() {
    }
}
