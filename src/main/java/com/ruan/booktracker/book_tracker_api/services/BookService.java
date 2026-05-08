package com.ruan.booktracker.book_tracker_api.services;

import java.util.List;
import java.util.Optional;


import com.ruan.booktracker.book_tracker_api.dto.BookDTO;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookDTO> findAll() {
        List<Book> list = repository.findAll();

        return list.stream()
                .map(BookDTO::new)
                .toList();
    }

    public BookDTO findById(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new BookDTO(entity);
    }

    public Book insert(Book obj) {
        return repository.save(obj);
    }

    public Book update(Long id, Book obj) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Book entity, Book obj) {
        entity.setTitle(obj.getTitle());
        entity.setAuthor(obj.getAuthor());
        entity.setTotalPages(obj.getTotalPages());
        entity.setGenre(obj.getGenre());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
