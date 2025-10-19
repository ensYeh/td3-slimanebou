package fr.uvsq.cprog.collex;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdresseIPTest {

    @Test
    public void testConstructeurValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.getIp());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurInvalideFormat() {
        // IP invalide : lettres
        new AdresseIP("192.abc.0.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurInvalideOctet() {
        // IP invalide : octet > 255
        new AdresseIP("256.0.0.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurInvalideMoinsOctets() {
        // IP invalide : moins de 4 octets
        new AdresseIP("192.168.1");
    }

    @Test
    public void testToString() {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        assertEquals("10.0.0.1", ip.toString());
    }

    @Test
    public void testEqualsEtHashCode() {
        AdresseIP ip1 = new AdresseIP("8.8.8.8");
        AdresseIP ip2 = new AdresseIP("8.8.8.8");
        AdresseIP ip3 = new AdresseIP("1.1.1.1");

        assertEquals(ip1, ip2);
        assertEquals(ip1.hashCode(), ip2.hashCode());

        assertNotEquals(ip1, ip3);
        assertNotEquals(ip1.hashCode(), ip3.hashCode());
    }
}
