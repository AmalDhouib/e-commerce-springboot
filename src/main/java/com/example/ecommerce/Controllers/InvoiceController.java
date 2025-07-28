package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.InvoiceEmailService;
import com.example.ecommerce.modele.Orders;
import com.example.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
@CrossOrigin("*")

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceEmailService invoiceEmailService;
    private OrderRepository OrderRepository;
    @PostMapping("/send")
    public ResponseEntity<String> sendInvoice(
            @RequestParam Long orderId,
            @RequestParam String email) {
        try {
            invoiceEmailService.sendInvoice(orderId, email);
            return ResponseEntity.ok("Facture envoyée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur : " + e.getMessage());
        }
    }
    @GetMapping("/{orderId}/download")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long orderId) {
        try {
            Orders order = OrderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

            ByteArrayOutputStream pdfStream = invoiceEmailService.generateInvoicePDF(order);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=facture_" + orderId + ".pdf")
                    .header("Content-Type", "application/pdf")
                    .body(pdfStream.toByteArray());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}