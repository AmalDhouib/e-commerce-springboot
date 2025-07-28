package com.example.ecommerce.Controllers;

import com.example.ecommerce.Request.ProductDTO;
import com.example.ecommerce.Services.ProductService;
import com.example.ecommerce.modele.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
    @GetMapping("getId/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }
    @GetMapping("get")
    public List<Product> findAll() {
        return productService.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        productService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Product update(@PathVariable Long id,
                          @ModelAttribute ProductDTO productDTO,
                          @RequestPart(value = "file", required = false) MultipartFile file) {
        return productService.update(id, productDTO, file);
    }



    @PostMapping("/createwithimage/{id}")
    public Product createproductwithimage(@PathVariable Long id , @ModelAttribute ProductDTO product, @RequestPart(value = "file", required = false)MultipartFile file) {
        return productService.createNewProductWithImage(id,product,file);
    }
//@ModelAttribute ->Elle indique à Spring de lier automatiquement les champs d’un formulaire HTML (soumis en POST) à un objet Java (Product ici).
//
//Les champs du formulaire (nom, prix, description, etc.) sont automatiquement remplis dans l’objet product.

// @RequestPart MultipartFile	Récupérer un fichier envoyé avec le formulaire (multipart/form-data)
   @GetMapping("/findctegory/{name}")
    public List<Product> findctegory(@PathVariable String name){
        return productService.searchByCategory(name);
   }
}
