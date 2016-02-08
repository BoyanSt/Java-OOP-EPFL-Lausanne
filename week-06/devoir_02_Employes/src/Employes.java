


/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//"Nous avons un nouvel employé : "
//"Montant de la prime souhaitée par "
//"  A voyagé "
//" jours et apporté "
//"  A corrigé "
//"  A mené à bien "

class Employe{
    private final String nom;
    private double revenueMansuel;
    private int tauxOccupation = 100;
    private double prime = 0;

    public Employe(String nom, double revenueMansuel, int...tauxOccupation) {
        this.nom = nom;
        this.revenueMansuel = revenueMansuel;
        if (tauxOccupation.length > 0){
            this.setTauxOccupation(tauxOccupation[0]);
        }
        System.out.print("Nous avons un nouvel employé : " + this.getNom() +",");
    }

    public String getNom() {
        return nom;
    }

    public double getRevenueMansuel() {
        return revenueMansuel;
    }

    public int getTauxOccupation() {
        return tauxOccupation;
    }

    public void setTauxOccupation(int tauxOccupation) {
        if (tauxOccupation < 10){
            this.tauxOccupation = 10;
        } else if (tauxOccupation > 100){
            this.tauxOccupation = 100;
        } else {
            this.tauxOccupation = tauxOccupation;
        }
    }

    public void demandePrime(){
        Scanner scn = new Scanner(System.in);
        double prime = 0;
        double summeMaxPrime = this.revenuAnnuel() * 0.02;
        String input = null;

        for (int i = 0; i < 5; i++) {
            System.out.println("Montant de la prime souhaitée par " + this.nom + " ?");
            input = scn.next();

            if (input.charAt(input.length()-1)=='.'){
                input = input.substring(0,input.length()-2);
            }
            try {
                prime = Double.parseDouble(input);

                if ( !(prime > summeMaxPrime || prime < 0)){
                    this.prime = prime;
                } else {
                    System.out.println("Trop cher!");
                }
            } catch (Exception ex){
                System.out.println("Vous devez introduire un nombre!");
            }
        }
    }

    public double revenuAnnuel(){
        int nombreDeMoisAnnee = 12;
        return nombreDeMoisAnnee * this.revenueMansuel * this.tauxOccupation/100.0 + this.prime;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(nom + " :" + "\n\r");
        output.append("  Taux d'occupation : " + tauxOccupation + "%. ");
        output.append("Salaire annuel : " + String.format("%.2f",this.revenuAnnuel()) + " francs");

        if (this.prime !=0){
            output.append(String.format(", Prime : %.2f.", this.prime) + "\n\r");
        } else {
            output.append(". \n\r");
        }
        return output.toString();
    }
}
class Manager extends Employe{
    private int jourDeVoyage;
    private int nombreDeClient;
    public static final int FACTEUR_GAIN_CLIENT = 500;
    public static final int FACTEUR_GAIN_VOYAGE = 100;

    public Manager(String nom, double revenueMansuel,
                   int jourDeVoyage, int nombreDeClient, int...tauxOccupation) {
        super(nom, revenueMansuel, tauxOccupation);
        this.jourDeVoyage = jourDeVoyage;
        this.nombreDeClient = nombreDeClient;
        System.out.println(" c'est un manager.");
    }

    @Override
    public double revenuAnnuel() {
        return super.revenuAnnuel() + this.jourDeVoyage * FACTEUR_GAIN_VOYAGE
                + this.nombreDeClient * FACTEUR_GAIN_CLIENT;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("  A voyagé %d jours et apporté %d nouveaux clients.",
                this.jourDeVoyage, this.nombreDeClient) + "\n\r";
    }
}

class Testeur extends Employe{
    private int nombreDeErreurs;
    public static final int FACTEUR_GAIN_ERREURS = 10;

    public Testeur(String nom, double revenueMansuel, int nombreDeErreurs, int...tauxOccupation) {
        super(nom, revenueMansuel, tauxOccupation);
        this.nombreDeErreurs = nombreDeErreurs;
        System.out.println(" c'est un testeur.");
    }

    @Override
    public double revenuAnnuel() {
        return super.revenuAnnuel() +
                this.nombreDeErreurs * FACTEUR_GAIN_ERREURS;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("  A corrigé %d erreurs.", this.nombreDeErreurs) + "\n\r";
    }
}

class Programmeur extends Employe{
    private int nombreDeProjets;
    public static final int FACTEUR_GAIN_PROJETS = 200;

    public Programmeur(String nom, double revenueMansuel, int nombreDeProjets, int...tauxOccupation) {
        super(nom, revenueMansuel, tauxOccupation);
        this.nombreDeProjets = nombreDeProjets;
        System.out.println(" c'est un programmeur.");
    }

    @Override
    public double revenuAnnuel() {
        return super.revenuAnnuel() +
                this.nombreDeProjets * FACTEUR_GAIN_PROJETS;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("  A mené à bien %d projets", this.nombreDeProjets) + "\n\r";
    }
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

