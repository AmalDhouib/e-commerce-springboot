package com.example.ecommerce.repositories;


import com.example.ecommerce.modele.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}


