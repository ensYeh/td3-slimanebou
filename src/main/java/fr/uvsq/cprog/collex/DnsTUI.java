package fr.uvsq.cprog.collex;
import java.util.Scanner;


public class DnsTUI {
    private final Dns dns;
    private final Scanner scanner;

    public DnsTUI(Dns dns) {
        this.dns = dns;
        this.scanner = new Scanner(System.in);
    }

    // Méthode principale pour lire la prochaine commande
    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();
        if (ligne.equalsIgnoreCase("exit") || ligne.equalsIgnoreCase("quit")) {
            return new Quitter();
        }

        String[] parts = ligne.split(" ");
        try {
            if (parts[0].equalsIgnoreCase("add")) {
                AdresseIP ip = new AdresseIP(parts[1]);
                NomMachine nom = new NomMachine(parts[2]);
                return new AjouterItem(dns, ip, nom);
            } else if (parts[0].equalsIgnoreCase("ls")) {
                boolean triParIP = parts.length > 1 && parts[1].equals("-a");
                String domaine = parts[triParIP ? 2 : 1];
                ListerDomaine cmd = new ListerDomaine(dns, domaine);
                cmd.setTriParIP(triParIP);  // passe l'info au ListerDomaine
                return cmd;
            } else if (ligne.matches("^(\\d{1,3}\\.){3}\\d{1,3}$")) {
                AdresseIP ip = new AdresseIP(ligne);
                return new RechercheNom(dns, ip);
            } else if (ligne.contains(".")) {
                NomMachine nom = new NomMachine(ligne);
                return new RechercheIP(dns, nom);
            }
        } catch (Exception e) {
            System.out.println("ERREUR : Commande invalide ou arguments incorrects !");
        }
        return null;
    }


    public void affiche(String message) {
        System.out.println(message);
    }
}
