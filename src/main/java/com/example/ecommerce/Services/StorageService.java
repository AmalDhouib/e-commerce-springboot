package com.example.ecommerce.Services;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class StorageService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload-dir");
    public MultipartFile store(MultipartFile file) {
        //MultipartFile est une interface de Spring Framework qui représente un fichier envoyé via un formulaire HTML (typiquement un formulaire avec enctype="multipart/form-data").
        try {
            // Vérifie si le fichier existe déjà
            Path destinationFile = this.rootLocation.resolve(file.getOriginalFilename());
            // this.rootLocation est le répertoire où les fichiers doivent être stockés (ex: upload-dir).
            //
            // file.getOriginalFilename() : récupère le nom original du fichier tel qu’il a été envoyé.
            //
            // resolve(...) : construit le chemin complet où le fichier sera enregistré.
            //
            // Par exemple : "upload-dir/photo.jpg"
            if (Files.exists(destinationFile)) {
                Files.delete(destinationFile);  // Supprime le fichier existant
            }
            // Sauvegarde du fichier
            Files.copy(file.getInputStream(), destinationFile);
            //Copie le contenu du fichier reçu (via getInputStream()) vers le chemin destinationFile.
            //
            //Cela effectue réellement l’enregistrement du fichier sur le disque.
        } catch (Exception e) {
            log.error("Erreur lors de l'enregistrement du fichier", e);
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier !");
        }
        return file;
    }
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            //Convertit le chemin du fichier en URI (chemin absolu sous forme d'URL).
            //
            //Crée un objet UrlResource, un type de Resource basé sur une URL/URI.
            //
            //Cela permet à Spring de manipuler le fichier comme une ressource téléchargeable.

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
        //Resource dans Spring
        //org.springframework.core.io.Resource est une interface très pratique dans Spring :
        //
        //Elle permet de manipuler des fichiers, URLs, chemins dans le classpath, etc. de façon uniforme.
        //
        //Elle est souvent utilisée dans des contrôleurs REST pour télécharger un fichier via HTTP
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
    public void deleteFile(String filePath) {
        try {
            Path file = Paths.get(filePath);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la suppression du fichier : " + filePath);
        }
    }
}