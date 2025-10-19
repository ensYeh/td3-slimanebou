package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;

public class ListerDomaine implements Commande{

    private final Dns dns;
    private final String domaine;
    private boolean triParIP = false;


    public ListerDomaine(Dns dns, String domaine) {
        this.dns = dns;
        this.domaine = domaine;
    }

    @Override
    public void execute() {
        List<DnsItem> items = dns.getItems(domaine);
        if (items.isEmpty()) {
            System.out.println("Aucune machine dans le domaine " + domaine);
        } else {
            if (triParIP) {
                items.sort(Comparator.comparingLong(ListerDomaine::ipToLong));
            } else {
                items.sort(Comparator.comparing(i -> i.getNomMachine().getNom()));
            }
            for (DnsItem item : items) {
                System.out.println(item);
            }
        }
    }

    public void setTriParIP(boolean triParIP) {
        this.triParIP = triParIP;
    }

    public static long ipToLong(DnsItem item) {
        String[] parts = item.getAdresseIP().getIp().split("\\.");
        long res = 0;
        for (String p : parts) {
            res = res * 256 + Integer.parseInt(p);
        }
        return res;
    }
}
