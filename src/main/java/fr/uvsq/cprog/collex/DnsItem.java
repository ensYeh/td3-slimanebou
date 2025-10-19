package fr.uvsq.cprog.collex;

public class DnsItem {

    private final NomMachine nomMachine;
    private final AdresseIP adresseIP;


    public DnsItem(NomMachine nomMachine, AdresseIP adresseIP) {
        this.nomMachine = nomMachine;
        this.adresseIP = adresseIP;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    public AdresseIP getAdresseIP() {
        return adresseIP;
    }

    @Override
    public String toString() {
        return getAdresseIP() + " " + getNomMachine();
    }

}
