package com.example.ecommerce.Controllers;

import com.example.ecommerce.Services.ClientService;
import com.example.ecommerce.modele.Client;
import com.example.ecommerce.modele.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/save")
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/getId/{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/get")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Client update(@PathVariable Long id ,@RequestBody Client client) {
        return clientService.update(id ,client);
    }
    @PostMapping("/createwithimage")
    public Client createproductwithimage(@ModelAttribute Client client,@RequestPart("file") MultipartFile file ) {
        return clientService.saveClientWithImage(client, file);
    }
}

