package fr.uvsq.cprog.collex;

import java.io.IOException;

public class AjouterItem implements Commande{

    private final Dns dns;
    private final AdresseIP ip;
    private final NomMachine nom;

    public AjouterItem(Dns dns, AdresseIP ip, NomMachine nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(ip, nom);
            System.out.println("Machine ajoutée avec succès !");
        } catch (IOException e) {
            System.out.println("ERREUR : Impossible d’écrire dans le fichier !");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
