package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Orders;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;  // attention à la casse !

    @Autowired
    private ProductRepository productRepository;

    public void sendInvoice(Long orderId, String userEmail) throws Exception {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order non trouvé"));

        ByteArrayOutputStream pdfStream = generateInvoicePDF(order);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(userEmail);
        helper.setSubject("Votre facture pour la commande #" + order.getId());
        helper.setText("Bonjour " + order.getDeliveryFullName() + ",\n\nVeuillez trouver ci-joint votre facture.\n\nMerci !");

        helper.addAttachment("facture.pdf", new ByteArrayResource(pdfStream.toByteArray()));

        mailSender.send(message);
    }

    public ByteArrayOutputStream generateInvoicePDF(Orders order) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("Facture commande #" + order.getId()));
        document.add(new Paragraph("Nom : " + order.getDeliveryFullName()));
        document.add(new Paragraph("Email : " + order.getDeliveryEmail()));
        document.add(new Paragraph("Adresse : " + order.getDeliveryAddress()));
        document.add(new Paragraph("Téléphone : " + order.getDeliveryPhone()));
        document.add(new Paragraph(" "));

        // Le tableau aura 2 colonnes : Produit et Prix unitaire
        PdfPTable table = new PdfPTable(2);
        table.addCell("Produit");
        table.addCell("Prix unitaire");

        // Affichage dans la console du nombre de produits et leurs détails
        System.out.println("Nombre de produits dans la commande : " + order.getProducts().size());
        System.out.println("Liste des produits dans la commande :");
        for (Product p : order.getProducts()) {
            System.out.println("Produit ID=" + p.getId() + ", Nom=" + p.getName() + ", Prix unitaire=" + p.getPrice());
            table.addCell(p.getName());
            table.addCell(String.format("%.2f €", p.getPrice()));
        }

        double totalGeneral = order.getTotal() != null ? Double.parseDouble(order.getTotal()) : 0;

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Total général : " + String.format("%.2f €", totalGeneral)));

        document.close();
        return out;
    }

}
