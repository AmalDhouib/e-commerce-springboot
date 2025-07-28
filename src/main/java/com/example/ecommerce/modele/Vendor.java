package com.example.ecommerce.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor extends _User{
    private String shopName;
    private String businessAddress;
    private String Website;
    private Double averageRating;
    private String imageVendor;




}
