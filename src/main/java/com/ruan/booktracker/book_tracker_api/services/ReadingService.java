package com.ruan.booktracker.book_tracker_api.services;


import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository repository;

    public List<Reading> findAll() {
        return repository.findAll();
    }

    public Reading findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Reading insert(Reading obj) {
        return repository.save(obj);
    }

    public Reading update(Long id, Reading obj) {
        Reading entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Reading entity, Reading obj) {
        entity.setCurrentPage(obj.getCurrentPage());
        entity.setStatus(obj.getStatus());
        entity.setStartedAt(obj.getStartedAt());
        entity.setFinishedAt(obj.getFinishedAt());
        entity.setUser(obj.getUser());
        entity.setBook(obj.getBook());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
