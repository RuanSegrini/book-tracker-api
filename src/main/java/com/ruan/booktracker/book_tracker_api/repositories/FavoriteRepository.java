package com.ruan.booktracker.book_tracker_api.repositories;

import com.ruan.booktracker.book_tracker_api.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
