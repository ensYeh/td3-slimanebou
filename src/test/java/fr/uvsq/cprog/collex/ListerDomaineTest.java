package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListerDomaineTest {

    private Dns dns;

    @Before
    public void setUp() throws Exception {
        // Création d'un Dns "factice" avec des items en mémoire
        dns = new DnsInMemory();
        dns.addItem(new AdresseIP("193.51.25.12"), new NomMachine("ecampus.uvsq.fr"));
        dns.addItem(new AdresseIP("193.51.25.24"), new NomMachine("pikachu.uvsq.fr"));
        dns.addItem(new AdresseIP("193.51.31.90"), new NomMachine("www.uvsq.fr"));
        dns.addItem(new AdresseIP("193.51.31.154"), new NomMachine("poste.uvsq.fr"));
    }

    @Test
    public void testTriParNom() {
        ListerDomaine cmd = new ListerDomaine(dns, "uvsq.fr");
        cmd.setTriParIP(false);

        List<DnsItem> items = dns.getItems("uvsq.fr");
        // Tri manuel par nom pour comparer
        items.sort((a, b) -> a.getNomMachine().getNom().compareTo(b.getNomMachine().getNom()));

        assertEquals("ecampus.uvsq.fr", items.get(0).getNomMachine().getNomComplet());
        assertEquals("pikachu.uvsq.fr", items.get(1).getNomMachine().getNomComplet());
        assertEquals("poste.uvsq.fr", items.get(2).getNomMachine().getNomComplet());
        assertEquals("www.uvsq.fr", items.get(3).getNomMachine().getNomComplet());
    }

    @Test
    public void testTriParIP() {
        ListerDomaine cmd = new ListerDomaine(dns, "uvsq.fr");
        cmd.setTriParIP(true);

        List<DnsItem> items = dns.getItems("uvsq.fr");
        // Tri manuel par IP pour comparer
        items.sort((a, b) -> Long.compare(ListerDomaine.ipToLong(a), ListerDomaine.ipToLong(b)));

        assertEquals("193.51.25.12", items.get(0).getAdresseIP().getIp());
        assertEquals("193.51.25.24", items.get(1).getAdresseIP().getIp());
        assertEquals("193.51.31.90", items.get(2).getAdresseIP().getIp());
        assertEquals("193.51.31.154", items.get(3).getAdresseIP().getIp());
    }

    // Classe interne pour simuler un Dns sans fichier
    private static class DnsInMemory extends Dns {
        private final List<DnsItem> items = new ArrayList<>();

        public DnsInMemory() throws Exception { super(java.nio.file.Paths.get("fake.txt")); }

        @Override
        public void addItem(AdresseIP ip, NomMachine nomMachine) {
            items.add(new DnsItem(nomMachine, ip));
        }

        @Override
        public List<DnsItem> getItems(String domaine) {
            List<DnsItem> result = new ArrayList<>();
            for (DnsItem item : items) {
                if (item.getNomMachine().getDomaine().equals(domaine)) {
                    result.add(item);
                }
            }
            return result;
        }
    }
}
