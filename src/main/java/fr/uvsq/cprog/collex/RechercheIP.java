package fr.uvsq.cprog.collex;

public class RechercheIP implements Commande{

    private Dns dns;
    private NomMachine nom;

    public RechercheIP(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(nom);
        if (item != null) {
            System.out.println(item.getAdresseIP());
        } else {
            System.out.println("ERREUR : Nom introuvable !");
        }
    }
}
