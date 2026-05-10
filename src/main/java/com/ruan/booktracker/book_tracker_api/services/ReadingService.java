package com.ruan.booktracker.book_tracker_api.services;


import com.ruan.booktracker.book_tracker_api.dto.reading.ReadingCreateDTO;
import com.ruan.booktracker.book_tracker_api.dto.reading.ReadingDTO;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.ReadingRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<ReadingDTO> findAll() {
        List<Reading> list =repository.findAll();

        return list.stream()
                .map(ReadingDTO::new)
                .toList();
    }

    public ReadingDTO findById(Long id) {
        Reading entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new ReadingDTO(entity);
    }

    public ReadingDTO insert(ReadingCreateDTO dto) {

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.userId()));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.bookId()));

        Reading entity = new Reading();

        entity.setCurrentPage(dto.currentPage());
        entity.setStatus(dto.status());

        entity.setUser(user);
        entity.setBook(book);

        entity = repository.save(entity);

        return new ReadingDTO(entity);
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
