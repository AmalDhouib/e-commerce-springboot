package com.example.ecommerce.modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String image;
    private String description;
    private String material;
    private String technique;
    private String originCountry;
    private Integer stockQuantity;

    private boolean ecoFriendly;
    private boolean limitedEdition;

    private String productionTime;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "products")
    @JsonBackReference
    private List<Orders> orders;

}


