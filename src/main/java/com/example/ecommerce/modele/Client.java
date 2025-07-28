package com.example.ecommerce.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Client extends _User {
    private String localisation;
    private String shippingAddress;
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;// date sans heure, c’est-à-dire uniquement année, mois et jour.
    private int loyaltyPoints;

    private String imageClient;


}
