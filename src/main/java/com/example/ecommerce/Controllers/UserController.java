package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services._UserService;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.modele._User;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final _UserService _UserService;


    @PostMapping("/save")
    public _User save(@RequestBody _User user) {
        return _UserService.save(user);
    }

    @GetMapping("/get")
    public List<_User> getUsers() {
        return _UserService.findAll();
    }
    @GetMapping("/get/{id}")
    public _User getById(@PathVariable Long id) {
        return _UserService.findById(id);

    }
    @PutMapping("/put/{id}")
    public _User put(@PathVariable Long id, @RequestBody _User user) {
        return _UserService.update(id, user);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        _UserService.delete(id);
    }
 }
