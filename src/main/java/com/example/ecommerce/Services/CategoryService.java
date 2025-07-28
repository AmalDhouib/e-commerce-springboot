package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Category;
import com.example.ecommerce.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));

    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
    public Category update(Long id ,Category newcategory) {
        Category existingcategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        existingcategory.setName(newcategory.getName());
        return categoryRepository.save(existingcategory);

    }
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
