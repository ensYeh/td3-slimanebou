package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Path fichierDns = Paths.get("dns.txt");
        if (!Files.exists(fichierDns)) {
            Files.createFile(fichierDns);
        }

        System.out.println( "Hello World!" );
    }
}
