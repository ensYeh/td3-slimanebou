package fr.uvsq.cprog.collex;

public class RechercheNom implements Commande{

    private Dns dns;
    private AdresseIP ip;

    public RechercheNom(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            System.out.println(item.getNomMachine());
        } else {
            System.out.println("ERREUR : IP introuvable !");
        }
    }
    
}
