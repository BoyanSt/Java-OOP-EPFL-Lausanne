
public class ex_01_Poeme {
    public static void main(String[] args) {
        Fleur f1 = new Fleur("Violette", "bleu");
        Fleur f2 = new Fleur(f1);
        System.out.print("dans un cristal ");
        f2.eclore();
        System.out.print("ne laissant plus ");
        System.out.println(f1);
        System.out.println(f2);
    }

    public static class Fleur{
        private String nameFleur;
        private String couleur;

        public Fleur(String name, String couleur){
            nameFleur = name;
            this.couleur = couleur;
        }

        public Fleur(Fleur autreFleur){
            nameFleur = autreFleur.nameFleur;
            couleur = autreFleur.couleur;
            System.out.println( nameFleur + " fraichement cueillie");
            System.out.print("Fragile corolle taillée ");

        }

        public void eclore(){
            System.out.println("veiné de " + couleur);
        }

        public String toString(){
            return "qu'un simple souffle";
        }

    }
}
