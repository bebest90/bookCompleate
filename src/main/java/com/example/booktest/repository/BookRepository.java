package com.example.booktest.repository;

import com.example.booktest.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByUserId(Long userId);
}