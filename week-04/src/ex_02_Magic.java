import java.util.ArrayList;

abstract class Carte {
    private int cout = 0;

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public Carte(int cout) {
        this.cout = cout;
    }

    public abstract void afficher();

}

class Creature extends Carte {
    private String nom;
    private int pointsDegat;
    private int pointsVie;

    public Creature(int cout, String nom, int pointsDegat, int pointsVie) {
        super(cout);
        this.setCout(cout);
        this.nom = nom;
        this.pointsDegat = pointsDegat;
        this.pointsVie = pointsVie;
    }

    @Override
    public void afficher() {
        System.out.println("Une " + this.getClass().getName());
        System.out.println("Nom : " + nom);
        System.out.println("Points de degats : " + pointsDegat);
        System.out.println("Points de vie : " + pointsVie);
    }
}

class Terrain extends Carte {
    char couleur;

    public Terrain(char couleur) {
        super(0);
        this.couleur = couleur;
    }

    @Override
    public void afficher() {
        System.out.println("Un " + this.getClass().getName());
        System.out.println("Couleur : " + couleur);
    }
}

class Sortilege extends Carte {
    private String nom;
    private String explication;

    public Sortilege(int cout, String nom, String explication) {
        super(cout);
        this.setCout(cout);
        this.nom = nom;
        this.explication = explication;
    }

    @Override
    public void afficher() {
        System.out.println("Une " + this.getClass().getName());
        System.out.println("Nom : " + nom);
        System.out.println("Explication : " + explication);
    }
}

class Jeu {
    private int numCartes;
    private Carte[] cartes;

    public Jeu(int numeroCartes) {
        this.cartes = new Carte[numeroCartes];
        this.numCartes = numeroCartes;
    }

    public void piocher(Carte carte) {
        int i = 0;

        while ((i < numCartes) && (cartes[i] != null)) {
            i++;
        }

        if (i < numCartes) {
            cartes[i] = carte;
        } else {
            System.out.println("Nombre maximal de cartes atteint");
        }
    }

    public void afficher() {
        for (int i = 0; i < numCartes; i++) {
            if (cartes[i] != null) {
                cartes[i].afficher();
            }

        }
    }

    public void joue() {

        int i = 0;

        while (cartes[i] == null && i < numCartes) {
            i++;
        }

        if (i < numCartes && cartes[i] != null) {
            System.out.println("je jeu la carte...");
            cartes[i].afficher();
            cartes[i] = null;
        } else {
            System.out.println("Plus de cartes a jouer.");
        }
    }
}

public class ex_02_Magic {
    public static void main(String[] args) {
        Jeu maMain = new Jeu(10);

        maMain.piocher(new Terrain('b'));
        maMain.piocher(new Creature(6, "Golem", 4, 6));
        maMain.piocher(new Sortilege(1, "Croissance Gigantesque",
                "La créature ciblée gagne +3/+3 jusqu'à la fin du tour"));

        System.out.println("Là, j'ai en stock :");
        maMain.afficher();
        maMain.joue();
        System.out.println("===============================");
        maMain.afficher();
    }
}
