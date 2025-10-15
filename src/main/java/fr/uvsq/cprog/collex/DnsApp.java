package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class DnsApp {

    private DnsTUI tui;
    private Dns dns;

    public DnsApp(String cheminFichier) {
        try {
            // Initialise la base DNS et l'interface utilisateur
            this.dns = new Dns(Path.of(cheminFichier));
            this.tui = new DnsTUI(dns);
        } catch (IOException e) {
            System.out.println("ERREUR : Impossible de charger le fichier DNS !");
            System.exit(1);
        }
    }

    public void run() {
        System.out.println("Bienvenue dans le simulateur DNS !");
        while (true) {
            Commande cmd = tui.nextCommande(); // Récupère la prochaine commande
            if (cmd != null) {
                cmd.execute();                 // Exécute la commande
            } else {
                System.out.println("Commande invalide !");
            }
        }
    }



    public static void main( String[] args ) throws IOException {

        Dns dns = new Dns(Paths.get("dns.txt"));
        ListerDomaine cmd = new ListerDomaine(dns, "uvsq.fr");
        cmd.execute();  // doit afficher toutes les machines du domaine

        String fichierDNS = "dns.txt"; // Fichier contenant la base
        DnsApp app = new DnsApp(fichierDNS);
        app.run();
    }
}

