package fr.uvsq.cprog.collex;

public class Quitter implements Commande{

    @Override
    public void execute() {
        System.out.println("Bay Bay !");
        System.exit(0);
    }
}
