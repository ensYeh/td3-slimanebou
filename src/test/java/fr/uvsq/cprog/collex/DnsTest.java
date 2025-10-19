package fr.uvsq.cprog.collex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class DnsTest {

    private Path tempFile;
    private Dns dns;

    @Before
    public void setUp() throws IOException {
        // Crée un fichier temporaire pour simuler dns.txt
        tempFile = Files.createTempFile("dns-test", ".txt");
        Files.write(tempFile, List.of(
                "www.uvsq.fr 193.51.31.90",
                "poste.uvsq.fr 193.51.31.154",
                "ecampus.uvsq.fr 193.51.25.12"
        ));

        dns = new Dns(tempFile);
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testGetItemParNom() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        DnsItem item = dns.getItem(nom);
        assertNotNull(item);
        assertEquals("193.51.31.90", item.getAdresseIP().getIp());
    }

    @Test
    public void testGetItemParAdresseIP() {
        AdresseIP ip = new AdresseIP("193.51.31.154");
        DnsItem item = dns.getItem(ip);
        assertNotNull(item);
        assertEquals("poste.uvsq.fr", item.getNomMachine().getNomComplet());
    }

    @Test
    public void testGetItemsParDomaine() {
        List<DnsItem> items = dns.getItems("uvsq.fr");
        assertEquals(3, items.size());
        assertTrue(items.stream().anyMatch(i -> i.getNomMachine().getNomComplet().equals("www.uvsq.fr")));
        assertTrue(items.stream().anyMatch(i -> i.getNomMachine().getNomComplet().equals("poste.uvsq.fr")));
    }

    @Test
    public void testAddItem() throws IOException {
        NomMachine newNom = new NomMachine("pikachu.uvsq.fr");
        AdresseIP newIp = new AdresseIP("193.51.25.24");
        dns.addItem(newIp, newNom);

        DnsItem retrieved = dns.getItem(newNom);
        assertNotNull(retrieved);
        assertEquals("193.51.25.24", retrieved.getAdresseIP().getIp());

        // Vérifie que le fichier temporaire est mis à jour
        List<String> lines = Files.readAllLines(tempFile);
        assertTrue(lines.stream().anyMatch(l -> l.contains("pikachu.uvsq.fr 193.51.25.24")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemAdresseExistante() throws IOException {
        AdresseIP ipExistante = new AdresseIP("193.51.31.90");
        NomMachine nom = new NomMachine("nouveau.uvsq.fr");
        dns.addItem(ipExistante, nom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemNomExistant() throws IOException {
        NomMachine nomExistante = new NomMachine("www.uvsq.fr");
        AdresseIP ip = new AdresseIP("193.51.50.50");
        dns.addItem(ip, nomExistante);
    }
}
