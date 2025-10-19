package fr.uvsq.cprog.collex;

import org.junit.Test;

import static org.junit.Assert.*;

public class NomMachineTest {
    @Test
    public void testConstructeurValide() {
        NomMachine nm = new NomMachine("www.uvsq.fr");
        assertEquals("www.uvsq.fr", nm.getNomComplet());
        assertEquals("www", nm.getNom());
        assertEquals("uvsq.fr", nm.getDomaine());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurSansPoint() {
        // Doit lancer une exception si pas de point
        new NomMachine("invalidName");
    }

    @Test
    public void testToString() {
        NomMachine nm = new NomMachine("serveur.example.com");
        assertEquals("serveur.example.com", nm.toString());
    }

    @Test
    public void testEqualsEtHashCode() {
        NomMachine nm1 = new NomMachine("www.uvsq.fr");
        NomMachine nm2 = new NomMachine("www.uvsq.fr");
        NomMachine nm3 = new NomMachine("mail.uvsq.fr");

        assertEquals(nm1, nm2);
        assertEquals(nm1.hashCode(), nm2.hashCode());

        assertNotEquals(nm1, nm3);
        assertNotEquals(nm1.hashCode(), nm3.hashCode());
    }

    @Test
    public void testGetters() {
        NomMachine nm = new NomMachine("ftp.uvsq.fr");
        assertEquals("ftp", nm.getNom());
        assertEquals("uvsq.fr", nm.getDomaine());
        assertEquals("ftp.uvsq.fr", nm.getNomComplet());
    }
}
