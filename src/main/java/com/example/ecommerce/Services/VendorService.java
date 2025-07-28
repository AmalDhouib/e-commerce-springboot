package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Product;
import com.example.ecommerce.modele.Vendor;
import com.example.ecommerce.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@AllArgsConstructor
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;
    private final StorageService storageService;


    public Vendor saveVendorWithImage(Vendor vendor, MultipartFile file) {
        storageService.store(file);
        vendor.setImageVendor(file.getOriginalFilename());
        return vendorRepository.save(vendor);
    }



    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor findById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendeur introuvable avec l'ID : " + id));
    }

    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

    public Vendor update(Long id, Vendor updatedVendor) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendeur introuvable avec l'ID : " + id));

        existingVendor.setShopName(updatedVendor.getShopName());
        existingVendor.setBusinessAddress(updatedVendor.getBusinessAddress());
        existingVendor.setWebsite(updatedVendor.getWebsite());
        existingVendor.setAverageRating(updatedVendor.getAverageRating());

        existingVendor.setFirstName(updatedVendor.getFirstName());
        existingVendor.setLastName(updatedVendor.getLastName());
        existingVendor.setEmail(updatedVendor.getEmail());
        existingVendor.setPassword(updatedVendor.getPassword());

        return vendorRepository.save(existingVendor);
    }
}
