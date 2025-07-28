package com.example.ecommerce.Services;

import com.example.ecommerce.modele.Client;
import com.example.ecommerce.modele.Product;
import com.example.ecommerce.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final StorageService storageService;
    public Client saveClientWithImage(Client client, MultipartFile file) {
        storageService.store(file);
        client.setImageClient(file.getOriginalFilename());
        return  clientRepository.save(client);
    }
    public Client save(Client client) {
        return clientRepository.save(client);
    }
        public void delete(long id) {
        clientRepository.deleteById(id);
    }
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    public Client update(Long id, Client newclient) {

        Client existingClient = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client non trouv<UNK> avec l'ID : " + id));
        existingClient.setLocalisation(newclient.getLocalisation());
        existingClient.setShippingAddress(newclient.getShippingAddress());
        existingClient.setPhoneNumber(newclient.getPhoneNumber());
        existingClient.setRegistrationDate(newclient.getRegistrationDate());
        existingClient.setEmail(newclient.getEmail());
        existingClient.setFirstName(newclient.getFirstName());
        existingClient.setLastName(newclient.getLastName());
        existingClient.setPassword(newclient.getPassword());
        return clientRepository.save(existingClient);

    }
}
