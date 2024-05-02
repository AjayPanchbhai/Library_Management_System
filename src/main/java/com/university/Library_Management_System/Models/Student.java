package com.university.Library_Management_System.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer student_id;

    private String name;

    @Column(unique=true, length=150, nullable=false)
    private String email;
    private Integer age;
    private String branch;
    private String address;

    public Student() {
    }
}
