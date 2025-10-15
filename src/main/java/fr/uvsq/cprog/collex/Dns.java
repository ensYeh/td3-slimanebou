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

    //  Récupérer un item par nom de machine
    public DnsItem getItem(NomMachine nomMachine) {
        for (DnsItem item : items) {
            if (item.getNomMachine().equals(nomMachine)) {
                return item;
            }
        }
        return null; // si pas trouvé
    }

    // Récupérer un item par adresse IP
    public DnsItem getItem(AdresseIP adresseIP) {
        for (DnsItem item : items) {
            if (item.getAdresseIP().equals(adresseIP)) {
                return item;
            }
        }
        return null; // si pas trouvé
    }


    // Récupérer tous les items d’un domaine
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> result = new ArrayList<>();
        for (DnsItem item : items) {
            if (item.getNomMachine().getDomaine().equals(domaine)) {
                result.add(item);
            }
        }
        return result;
    }

    // Ajouter une nouvelle entrée DNS

    public void addItem(AdresseIP ip, NomMachine nomMachine) throws IOException {
        // Vérifie si l'adresse ou le nom existe déjà
        if (getItem(ip) != null) {
            throw new IllegalArgumentException("ERREUR : L'adresse IP existe déjà !");
        }
        if (getItem(nomMachine) != null) {
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }

        DnsItem item = new DnsItem(nomMachine, ip);
        items.add(item);

        // Met à jour le fichier
        List<String> lignes = new ArrayList<>();
        for (DnsItem i : items) {
            lignes.add(i.getNomMachine().getNomComplet() + " " + i.getAdresseIP().getIp());
        }
        Files.write(fichierDns, lignes);
    }









}
