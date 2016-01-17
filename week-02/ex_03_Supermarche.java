
import com.sun.deploy.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ex_03_Supermarche {
    public static void main(String[] args) {
        // Les articles vendus dans le supermarché
        Article choufleur = new Article("Chou-fleur extra", 3.50, false);
        Article roman = new Article("Les malheurs de Sophie", 16.50, true);
        Article camembert = new Article("Cremeux 100%MG", 5.80, false);
        Article cdrom = new Article("C++ en trois jours", 48.50, false);
        Article boisson = new Article("Petit-lait", 2.50, true);
        Article petitspois = new Article("Pois surgeles", 4.35, false);
        Article poisson = new Article("Sardines", 6.50, false);
        Article biscuits = new Article("Cookies de grand-mere", 3.20, false);
        Article poires = new Article("Poires Williams", 4.80, false);
        Article cafe = new Article("100% Arabica", 6.90, true);
        Article pain = new Article("Pain d'epautre", 6.90, false);

        // Les caddies du supermarché
        Caddie caddie1 = new Caddie();
        Caddie caddie2 = new Caddie();
        Caddie caddie3 = new Caddie();

        // Les caisses du supermarché
        // le premier argument est le numero de la caisse
        // le second argument est le montant initial de la caisse.
        Caisse caisse1 = new Caisse(1, 0.0);
        Caisse caisse2 = new Caisse(2, 0.0);

        // les clients font leurs achats
        // le second argument de la méthode remplir
        // correspond à une quantité

        // remplissage du 1er caddie
        caddie1.remplir(choufleur, 2);
        caddie1.remplir(cdrom, 1);
        caddie1.remplir(biscuits, 4);
        caddie1.remplir(boisson, 6);
        caddie1.remplir(poisson, 2);

        // remplissage du 2eme caddie
        caddie2.remplir(roman, 1);
        caddie2.remplir(camembert, 1);
        caddie2.remplir(petitspois, 2);
        caddie2.remplir(poires, 2);

        // remplissage du 3eme caddie
        caddie3.remplir(cafe, 2);
        caddie3.remplir(pain, 1);
        caddie3.remplir(camembert, 2);

        // Les clients passent à la caisse
        caisse1.scanner(caddie1);
        caisse1.scanner(caddie2);
        caisse2.scanner(caddie3);

        caisse1.totalCaisse();
        caisse2.totalCaisse();
    }

    public static class Article{
        private String nom;
        private double prix;
        private boolean estEnSold;

        public Article(String nom, double prix, boolean siEnSold){
            this.nom = nom;
            this.prix = prix;
            this.estEnSold = siEnSold;
        }
    }

    public static class Achat{
        private Article article;
        private int quantite;

        private Achat(Article article, int quantite){
            this.article = article;
            this.quantite = quantite;
        }
    }

    public static class Caddie{
        private ArrayList<Achat> listAchats = new ArrayList<>();

        public void remplir(Article article, int quantite){
            Achat achat = new Achat(article, quantite);
            listAchats.add(achat);
        }
    }

    public static class Caisse{
        private int numero;
        private double montantTotal = 0.0;

        public Caisse(int numero, double montant){
            this.numero = numero;
            montantTotal = montant;
        }

        public void scanner(Caddie caddie){
            double totalPrixCaddie = 0;
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            System.out.println(dateFormat.format(date));
            System.out.printf("Caisse numero %d",numero).println();
            System.out.println();

            for (Achat achat : caddie.listAchats) {
                String nom = achat.article.nom;
                double prixArticle = achat.article.prix;
                int quantite = achat.quantite;
                boolean siEnSold = achat.article.estEnSold;
                double prixAchat = 0;
                if (siEnSold){
                    prixAchat = (prixArticle/2) * quantite;
                    totalPrixCaddie += prixAchat;
                    System.out.println(nom + " : " + prixArticle + " x " +
                            quantite + " = " + prixAchat + " Frs" + " (1/2 Prix)");
                } else {
                    prixAchat = prixArticle * quantite;
                    totalPrixCaddie += prixAchat;
                    System.out.println(nom + " : " + prixArticle + " x " + quantite +
                            " = " + prixAchat + " Frs" );
                }
            }
            montantTotal += totalPrixCaddie;
            System.out.println();
            System.out.printf("Montant à payer : %.2f Frs",totalPrixCaddie).println();
            System.out.println(new String(new char[50]).replace("\0", "="));
        }

        public void totalCaisse(){
            System.out.printf("La caisse  numero  a encaisse %.2f Frs aujourd'hui",montantTotal).println();
        }
    }
}
