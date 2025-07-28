package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.FavoriteService;
import com.example.ecommerce.Services.ProductService;
import com.example.ecommerce.modele.Favorite;
import com.example.ecommerce.modele.FavoriteDTO;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.modele._User;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories._UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/favorite")
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final _UserRepository userRepository;

    @PostMapping("/save")
    public Favorite save(@RequestBody FavoriteDTO dto) {
        _User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return favoriteService.findByUserIdAndProductId(user.getId(), product.getId())
                .orElseGet(() -> {
                    Favorite favorite = new Favorite();
                    favorite.setUser(user);
                    favorite.setProduct(product);
                    return favoriteService.save(favorite);
                });
    }

    @GetMapping("/getId/{id}")
    public Favorite getById(@PathVariable Long id) {
        return favoriteService.findById(id);
    }

    @GetMapping("/get")
    public List<Favorite> findAll() {
        return favoriteService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        favoriteService.delete(id);
    }

    @DeleteMapping("/deleteByUserAndProduct")
    public void deleteByUserAndProduct(@RequestParam Long userId, @RequestParam Long productId) {
        favoriteService.deleteByUserIdAndProductId(userId, productId);
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.findByUserId(userId);
    }

    @GetMapping("/check")
    public boolean checkFavorite(@RequestParam Long userId, @RequestParam Long productId) {
        return favoriteService.findByUserIdAndProductId(userId, productId).isPresent();
    }
}
