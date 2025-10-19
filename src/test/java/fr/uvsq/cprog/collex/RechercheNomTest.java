package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class RechercheNomTest {

    private Dns dns;

    @Before
    public void setUp() throws IOException {
        // Crée un fichier temporaire DNS
        Path tempFile = Files.createTempFile("dns", ".txt");
        Files.write(tempFile, java.util.List.of(
                "www.uvsq.fr 193.51.31.90",
                "ecampus.uvsq.fr 193.51.25.12"
        ));

        dns = new Dns(tempFile);
    }

    @Test
    public void testExecute_ipExists() {
        AdresseIP ip = new AdresseIP("193.51.31.90");
        RechercheNom cmd = new RechercheNom(dns, ip);

        // On capture la sortie console
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        cmd.execute();

        String sortie = out.toString().trim();
        assertEquals("www.uvsq.fr", sortie);
    }

    @Test
    public void testExecute_ipNotExists() {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        RechercheNom cmd = new RechercheNom(dns, ip);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        cmd.execute();

        String sortie = out.toString().trim();
        assertEquals("ERREUR : IP introuvable !", sortie);
    }
}
