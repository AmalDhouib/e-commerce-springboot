package com.example.ecommerce.repositories;

import com.example.ecommerce.modele.Category;
import com.example.ecommerce.modele.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(Category category);

}
