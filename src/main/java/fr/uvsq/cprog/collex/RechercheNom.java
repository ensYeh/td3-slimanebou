package fr.uvsq.cprog.collex;

public class RechercheNom implements Commande{

    private final Dns dns;
    private final AdresseIP ip;

    public RechercheNom(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            System.out.println(item.getNomMachine().getNomComplet());
        } else {
            System.out.println("ERREUR : IP introuvable !");
        }
    }
}
