package com.ruan.booktracker.book_tracker_api.config;

import com.ruan.booktracker.book_tracker_api.entities.User;
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

    @Override
    public void run(String... args) throws Exception {


        User u1 = new User(null, "antonia lindeberg", "antonialindeberg@hotmail.com", "12345678", LocalDateTime.now());
        User u2 = new User(null, "mario solimoes", "mariosolimoes@hotmail.com", "12345678", LocalDateTime.now() );

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
