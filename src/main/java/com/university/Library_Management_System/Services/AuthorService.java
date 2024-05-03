package com.university.Library_Management_System.Services;

import com.university.Library_Management_System.Models.Author;
import com.university.Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthor(int authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(int authorId, Author author) {
        Author author1 = this.getAuthor(authorId);

        if(author1 != null) {
            if(author.getName() != null) author1.setName(author.getName());
            if(author.getAge() != null) author1.setAge(author.getAge());
            if(author.getNo_of_books() != null) author1.setNo_of_books(author.getNo_of_books());
            if(author.getEmail() != null) author1.setEmail(author.getEmail());

            authorRepository.save(author1);
        }

        return this.getAuthor(authorId);
    }

    public Author deleteAuthor(int authorId) {
        Author author = this.getAuthor(authorId);

        if(author != null)
            authorRepository.deleteById(authorId);

        return author;
    }
}
