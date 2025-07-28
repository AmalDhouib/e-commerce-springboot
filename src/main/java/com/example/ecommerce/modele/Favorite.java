package com.example.ecommerce.modele;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    // ou @JsonIgnoreProperties("favorites") si tu utilises une liste dans Product
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private _User user;


    private LocalDateTime addedAt = LocalDateTime.now();
}
