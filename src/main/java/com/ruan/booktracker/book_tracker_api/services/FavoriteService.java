package com.ruan.booktracker.book_tracker_api.services;


import com.ruan.booktracker.book_tracker_api.dto.FavoriteDTO;
import com.ruan.booktracker.book_tracker_api.entities.Favorite;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repository;

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

    public Favorite insert(Favorite obj) {

        if (obj.getUser() == null || obj.getBook() == null) {
            throw new RuntimeException("Favorite must have a user and a book");
        }

        return repository.save(obj);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}