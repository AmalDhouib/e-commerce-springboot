package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Favorite;
import com.example.ecommerce.repositories.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public void delete(Long id) {
        favoriteRepository.deleteById(id);
    }

    public void deleteByUserIdAndProductId(Long userId, Long productId) {
        favoriteRepository.findByUserIdAndProductId(userId, productId)
                .ifPresent(fav -> favoriteRepository.deleteById(fav.getId()));
    }

    public Favorite findById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }

    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    public Optional<Favorite> findByUserIdAndProductId(Long userId, Long productId) {
        return favoriteRepository.findByUserIdAndProductId(userId, productId);
    }

    public List<Favorite> findByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }
}
