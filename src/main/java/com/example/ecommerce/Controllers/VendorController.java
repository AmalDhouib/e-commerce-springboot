package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.VendorService;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.modele.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/vendor")
@AllArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @PostMapping("/save")
    public Vendor save(@RequestBody Vendor vendor) {
        return vendorService.save(vendor);
    }

    @GetMapping("/getId/{id}")
    public Vendor getById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @GetMapping("/get")
    public List<Vendor> findAll() {
        return vendorService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        vendorService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Vendor update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.update(id, vendor);
    }
    @PostMapping("/createwithimage")
    public Vendor createVendorWithImage(
            @ModelAttribute Vendor vendor,
            @RequestPart("file") MultipartFile file) {
        return vendorService.saveVendorWithImage(vendor, file);
    }


}
