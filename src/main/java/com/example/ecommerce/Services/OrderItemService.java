package com.example.ecommerce.Services;

import com.example.ecommerce.modele.OrderItem;
import com.example.ecommerce.repositories.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    @AllArgsConstructor
    public class OrderItemService {

        private final OrderItemRepository orderItemRepository;

        public OrderItem save(OrderItem orderItem) {
            return orderItemRepository.save(orderItem);
        }

        public List<OrderItem> findAll() {
            return orderItemRepository.findAll();
        }

        public void deleteById(Long id) {
            orderItemRepository.deleteById(id);
        }
}
