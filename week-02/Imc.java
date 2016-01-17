 class Patient{
    private double masse;
    private double hauteur;

    public void init(double masse, double hauteur){
        if(masse<0||hauteur<0){
            this.masse = 0;
            this.hauteur = 0;
        } else {
            this.masse = masse;
            this.hauteur = hauteur;
        }
    }

     public void afficher(){
        System.out.printf("Patient : %.1f kg pour %.1f m",masse,hauteur).println();
    }
    public double imc(){
        if (hauteur==0){
            return 0;
        } else {
            return masse/(hauteur*hauteur);
        }
    }

    public double poids(){
        return masse;
    }
    public double taille(){
        return hauteur;
    }
}


class Imc {
    public static void main(String[] args) {

        Patient quidam = new Patient();
        quidam.init(74.5, 1.75);
        quidam.afficher();
        System.out.println("IMC : " + quidam.imc());
        quidam.init( -2.0, 4.5);
        quidam.afficher();
    }

}
