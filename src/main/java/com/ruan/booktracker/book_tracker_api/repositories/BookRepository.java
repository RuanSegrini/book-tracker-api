package com.ruan.booktracker.book_tracker_api.repositories;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
