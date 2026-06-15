package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.reading.request.CreateReadingRequest;
import com.ruan.booktracker.book_tracker_api.dto.reading.request.UpdateReadingRequest;
import com.ruan.booktracker.book_tracker_api.dto.reading.response.ReadingResponse;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Reading;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.entities.enums.ReadingStatus;
import com.ruan.booktracker.book_tracker_api.exceptions.BusinessRuleException;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.ReadingRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReadingService {

    private final ReadingRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReadingService(ReadingRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<ReadingResponse> findAll() {
        return repository.findAll().stream().map(ReadingResponse::new).toList();
    }

    public ReadingResponse findById(UUID id) {
        Reading entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading", id));
        return new ReadingResponse(entity);
    }

    public ReadingResponse insert(CreateReadingRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", dto.userId()));
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", dto.bookId()));

        validateReading(dto.currentPage(), dto.status(), book.getTotalPages());

        Reading entity = new Reading();
        entity.setCurrentPage(dto.currentPage());
        entity.setStatus(dto.status());
        entity.setUser(user);
        entity.setBook(book);

        if (dto.status() == ReadingStatus.COMPLETED) {
            entity.setFinishedAt(LocalDateTime.now());
        }

        entity = repository.save(entity);
        return new ReadingResponse(entity);
    }

    public ReadingResponse update(UUID id, UpdateReadingRequest dto) {
        Reading entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading", id));

        validateReading(dto.currentPage(), dto.status(), entity.getBook().getTotalPages());

        entity.setCurrentPage(dto.currentPage());
        entity.setStatus(dto.status());

        if (dto.status() == ReadingStatus.COMPLETED && entity.getFinishedAt() == null) {
            entity.setFinishedAt(LocalDateTime.now());
        } else if (dto.status() != ReadingStatus.COMPLETED) {
            entity.setFinishedAt(null);
        }

        entity = repository.save(entity);
        return new ReadingResponse(entity);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Reading", id);
        }
        repository.deleteById(id);
    }

    private void validateReading(Integer currentPage, ReadingStatus status, Integer totalPages) {
        if (currentPage > totalPages) {
            throw new BusinessRuleException(
                    "Current page (" + currentPage + ") cannot exceed total pages (" + totalPages + ")");
        }
        if (status == ReadingStatus.COMPLETED && currentPage < totalPages) {
            throw new BusinessRuleException(
                    "Cannot mark as COMPLETED when current page (" + currentPage
                    + ") is less than total pages (" + totalPages + ")");
        }
    }
}
