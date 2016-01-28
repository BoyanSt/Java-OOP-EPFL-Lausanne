import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;

abstract class Employe{
    private String nom;
    private String prenom;
    private int age;
    private String dateDEntree;

    public Employe(String nom, String prenom, int age, String dateDEntree) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.dateDEntree = dateDEntree;
    }

    public String getNom() {
        StringBuilder output = new StringBuilder();

        String type = this.getClass().getName();

        switch (type){
            case "Vendeur" : output.append("Le vendeur "); break;
            case "Representant" : output.append("Le représentant "); break;
            case "Technicien" : output.append("Le technicien "); break;
            case "Manutentionnaire" : output.append("Le manut. "); break;
            case "TechnARisque" : output.append("Le technicien "); break;
            case "ManutARisque" : output.append("Le manut. "); break;
        }

        output.append( nom + " " + prenom );
        return output.toString();
    }

    public abstract double calculerSalaire();
}

class FrontEndEmploye extends Employe{
    private final double PROCENTAGE_CHIFFRE_AFFAIRE = 0.2;
    private double chiffreDAffaire;

    public FrontEndEmploye(String nom, String prenom, int age, String dateDEntree, double chiffreDAffaire) {
        super(nom, prenom, age, dateDEntree);
        this.chiffreDAffaire = chiffreDAffaire;
    }

    @Override
    public double calculerSalaire() {
        return chiffreDAffaire * PROCENTAGE_CHIFFRE_AFFAIRE;
    }
}

class Vendeur extends FrontEndEmploye{

    protected final double BONUS_SALAIRE = 400;

    public Vendeur(String nom, String prenom, int age, String dateDEntree, double chiffreDAffaire) {
        super(nom, prenom, age, dateDEntree, chiffreDAffaire);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + BONUS_SALAIRE;
    }
}

class Representant extends FrontEndEmploye{
    protected final double BONUS_SALAIRE = 800;

    public Representant(String nom, String prenom, int age, String dateDEntree, double chiffreDAffaire) {
        super(nom, prenom, age, dateDEntree, chiffreDAffaire);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + BONUS_SALAIRE;
    }
}

class Technicien extends Employe{
    private final int FACTOR_PAYEMENT = 5;
    private double nombreUnite;

    public Technicien(String nom, String prenom, int age, String dateDEntree, double nombreUnite) {
        super(nom, prenom, age, dateDEntree);
        this.nombreUnite = nombreUnite;
    }

    @Override
    public double calculerSalaire() {
        return nombreUnite * FACTOR_PAYEMENT;
    }
}

class Manutentionnaire extends Employe{
    private final int PAYMENT_HEUR = 65;
    private double nombreHeures;

    public Manutentionnaire(String nom, String prenom, int age, String dateDEntree, double nombreHeures) {
        super(nom, prenom, age, dateDEntree);
        this.nombreHeures = PAYMENT_HEUR;
    }

    @Override
    public double calculerSalaire() {
        return nombreHeures * 65;
    }
}

class TechnARisque extends Technicien implements primeMensuelleRisque{
    public TechnARisque(String nom, String prenom, int age, String dateDEntree, double nombreUnite) {
        super(nom, prenom, age, dateDEntree, nombreUnite);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + ajoutPrime();
    }
}

class ManutARisque extends Manutentionnaire implements primeMensuelleRisque{
    public ManutARisque(String nom, String prenom, int age, String dateDEntree, double nombreHeures) {
        super(nom, prenom, age, dateDEntree, nombreHeures);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + ajoutPrime();
    }
}

interface primeMensuelleRisque{
    static int prime = 200;

    default double ajoutPrime(){
        return prime;
    }
}

class Personnel{
    private ArrayList<Employe> listeEmployes;

    public Personnel() {
        this.listeEmployes = new ArrayList<>();
    }

    public void ajouterEmploye(Employe employe){
        listeEmployes.add(employe);
    }

    public void afficherSalaires(){
        for (Employe employe : listeEmployes) {
            System.out.print(employe.getNom());
            System.out.println(" gagne " + employe.calculerSalaire() + " francs.");
        }
    }

    public double salaireMoyen(){
        double totalSalaire = 0;
        for (Employe employe : listeEmployes) {
            totalSalaire += employe.calculerSalaire();
        }

        return totalSalaire/listeEmployes.size();
    }
}

public class ex_03_Salaires {
    public static void main(String[] args) {

        Personnel p = new Personnel();

        p.ajouterEmploye(new Vendeur("Pierre", "Business", 45, "1995", 30000));
        p.ajouterEmploye(new Representant("Léon", "Vendtout", 25, "2001", 20000));
        p.ajouterEmploye(new Technicien("Yves", "Bosseur", 28, "1998", 1000));
        p.ajouterEmploye(new Manutentionnaire("Jeanne", "Stocketout", 32, "1998", 45));
        p.ajouterEmploye(new TechnARisque("Jean", "Flippe", 28, "2000", 1000));
        p.ajouterEmploye(new ManutARisque("Al", "Abordage", 30, "2001", 45));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");

    }

}
