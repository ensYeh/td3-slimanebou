package fr.uvsq.cprog.collex;

public class NomMachine {

    private String nomComplet; // www.uvsq.fr
    private String nom;         // www
    private String domaine;     // uvsq.fr

    public NomMachine(String nomComplet) {
        // la chaine contient au moins un point sinon erreur
        if (!nomComplet.contains(".")) {
            throw new IllegalArgumentException("Nom de machine invalide : " + nomComplet);
        }
        this.nomComplet = nomComplet;
        String[] parts = nomComplet.split("\\.", 2);
        this.nom = parts[0];
        this.domaine = parts[1];
    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return domaine;
    }


    public String getNomComplet() {
        return nomComplet;
    }



}
