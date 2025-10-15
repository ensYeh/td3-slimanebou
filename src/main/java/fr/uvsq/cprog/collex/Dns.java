package fr.uvsq.cprog.collex;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Dns {
    private List<DnsItem> items;   // Liste de toutes les entrées DNS
    private Path fichierDns;       // Fichier texte contenant la base


    // constructeur qui chargera la base de données
    public Dns(Path fichierDns) throws IOException {
        this.fichierDns = fichierDns;
        this.items = new ArrayList<>();
        chargerBase();  // Charge le fichier au démarrage
    }


    //  Chargement de la base depuis le fichier
    private void chargerBase() throws IOException {
        if (!Files.exists(fichierDns)) {
            Files.createFile(fichierDns);
        }
        List<String> lignes = Files.readAllLines(fichierDns);
        for (String ligne : lignes) {
            if (!ligne.trim().isEmpty()) {
                String[] parts = ligne.split(" ");
                AdresseIP ip = new AdresseIP(parts[1]);
                NomMachine nom = new NomMachine(parts[0]);
                items.add(new DnsItem(nom, ip));
            }
        }
    }








}
