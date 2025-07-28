package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.OrderItemService;
import com.example.ecommerce.modele.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
@AllArgsConstructor
@CrossOrigin("*")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping("/add")
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.save(orderItem);
    }

    @GetMapping("/all")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
    }

    // Ajoute d'autres endpoints si besoin (update, getById...)
}
