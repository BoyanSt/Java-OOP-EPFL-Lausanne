import java.util.ArrayList;

public class ex_02_Poste {

    public static void  main(String args[]) {
        //Cr'eation d'une boite-aux-lettres
        // 30  est la capacit'e maximale de la
        // boite aux lettres
        // (pas necessaire si vous dêcidez d'utiliser
        // un ArrayList).
        Boite boite = new Boite(30);

        //Creation de divers courriers/colis..
        //String adresse, double poids, String modeExpedition, String format
        Lettre lettre1 = new Lettre("Chemin des Acacias 28, 1009 Pully", 200, "express", "A3");
        Lettre lettre2 = new Lettre("", 800, "normal", "A4"); // invalide

        Publicite pub1 = new Publicite("Les Moilles  13A, 1913 Saillon",1500, "express");
        Publicite pub2 = new Publicite("", 3000, "normal"); // invalide

        Colis colis1 = new Colis("Grand rue 18, 1950 Sion", 5000, "express", 30);
        Colis colis2 = new Colis("Chemin des fleurs 48, 2800 Delemont", 3000, "express", 70); //Colis invalide !

        boite.ajouterCourrier(lettre1);
        boite.ajouterCourrier(lettre2);
        boite.ajouterCourrier(pub1);
        boite.ajouterCourrier(pub2);
        boite.ajouterCourrier(colis1);
        boite.ajouterCourrier(colis2);


        System.out.println("Le montant total d'affranchissement est de " +
                boite.affranchir());
        boite.afficher();

        System.out.println("La boite contient " + boite.courriersInvalides()
                + " courriers invalides");
    }

    public static class Boite{
        private ArrayList<Courrier> listCourriers ;

        public Boite(int num){
            listCourriers = new ArrayList<>(num);
        }

        public ArrayList<Courrier> getListCourriers() {
            return listCourriers;
        }

        public void ajouterCourrier(Courrier courrier){
            listCourriers.add(courrier);
        }

        public double affranchir(){
            double affranchissementTotal = 0;

            for (Courrier courrier : listCourriers) {
                if (courrier.estValid()){
                    affranchissementTotal += courrier.calcPrixAffranchissment();
                }
            }
            return affranchissementTotal;
        }

        public void afficher(){

            for (Courrier courrier : listCourriers) {
                System.out.println(courrier);
            }
        }

        public int courriersInvalides(){
            int counter = 0;
            for (Courrier courrier : listCourriers) {
                if (!courrier.estValid()){
                    counter++;
                }
            }
            return counter;
        }
    }

    public static class Courrier{
        private double poids;
        private String modeExpedition;
        private String adresse;

        public Courrier(String adresse, double poids, String modeExpedition) {
            this.adresse = adresse;
            this.poids = poids;
            this.modeExpedition = modeExpedition;
        }

        public double getPoids() {
            return poids;
        }

        public String getAdresse() {
            return adresse;
        }

        public String getModeExpedition() {
            return modeExpedition;
        }

        public double calcPrixAffranchissment(){
            return 0;
        }

        public boolean estExpress(){
            return modeExpedition.equals("express");
        }

        public boolean estValid(){
             return (!this.adresse.equals(""));
        }

        @Override
        public String toString() {
            return  "Poids : " + poids + "\n" +
                    "Mode d'expedition : " + modeExpedition + "\n" +
                    "Adresse : " + adresse + "\n" +
                    "Prix : " + this.calcPrixAffranchissment() + "\n";
        }
    }

    public static class Lettre extends Courrier{
        private String format;

        public Lettre(String adresse, double poids, String modeExpedition, String format) {
            super(adresse, poids, modeExpedition);
            this.format = format;
        }

        @Override
        public double calcPrixAffranchissment() {
            double tarifDeBase;
            double prixAffranchessment;
            if(!this.estValid()){
                return 0.0;
            }
            if (format.equals("A4")){
                tarifDeBase = 2.5;
                prixAffranchessment = tarifDeBase + this.getPoids()/1000;
            } else {
                tarifDeBase = 3.5;
                prixAffranchessment = tarifDeBase + this.getPoids()/1000;
            }

            if (this.estExpress()){
                return prixAffranchessment*2;
            } else {
                return prixAffranchessment;
            }
        }

        @Override
        public String toString() {
            System.out.println("Lettre");
            if (!this.estValid()){
                System.out.println("(Courrier  invalide)");
            }
            return super.toString() + "Format : " + format + "\n";
        }
    }

    public static class Publicite extends Courrier{

        public Publicite(String adresse, double poids, String modeExpedition) {
            super(adresse, poids, modeExpedition);
        }

        @Override
        public double calcPrixAffranchissment() {
            if(!this.estValid()){
                return 0.0;
            }
            if (this.estExpress()){
                return (this.getPoids()/1000)*10;
            } else {
                return (this.getPoids()/1000)*5;
            }
        }

        @Override
        public String toString() {
            System.out.println("Publicite");
            if (!this.estValid()){
                System.out.println("(Courrier  invalide)");
            }
            return super.toString();
        }
    }

    public static class Colis extends Courrier{
        private double volume;

        public Colis(String adresse, double poids, String modeExpedition, double volume) {
            super(adresse, poids, modeExpedition);
            this.volume = volume;
        }

        @Override
        public double calcPrixAffranchissment() {
            if(!this.estValid()){
                return 0.0;
            }

            double prix = 0.25*volume + this.getPoids()/1000;

            if (this.estExpress()){
                return prix*2;
            } else {
                return prix;
            }
        }

        @Override
        public boolean estValid() {
            return (volume<=50 && this.getAdresse()!=null);
        }

        @Override
        public String toString() {

            System.out.println("Colis");
            if (!this.estValid()){
                System.out.println("(Courrier  invalide)");
            }
            return super.toString() + "Volume : " + volume + "\n";
        }
    }

}
