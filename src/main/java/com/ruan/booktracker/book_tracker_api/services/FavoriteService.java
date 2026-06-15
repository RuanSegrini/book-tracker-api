package com.ruan.booktracker.book_tracker_api.services;

import com.ruan.booktracker.book_tracker_api.dto.favorite.request.CreateFavoriteRequest;
import com.ruan.booktracker.book_tracker_api.dto.favorite.response.FavoriteResponse;
import com.ruan.booktracker.book_tracker_api.entities.Book;
import com.ruan.booktracker.book_tracker_api.entities.Favorite;
import com.ruan.booktracker.book_tracker_api.entities.User;
import com.ruan.booktracker.book_tracker_api.exceptions.BusinessRuleException;
import com.ruan.booktracker.book_tracker_api.exceptions.ResourceNotFoundException;
import com.ruan.booktracker.book_tracker_api.repositories.BookRepository;
import com.ruan.booktracker.book_tracker_api.repositories.FavoriteRepository;
import com.ruan.booktracker.book_tracker_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FavoriteService {

    private final FavoriteRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public FavoriteService(FavoriteRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<FavoriteResponse> findAll() {
        return repository.findAll().stream().map(FavoriteResponse::new).toList();
    }

    public FavoriteResponse findById(UUID id) {
        Favorite entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite", id));
        return new FavoriteResponse(entity);
    }

    public FavoriteResponse insert(CreateFavoriteRequest dto) {
        if (repository.existsByUserIdAndBookId(dto.userId(), dto.bookId())) {
            throw new BusinessRuleException("User has already favorited this book");
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", dto.userId()));
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", dto.bookId()));

        Favorite entity = new Favorite();
        entity.setUser(user);
        entity.setBook(book);

        entity = repository.save(entity);
        return new FavoriteResponse(entity);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite", id);
        }
        repository.deleteById(id);
    }
}
