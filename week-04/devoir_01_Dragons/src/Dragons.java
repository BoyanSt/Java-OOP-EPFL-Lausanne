// Utility.distance(x,y) retourne la distance entre x et y
class Utility {
    static int distance(int x, int y) {
        return Math.abs(x - y);
    }

}

class Creature {
    /*****************************************************
     * Compléter le code à partir d'ici
     *****************************************************/
    private String nom;
    private int niveau;
    private int pointsDeVie;
    private int force;
    private int position = 0;

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public Creature(String nom, int niveau, int pointsDeVie, int force, int... position) {
        this.nom = nom;
        this.niveau = niveau;
        this.pointsDeVie = pointsDeVie;
        this.force = force;

        if (position.length > 0) {
            this.position = position[0];
        }
    }

    public int position() {
        return this.position;
    }

    public boolean vivant() {
        return this.pointsDeVie > 0;
    }

    public int pointsAttaque() {
        if (this.vivant()) {
            return this.niveau * this.force;
        } else {
            return 0;
        }
    }

    public void deplacer(int num) {
        if (this.vivant()) {
            this.position += num;
        }
    }

    public void adieux() {
        System.out.println(this.nom + " n'est plus!");
    }

    public void faiblir(int num) {
        if (this.vivant()) {
            this.pointsDeVie -= num;
            if (this.pointsDeVie <= 0) {
                this.pointsDeVie = 0;
                this.adieux();
            }
        }
    }

    @Override
    public String toString() {
        return nom + ", niveau: " + niveau +
                ", points de vie: " + pointsDeVie +
                ", force: " + force + ", points d'attaque: " + this.pointsAttaque() +
                ", position: " + position;
    }
}

class Dragon extends Creature {
    private int porteeFlamme;

    public Dragon(String nom, int niveau, int pointsDeVie, int force, int porteeFlamme, int... position) {
        super(nom, niveau, pointsDeVie, force, position);
        this.porteeFlamme = porteeFlamme;
    }

    public void voler(int num) {
        if (this.vivant()) {
            this.setPosition(num);
        }
    }

    public void souffleSur(Creature creature) {
        if (this.vivant() && creature.vivant() &&
                Utility.distance(this.position(), creature.position()) <= this.porteeFlamme) {
            creature.faiblir(this.pointsAttaque());
            this.faiblir(Utility.distance(this.position(), creature.position()));

            if (this.vivant() && !creature.vivant()) {
                this.setNiveau(this.getNiveau() + 1);
            }
        }
    }
}

class Hydre extends Creature {
    private int longueurCou;
    private int dosePoison;

    public Hydre(String nom, int niveau, int pointsDeVie, int force, int longueurCou, int dosePoison, int... position) {
        super(nom, niveau, pointsDeVie, force, position);
        this.longueurCou = longueurCou;
        this.dosePoison = dosePoison;
    }

    public void empoisonne(Creature creature) {
        if (this.vivant() && creature.vivant() &&
                Utility.distance(this.position(), creature.position()) <= this.longueurCou) {
            creature.faiblir(this.pointsAttaque() + this.dosePoison);

            if (this.vivant() && !creature.vivant()) {
                this.setNiveau(this.getNiveau() + 1);
            }
        }
    }
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
// ======================================================================
class Dragons {
    private static void combat(Dragon dragon, Hydre hydre) {
        hydre.empoisonne(dragon); // l'hydre a l'initiative (elle est plus rapide)
        dragon.souffleSur(hydre);

    }


    public static void main(String[] args) {

        Dragon drag1 = new Dragon("Dragon bleu", 4, 58, 3, 18, 152);
        drag1.voler(159);
        System.out.println(drag1);
        Dragon dragon = new Dragon("Dragon rouge", 2, 10, 3, 20);
        Hydre hydre = new Hydre("Hydre maléfique", 2, 10, 1, 10, 1, 42);


        System.out.println(dragon);
        System.out.println("se prépare au combat avec :");
        System.out.println(hydre);

        System.out.println();

        System.out.println("1er combat :");
        System.out.println("\t Les créatures ne sont pas à portée, donc ne peuvent pas s'attaquer.");
        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);

        System.out.println();

        System.out.println("Le dragon vole à proximité de l'hydre :");
        dragon.voler(hydre.position() - 1);
        System.out.println(dragon);

        System.out.println();

        System.out.println("L'hydre recule d'un pas :");
        hydre.deplacer(1);
        System.out.println(hydre);

        System.out.println();
        System.out.println("2e combat :");
        System.out.format("\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",
                "+ l'hydre inflige au dragon une attaque de 3 points",
                " [ niveau (2) * force (1) + poison (1) = 3 ] ;",
                "+  le dragon inflige à l'hydre une attaque de 6 points",
                " [ niveau (2) * force (3) = 6 ] ;",
                " + pendant son attaque, le dragon perd 2 points de vie supplémentaires",
                "[ correspondant à la distance entre le dragon et l'hydre : 43 - 41 = 2 ].");

        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);

        System.out.println();
        System.out.println("Le dragon avance d'un pas :");
        dragon.deplacer(1);
        System.out.println(dragon);

        System.out.println();
        System.out.println("3e combat :");
        System.out.format("\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",

                "+ l'hydre inflige au dragon une attaque de 3 points",
                "[ niveau (2) * force (1) + poison (1) = 3 ] ;",
                "+ le dragon inflige à l'hydre une attaque de 6 points",
                "[ niveau (2) * force (3) = 6 ] ;",
                "+ pendant son attaque, le dragon perd 1 point de vie supplémentaire",
                "[ correspondant à la distance entre le dragon et l'hydre : 43 - 42 = 1 ] ;",
                "+ l'hydre est vaincue et le dragon monte au niveau 3.");
        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);

        System.out.println();
        System.out.println("4e Combat :");
        System.out.println("\t quand une créature est vaincue, rien ne se passe.");
        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);
    }
}
