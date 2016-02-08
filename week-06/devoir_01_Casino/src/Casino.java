

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

abstract class Mise{
    private int nombreDeJeton;

    public Mise(int nombreDeJeton) {
        this.nombreDeJeton = nombreDeJeton;
    }

    public final int getMise() {
        return nombreDeJeton;
    }

    abstract int gain(int n);
}

class Pleine extends Mise{
    public static final int FACTEUR_GAIN = 35;
    private int numeroMise;

    public Pleine(int nombreDeJeton, int numeroMise) {
        super(nombreDeJeton);
        this.numeroMise = numeroMise;
    }

    public int getNumeroMise() {
        return numeroMise;
    }

    @Override
    int gain(int n) {
        if (this.numeroMise == n){
            return this.getMise() * FACTEUR_GAIN;
        } else {
            return 0;
        }
    }
}

class Rouges extends Mise{
    public static final int[] ROUGES =
            new int[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public Rouges(int nombreDeJeton) {
        super(nombreDeJeton);
    }




    @Override
    int gain(int n) {
        boolean estGagne = false;

        for (int i = 0; i < ROUGES.length; i++) {
            if (n == ROUGES[i]){
                estGagne = true;
                break;
            }
        }

        if (estGagne){
            return this.getMise();
        } else {
            return 0;
        }
    }
}

class Joueur{
    private String nom;
    private Mise mise;

    public Joueur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    void setStrategie(Mise uneMise){
        if (uneMise instanceof Rouges){
            this.mise = new Rouges(uneMise.getMise());
        } else if (uneMise instanceof Pleine){
            this.mise = new Pleine(uneMise.getMise(),((Pleine) uneMise).getNumeroMise());
        }
    }

    public int getMise(){
        if (this.mise == null){
            return 0;
        }
        return this.mise.getMise();
    }

    public int gain(int n){
        if (this.mise == null){
            return 0;
        }
        return this.mise.gain(n);
    }
}

abstract class Roulette{
    private ArrayList<Joueur> listeJoueurs = new ArrayList<>();
    private int gain = 0;
    private int numeroTire;


    public void participe(Joueur joueur){
        listeJoueurs.add(joueur);
    }

    public int getParticipants(){
        return this.listeJoueurs.size();
    }

    public int getGainMaison(){
        return this.gain;
    }

    public void rienNeVaPlus(int num){
        this.numeroTire = num;
    }

    public int getTirage(){
        return this.numeroTire;
    }

    abstract int perteMise(int miseDuJoueur);

    public void calculerGainMaison(){
        if (this.gain !=0){
            this.gain = 0;
        }
        for (Joueur joueur : listeJoueurs) {
            if (joueur.gain(this.getTirage()) > 0){
                gain -= joueur.gain(this.getTirage());
            } else {
                gain += this.perteMise(joueur.getMise());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Croupier : le numéro du tirage est le " + this.getTirage() + "\n\r");
        for (Joueur joueur : listeJoueurs) {
            output.append("Le joueur " + joueur.getNom() + " mise " + joueur.getMise());

            if (joueur.gain(this.getTirage()) > 0){
                output.append(" et gagne " + this.getTirage() + "\n\r");
            } else {
                output.append(" et perd \n\r");
            }
        }

        output.append("Gain perte du casino : " + Math.abs(this.getGainMaison()) + "\n\r");
        return output.toString();
    }
}

class RouletteFrancaise extends Roulette{
    @Override
    int perteMise(int miseDuJoueur) {
        return miseDuJoueur;
    }

}

class RouletteAnglaise extends Roulette implements ControleJoueurs {

    @Override
    int perteMise(int miseDuJoueur) {
        return miseDuJoueur/2;
    }

    @Override
    public boolean check() {
        return this.getParticipants() < 10;
    }

    @Override
    public void participe(Joueur joueur) {
        if (this.check()) {
            super.participe(joueur);
        }
    }
}

interface ControleJoueurs {
    boolean check();
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Casino {

    private static void simulerJeu(Roulette jeu) {
//        for (int tirage : new int [] { 12, 1, 31 }) {
//            jeu.rienNeVaPlus(tirage);
//            jeu.calculerGainMaison();
//            System.out.println(jeu);
//            System.out.println("");
//        }

        for (int tirage : new int [] { 26 }) {
            jeu.rienNeVaPlus(tirage);
            jeu.calculerGainMaison();
            System.out.println(jeu);
            System.out.println("");
        }
    }

    public static void main(String[] args) {

//        Un joueur misant une pleine à 4 jetons sur le 32
//        Un joueur misant 73 sur une rouges
//        Un joueur misant une pleine à 21 jetons sur le 16
//        Un joueur misant 88 sur une rouges
//        Un joueur misant une pleine à 65 jetons sur le 25
//        Un joueur misant 46 sur une rouges

        Roulette anglais = new RouletteAnglaise();
        Joueur jack0 = new Joueur("jack0");
        jack0.setStrategie(new Pleine(4,32));

        Joueur jack1 = new Joueur("jack1");
        jack1.setStrategie(new Rouges(73));

        Joueur jack2 = new Joueur("jack2");
        jack2.setStrategie(new Pleine(21, 16));

        Joueur jack3 = new Joueur("jack3");
        jack3.setStrategie(new Rouges(88));

        Joueur jack4 = new Joueur("jack4");
        jack4.setStrategie(new Pleine(65, 25));

        Joueur jack5 = new Joueur("jack5");
        jack5.setStrategie(new Rouges(46));

        anglais.participe(jack0);
        anglais.participe(jack1);
        anglais.participe(jack2);
        anglais.participe(jack3);
        anglais.participe(jack4);
        anglais.participe(jack5);
        anglais.rienNeVaPlus(12);
        anglais.calculerGainMaison();
        String out = anglais.toString();
        System.out.println(out);




        Joueur joueur1 = new Joueur("Dupond");
        joueur1.setStrategie(new Pleine(100,1)); // miser 100 jetons sur le 1

        System.out.println();

        Joueur joueur2 = new Joueur("Dupont");
        joueur2.setStrategie(new Rouges(30)); // miser 30 jetons sur les rouges

        System.out.println();

        Roulette jeu1 = new RouletteAnglaise();
        jeu1.participe(joueur1);
        jeu1.participe(joueur2);

        Roulette jeu2 = new RouletteFrancaise();
        jeu2.participe(joueur1);
        jeu2.participe(joueur2);

        System.out.println("Roulette anglaise :");
        simulerJeu(jeu1);
        System.out.println("Roulette française :");
        simulerJeu(jeu2);
    }
}
