package com.university.Library_Management_System.Repositories;

import com.university.Library_Management_System.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard, Integer> {
}
