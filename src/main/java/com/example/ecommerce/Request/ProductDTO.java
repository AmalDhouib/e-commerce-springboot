package com.example.ecommerce.Request;

import com.example.ecommerce.modele.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
        private String name;
        private String description;
        private Double price;
        private String material;
        private String technique;
        private String originCountry;
        private Integer stockQuantity;
        private Boolean ecoFriendly;
        private Boolean limitedEdition;
        private String image;
        private Category category;
        private String productionTime;        // Temps de fabrication estimé

        // getters/setters
    }


