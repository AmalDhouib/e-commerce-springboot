package com.example.ecommerce.Services;

import com.example.ecommerce.Request.ProductDTO;
import com.example.ecommerce.modele.Category;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;//injection par constructeur

    @Autowired
    private CategoryRepository categoryRepository;

    private final StorageService storageService;
    @Autowired
    private CategoryService categoryService;

    public Product createNewProductWithImage(Long categoryId, ProductDTO dto, MultipartFile file) {
        // 1. Vérifier que la catégorie existe
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        // 2. Sauvegarder le fichier
        MultipartFile storedFile = storageService.store(file);
        String filename = storedFile.getOriginalFilename();

        // 3. Créer manuellement une nouvelle instance de Product
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setMaterial(dto.getMaterial());
        product.setTechnique(dto.getTechnique());
        product.setOriginCountry(dto.getOriginCountry());
        product.setStockQuantity(dto.getStockQuantity());
        product.setEcoFriendly(dto.getEcoFriendly());
        product.setLimitedEdition(dto.getLimitedEdition());
        product.setImage(filename);
        product.setCategory(category);
        product.setProductionTime(dto.getProductionTime());

        // 4. Sauvegarder
        return productRepository.save(product);
    }

    public List<Product> searchByCategory(String namecategory) {
        // Rechercher la catégorie par son nom
        Category category = categoryRepository.findByName(namecategory)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        // Retourner tous les produits liés à cette catégorie
        return productRepository.findByCategory(category);
    }







    public Product save(Product product) {//ajouter product et return product
        return productRepository.save(product);//methode prédefinie de repository
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'ID : " + id));
    }
   //si on utilise .get() si nest pas trouvé pas de probleme
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    public Product update(Long id, ProductDTO updatedProduct, MultipartFile file) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        // Mise à jour des champs classiques
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setMaterial(updatedProduct.getMaterial());
        existingProduct.setTechnique(updatedProduct.getTechnique());
        existingProduct.setOriginCountry(updatedProduct.getOriginCountry());
        existingProduct.setEcoFriendly(updatedProduct.getEcoFriendly());
        existingProduct.setLimitedEdition(updatedProduct.getLimitedEdition());
        existingProduct.setProductionTime(updatedProduct.getProductionTime());

        // Si un nouveau fichier image est uploadé, on remplace
        if (file != null && !file.isEmpty()) {
            MultipartFile storedFile = storageService.store(file);
            String newFilename = storedFile.getOriginalFilename();
            existingProduct.setImage(newFilename);
        }
        // Sinon on garde l'ancienne image intacte (pas de changement ici)

        // Mise à jour catégorie si besoin
        if (updatedProduct.getCategory() != null && updatedProduct.getCategory().getId() != null) {
            Long categoryId = updatedProduct.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée avec l'ID : " + categoryId));
            existingProduct.setCategory(category);
        }


        return productRepository.save(existingProduct);
    }




}
