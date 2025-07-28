package com.example.ecommerce.repositories;

import com.example.ecommerce.modele.Orders;
import com.example.ecommerce.modele.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository <Orders,Long> {
    List<Orders> findByUserId(Long userId);


}
