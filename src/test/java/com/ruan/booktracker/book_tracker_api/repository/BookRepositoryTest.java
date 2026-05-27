package com.ruan.booktracker.book_tracker_api.repository;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void salvarTest() {

        Book b1 = new Book(null, "Clean Code", "Robert Martin", 400, Genre.SCIENCE);

        Book savedBook = bookRepository.save(b1);

        Assertions.assertNotNull(savedBook.getId());
        Assertions.assertEquals("Clean Code", savedBook.getTitle());
    }
}