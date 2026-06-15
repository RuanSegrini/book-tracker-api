package com.ruan.booktracker.book_tracker_api.repositories;

import com.ruan.booktracker.book_tracker_api.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {

    boolean existsByUserIdAndBookId(UUID userId, UUID bookId);

    List<Favorite> findByUserId(UUID userId);
}
