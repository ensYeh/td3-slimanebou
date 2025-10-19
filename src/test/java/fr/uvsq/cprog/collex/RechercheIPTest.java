package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class RechercheIPTest {

    private Dns dns;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws Exception {
        // Redirige la sortie console pour capturer les affichages
        System.setOut(new PrintStream(outContent));

        // Crée un fichier DNS temporaire
        Path tempFile = Files.createTempFile("dns", ".txt");
        Files.write(tempFile, ("www.uvsq.fr 193.51.31.90\n" +
                "poste.uvsq.fr 193.51.31.154").getBytes());

        // Initialise la base DNS
        dns = new Dns(tempFile);
    }

    @Test
    public void testExecute_nomExistant() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        RechercheIP cmd = new RechercheIP(dns, nom);
        cmd.execute();

        assertEquals("193.51.31.90" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testExecute_nomInexistant() {
        NomMachine nom = new NomMachine("inconnu.uvsq.fr");
        RechercheIP cmd = new RechercheIP(dns, nom);
        cmd.execute();

        assertEquals("ERREUR : Nom introuvable !" + System.lineSeparator(), outContent.toString());
    }
}
