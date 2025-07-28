package com.example.ecommerce.modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order-items")
    private Orders order;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-item-ref")
    private Product product;
}

