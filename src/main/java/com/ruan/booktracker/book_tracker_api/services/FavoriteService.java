package com.ruan.booktracker.book_tracker_api.services;


import com.ruan.booktracker.book_tracker_api.dto.favorite.FavoriteCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.favorite.FavoriteDTO;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Favorite;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.FavoriteRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<FavoriteDTO> findAll() {
        List<Favorite> list = repository.findAll();

        return list.stream()
                .map(FavoriteDTO::new)
                .toList();
    }

    public FavoriteDTO findById(Long id) {
        Favorite entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new FavoriteDTO(entity);
    }

    public FavoriteDTO insert(FavoriteCreateDTO dto) {

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.userId()));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.bookId()));

        Favorite entity = new Favorite();

        entity.setUser(user);
        entity.setBook(book);

        entity = repository.save(entity);

        return new FavoriteDTO(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}