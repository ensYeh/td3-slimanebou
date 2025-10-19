package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {

    @Test
    public void testCreationEtGetters() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        AdresseIP ip = new AdresseIP("193.51.31.90");

        DnsItem item = new DnsItem(nom, ip);

        assertEquals(nom, item.getNomMachine());
        assertEquals(ip, item.getAdresseIP());
    }

    @Test
    public void testToString() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        AdresseIP ip = new AdresseIP("193.51.31.90");

        DnsItem item = new DnsItem(nom, ip);

        String attendu = "193.51.31.90 www.uvsq.fr";
        assertEquals(attendu, item.toString());
    }
}
