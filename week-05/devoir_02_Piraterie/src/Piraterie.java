/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

abstract class Navire {
    private int x;
    private int y;
    private int drapeau;
    private boolean detruit = false;

    public Navire(int unitX, int unitY, int drapeau) {
        this.setX(unitX);
        this.setY(unitY);
        this.drapeau = drapeau;
    }

    public final void FF(){}

    public void setDetruit(boolean detruit) {
        final int g = 5;
        this.detruit = detruit;
    }

    public void setX(int x) {
        if (x >= 0 && x <= Piraterie.MAX_X) {
            this.x = x;
        } else if (x < 0) {
            this.x = 0;
        } else {
            this.x = Piraterie.MAX_X;
        }
    }

    public void setY(int y) {
        if (y >= 0 && y <= Piraterie.MAX_Y) {
            this.y = y;
        } else if (y < 0) {
            this.y = 0;
        } else {
            this.y = Piraterie.MAX_Y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDrapeau() {
        return drapeau;
    }

    public boolean estDetruit() {
        return detruit;
    }

    public double distance(Navire navire) {
        return Math.sqrt(this.returnSquare(x - navire.getX()) +
                this.returnSquare(y - navire.getY()));
    }

    private double returnSquare(double num) {
        return num * num;
    }

    public void avance(int unitsX, int unitsY) {
        this.setX(this.getX() + unitsX);
        this.setY(this.getY() + unitsY);
    }

    public void coule() {
        this.detruit = true;
    }


    abstract void recoitBoulet();

    abstract void combat(Navire autreNavire);

    public boolean estPacifique(){
        return true;
    }

    public void rencontre(Navire autreNavire){
        if (this.distance(autreNavire) < Piraterie.RAYON_RENCONTRE &&
                this.getDrapeau() != autreNavire.getDrapeau()){
            this.combat(autreNavire);
        }
    }

    public String getNom(){
        return "Navire";
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(this.getNom() + " avec drapeau " + drapeau + " en "+
                      "(" + this.x + ", " + this.y + ") -> ");
        return output.toString();
    }
}

class Pirate extends Navire{
    private boolean endommage;

    public Pirate(int unitX, int unitY, int drapeau, boolean endommage) {
        super(unitX, unitY, drapeau);
        this.endommage = endommage;
    }

    public boolean estEndommage(){
        return endommage;
    }

    @Override
    public boolean estPacifique() {
        return false;
    }

    @Override
    public void combat(Navire autreNavire) {
        if (autreNavire.estPacifique()){
            autreNavire.recoitBoulet();
        } else {
            autreNavire.recoitBoulet();
            this.recoitBoulet();
        }
    }

    @Override
    public void recoitBoulet() {
        if (this.estEndommage()){
            this.setDetruit(true);
        } else if (!this.estEndommage()){
            this.endommage = true;
        }
    }

    @Override
    public String getNom() {
        return "Bateau pirate";
    }

    @Override
    public String toString() {
        if (this.estEndommage() && !this.estDetruit()){
            return super.toString() + "ayant subi des dommages";
        } else if (this.estDetruit()){
            return super.toString() + "détruit";
        } else {
            return super.toString() + "intact";
        }
    }
}

class Marchand extends Navire{

    public Marchand(int unitX, int unitY, int drapeau) {
        super(unitX, unitY, drapeau);
    }

    @Override
    public void combat(Navire autreNavire) {
        if (!autreNavire.estPacifique()){
            this.recoitBoulet();
        }
    }

    @Override
    public boolean estPacifique() {
        return true;
    }

    @Override
    public void recoitBoulet() {
        this.setDetruit(true);
    }

    @Override
    public String getNom() {
        return "Bateau marchand";
    }

    @Override
    public String toString() {
        if (this.estDetruit()){
            return super.toString() + "détruit";
        } else {
            return super.toString() + "intact";
        }
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
class Piraterie {

    static public final int MAX_X = 500;
    static public final int MAX_Y = 500;
    static public final double RAYON_RENCONTRE = 10;

    static public void main(String[] args) {
        // Test de la partie 1
        System.out.println("***Test de la partie 1***");
        System.out.println();
        // un bateau pirate 0,0 avec le drapeau 1 et avec dommages
        Navire ship1 = new Pirate(0, 0, 1, true);
        // un bateau marchand en 25,0 avec le drapeau 2
        Navire ship2 = new Marchand(25, 0, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println("Distance: " + ship1.distance(ship2));
        System.out.println("Quelques déplacements horizontaux et verticaux");
        // se deplace de 75 unites a droite et 100 en haut
        ship1.avance(75, 100);
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println("Un déplacement en bas:");
        ship1.avance(0, -5);
        System.out.println(ship1);
        ship1.coule();
        ship2.coule();
        System.out.println("Après destruction:");
        System.out.println(ship1);
        System.out.println(ship2);

        // Test de la partie 2
        System.out.println();
        System.out.println("***Test de la partie 2***");
        System.out.println();

        // deux vaisseaux sont enemis s'ils ont des drapeaux differents

        System.out.println("Bateau pirate et marchand ennemis (trop loin):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(0, 25, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Bateau pirate et marchand ennemis (proches):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(2, 0, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Bateau pirate et marchand amis (proches):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(2, 0, 1);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Deux bateaux pirates ennemis intacts (proches):");
        // bateaux pirates intacts
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Pirate(2, 0, 2, false);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Un bateau pirate intact et un avec dommages, ennemis:");
        // bateau pirate intact
        Navire ship3 = new Pirate(0, 2, 3, false);
        System.out.println(ship1);
        System.out.println(ship3);
        ship3.rencontre(ship1);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship3);
        System.out.println();

        System.out.println("Deux bateaux pirates ennemis avec dommages:");
        System.out.println(ship2);
        System.out.println(ship3);
        ship3.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship2);
        System.out.println(ship3);
        System.out.println();
    }
}
