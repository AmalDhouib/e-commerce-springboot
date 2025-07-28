package com.example.ecommerce.repositories;

import com.example.ecommerce.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
