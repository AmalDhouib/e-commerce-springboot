package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Product;
import com.example.ecommerce.modele._User;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories._UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class _UserService {
    private final _UserRepository userRepository;



    public _User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<_User> findAll() {
        return userRepository.findAll();
    }
    public _User save(_User user) {
        return userRepository.save(user);
    }
    public void delete(Long id ) {
        userRepository.deleteById(id);
    }
    public _User update(Long id, _User newUser) {
        _User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());

        return userRepository.save(existingUser);
    }



}
