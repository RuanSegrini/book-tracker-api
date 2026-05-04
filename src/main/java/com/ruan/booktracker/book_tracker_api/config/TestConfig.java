package com.ruan.booktracker.book_tracker_api.config;

import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.ReadingRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReadingRepository readingRepository;

    @Override
    public void run(String... args) throws Exception {

        // USERS
        User u1 = new User(null, "Antonia Lindeberg", "antonia@mail.com", "123456");
        User u2 = new User(null, "Mario Solimoes", "mario@mail.com", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));

        // BOOKS
        Book b1 = new Book(null, "Clean Code", "Robert Martin", 400, Genre.SCIENCE);
        Book b2 = new Book(null, "Harry Potter", "J.K. Rowling", 300, Genre.FANTASY);

        bookRepository.saveAll(Arrays.asList(b1, b2));

        // READINGS
        Reading r1 = new Reading();
        r1.setUser(u1);
        r1.setBook(b1);
        r1.setCurrentPage(120);
        r1.setStatus(ReadingStatus.READING);

        Reading r2 = new Reading();
        r2.setUser(u2);
        r2.setBook(b2);
        r2.setCurrentPage(300);
        r2.setStatus(ReadingStatus.COMPLETED);
        r2.setFinishedAt(LocalDateTime.now());

        readingRepository.saveAll(Arrays.asList(r1, r2));
    }
}
