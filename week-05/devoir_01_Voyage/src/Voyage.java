import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class OptionVoyage {
    private String nom;
    private double prixForfaitaire;

    public OptionVoyage(String nom, double prixForfaitaire) {
        this.nom = nom;
        this.prixForfaitaire = prixForfaitaire;
    }

    public String getNom() {
        return nom;
    }

    public double prix() {
        return prixForfaitaire;
    }

    @Override
    public String toString() {
        return nom + " -> " + this.prix() + " CHF";
    }
}

class Sejour extends OptionVoyage {
    private int nombreDeNuits;
    private double prixParNuit;

    public Sejour(String nom, double prixForfaitaire, int nombreDeNuits, double prixParNuit) {
        super(nom, prixForfaitaire);
        this.nombreDeNuits = nombreDeNuits;
        this.prixParNuit = prixParNuit;
    }

    @Override
    public double prix() {
        return super.prix() + nombreDeNuits * prixParNuit;
    }
}

class Transport extends OptionVoyage {
    private boolean estLong = false;
    public static final double TARIF_LONG = 1500.0;
    public static final double TARIF_BASE = 200.0;

    public Transport(String nom, double prixForfaitaire, boolean... estLong) {
        super(nom, prixForfaitaire);
        if (estLong.length > 0) {
            this.estLong = estLong[0];
        }
    }

    @Override
    public double prix() {
        double prix = super.prix();
        if (!estLong) {
            return prix + TARIF_BASE;
        } else {
            return prix + TARIF_LONG;
        }
    }
}

class KitVoyage {
    private ArrayList<OptionVoyage> listeOptions;
    private String depart;
    private String destination;

    public KitVoyage(String depart, String destination) {
        this.listeOptions = new ArrayList<>();
        this.depart = depart;
        this.destination = destination;
    }

    public double prix() {
        double prixTotal = 0;
        for (OptionVoyage option : listeOptions) {
            prixTotal += option.prix();
        }
        return prixTotal;
    }

    public void ajouterOption(OptionVoyage optionVoyage) {
        if (optionVoyage != null) {
            this.listeOptions.add(optionVoyage);
        }
    }

    public void annuler() {
        this.listeOptions.clear();
    }

    public int getNbOptions() {
        return this.listeOptions.size();
    }

    @Override
    public String toString() {
        StringBuilder outputOptions = new StringBuilder();
        outputOptions.append("Voyage de " + depart + " à " + destination + ", avec pour options : " + "\n\r");

        for (OptionVoyage option : listeOptions) {
            outputOptions.append(" - " + option.toString() + "\n\r");

        }
        outputOptions.append("Prix total : " + this.prix() + " CHF" + "\n\r");
        return outputOptions.toString();
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("Séjour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidée : London by night", 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1


        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 50.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("Croisière", 1300.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2


        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");


        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}

