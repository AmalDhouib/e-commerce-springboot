package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.OrderService;
import com.example.ecommerce.modele.Orders;
import com.example.ecommerce.modele.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public Orders save(@RequestBody Orders order) {
        return orderService.save(order);
    }


    @GetMapping("/getId/{id}")
    public Orders getById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/get")
    public List<Orders> findAll() {
        return orderService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Orders update(@PathVariable Long id, @RequestBody Orders order) {
        return orderService.update(id, order);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long id) {
        try {
            List<Product> products = orderService.getProductsByOrderId(id);
            return ResponseEntity.ok(products);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUser(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

}
