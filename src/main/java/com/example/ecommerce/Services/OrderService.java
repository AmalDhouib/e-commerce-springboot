package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Orders;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Ajouter une commande
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    // Trouver une commande par ID
    public Orders findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'ID : " + id));
    }

    // Récupérer toutes les commandes
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }
    public List<Product> getProductsByOrderId(Long id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID : " + id));
        return order.getProducts();
    }
    // Supprimer une commande
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    // Mettre à jour une commande
    public Orders update(Long id, Orders updatedOrder) {
        Orders existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'ID : " + id));
        if (updatedOrder.getUser() != null) {
            existingOrder.setUser(updatedOrder.getUser());
        } else {
            existingOrder.setUser(existingOrder.getUser());
        }

        if (updatedOrder.getStatus() != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
        } else {
            existingOrder.setStatus(existingOrder.getStatus());
        }

        if (updatedOrder.getProducts() != null) {
            existingOrder.setProducts(updatedOrder.getProducts());
        } else {
            existingOrder.setProducts(existingOrder.getProducts());
        }

        existingOrder.setDeliveryFullName(updatedOrder.getDeliveryFullName());
        existingOrder.setDeliveryEmail(updatedOrder.getDeliveryEmail());
        existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
        existingOrder.setDeliveryPhone(updatedOrder.getDeliveryPhone());


        return orderRepository.save(existingOrder);
    }
    public List<Orders> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
