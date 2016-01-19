import java.util.ArrayList;
import java.util.Collections;


class Cellule {
    /*****************************************************
     * Compléter le code à partir d'ici
     *****************************************************/
    private String nom;
    private double taille;
    private int energie;
    private String couleur;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public int getEnergie() {
        return energie;
    }

    public String getCouleur() {
        return couleur;
    }

    public double getTaille() {
        return taille;
    }

    public Cellule(){
        nom  =  "Pyrobacculum";
        taille  = 10;
        energie  = 5;
        couleur  = "verte";
    }

    public Cellule(String nom, double taille, int energie, String couleur) {
        this.nom = nom;
        this.taille = taille;
        this.energie = energie;
        this.couleur = couleur;
    }

    public Cellule(Cellule uneCellule){
        this(uneCellule.getNom(),uneCellule.getTaille(),
                uneCellule.getEnergie(),uneCellule.getCouleur());
    }

    public void affiche(){
        System.out.print(nom + ", taille = " + taille + " microns, ");
        System.out.printf("énergie = %d, couleur = %s",energie,couleur).println();
    }

    public Cellule division(){
        Cellule clone = new Cellule(this);

        this.energie -= 1;
        clone.changeCouleur();
        return clone;
    }

    public void changeCouleur(){
        switch (this.couleur){
            case "verte" : this.couleur = "bleue"; break;
            case "bleue" : this.couleur = "rouge"; break;
            case "rouge" : this.couleur = "rose bonbon"; break;
            case "violet" : this.couleur = "verte"; break;
            default: this.couleur += " fluo"; break;
        }
    }
}

class Petri {
    private ArrayList<Cellule> listeCellule;

    public Petri() {
        listeCellule = new ArrayList<>();
    }

    public ArrayList<Cellule> getListeCellule() {
        return listeCellule;
    }

    public void ajouter(Cellule cellule) {
        this.listeCellule.add(cellule);
    }

    public void ajouter() {
        Cellule cellule = new Cellule();
        this.listeCellule.add(cellule);
    }

    public void ajouter(String nom, double taille, int energie, String couleur) {
        Cellule cellule = new Cellule(nom, taille, energie, couleur);
        this.listeCellule.add(cellule);
    }

    public void affiche() {
        for (Cellule cellule : getListeCellule()) {
            cellule.affiche();
        }
    }
    public void evolue(){
        ArrayList<Cellule> listeClones = new ArrayList<>();

        for (Cellule cellule : listeCellule) {
            Cellule clone = cellule.division();
            listeClones.add(clone);
        }

        for (Cellule clone : listeClones) {
            listeCellule.add(clone);
        }

        for (int i = 0; i < listeCellule.size(); i++) {
            if (listeCellule.get(i).getEnergie()<=0){
                int last = listeCellule.size()-1;
                Collections.swap(listeCellule, i, last);
                listeCellule.remove(last);
            }
        }
    }
}
/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
class SimulationPetri {
    public static void main(String[] args) {
        Petri assiette = new Petri();

        assiette.ajouter(new Cellule());
        assiette.ajouter(new Cellule("PiliIV", 4, 1, "jaune"));
        System.out.println("Population avant évolution :");
        assiette.affiche();

        assiette.evolue();
        System.out.println("Population après évolution :");
        assiette.affiche();
    }
}

