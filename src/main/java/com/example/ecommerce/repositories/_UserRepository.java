package com.example.ecommerce.repositories;

import com.example.ecommerce.modele._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface _UserRepository extends JpaRepository<_User, Long> {
    Optional<_User> findByEmail(String email);

}
