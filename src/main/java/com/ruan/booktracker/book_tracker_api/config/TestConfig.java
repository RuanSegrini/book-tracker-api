package com.ruan.booktracker.book_tracker_api.config;

import com.ruan.booktracker.book_tracker_api.entities.*;
import com.ruan.booktracker.book_tracker_api.entities.enums.Genre;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import com.ruan.booktracker.book_tracker_api.repositories.*;
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

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

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

        // REVIEWS
        Review rev1 = new Review();
        rev1.setUser(u1);
        rev1.setBook(b1);
        rev1.setRating(5);
        rev1.setComment("Excelente livro!");

        Review rev2 = new Review();
        rev2.setUser(u2);
        rev2.setBook(b2);
        rev2.setRating(4);
        rev2.setComment("Muito bom!");

        reviewRepository.saveAll(Arrays.asList(rev1, rev2));

        // FAVORITES
        Favorite f1 = new Favorite();
        f1.setUser(u1);
        f1.setBook(b2);

        Favorite f2 = new Favorite();
        f2.setUser(u2);
        f2.setBook(b1);

        favoriteRepository.saveAll(Arrays.asList(f1, f2));
    }
}