package com.example.ecommerce.repositories;

import com.example.ecommerce.modele.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserIdAndProductId(Long userId, Long productId);
    List<Favorite> findByUserId(Long userId);
}
