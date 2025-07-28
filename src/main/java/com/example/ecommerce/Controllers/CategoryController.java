package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.CategoryService;
import com.example.ecommerce.modele.Category;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/getId/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/get")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }
}
