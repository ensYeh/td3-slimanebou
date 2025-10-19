package fr.uvsq.cprog.collex;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.Assert.*;

public class DnsTUITest {

    @Test
    public void testQuitCommande() throws IOException {
        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Dns dns = new Dns(java.nio.file.Paths.get("test_dns.txt"));
        DnsTUI tui = new DnsTUI(dns);

        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof Quitter);
    }

    @Test
    public void testLsCommande() throws IOException {
        String input = "ls uvsq.fr\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Dns dns = new Dns(java.nio.file.Paths.get("test_dns.txt"));
        DnsTUI tui = new DnsTUI(dns);

        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof ListerDomaine);
    }
}
