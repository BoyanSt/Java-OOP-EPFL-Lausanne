import java.util.ArrayList;

/*****************************************************
 * Compléter le code à partir d'ici
 *****************************************************/

class Produit {
    private String nom;
    private String unite = "";

    public Produit(String nom, String... unite) {
        this.nom = nom;
        if (unite.length > 0) {
            this.unite = unite[0];
        }
    }

    public String getNom() {
        return nom;
    }

    public String getUnite() {
        return unite;
    }

    public Produit adapter(double n) {
        return this;
    }

    public double quantiteTotale(String nomProduit) {
        if (nomProduit.equals(this.nom)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return nom;
    }
}

class Ingredient {
    private Produit produit;
    private double quantite;
//    private double proportion;

    public Ingredient(Produit produit, double quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

//    public double getProportion() {
//        return proportion;
//    }
//
//    public void setProportion(double proportion) {
//        this.proportion = proportion;
//    }

    public Produit getProduit() {
        return produit;
    }

    public double getQuantite() {
        return quantite;
    }

    public double quantiteTotale(String nomProduit) {
        return this.quantite * this.produit.quantiteTotale(nomProduit);
    }

    @Override
    public String toString() {
        return quantite + " " + this.produit.getUnite() + " de " +
                this.produit.toString();
    }
}

class Recette {
    private ArrayList<Ingredient> listeIngredients;
    private String nom;
    private double nombreDeFois = 1;

    public Recette(String nom, double... nombreDeFois) {
        this.nom = nom;
        this.listeIngredients = new ArrayList<>();
        if (nombreDeFois.length > 0) {
            this.nombreDeFois = nombreDeFois[0];
        }
    }

    public void ajouter(Produit produit, double quantite) {

        Ingredient ingredient =
                new Ingredient(produit, this.nombreDeFois * quantite);

        this.listeIngredients.add(ingredient);
    }

    public Recette clone() {
        Recette copyRecette = new Recette(this.nom, this.nombreDeFois);
        for (Ingredient ingredient : this.listeIngredients) {
            copyRecette.ajouter(ingredient.getProduit(),ingredient.getQuantite()*2);
        }

        return copyRecette;
    }

    public Recette adapter(double n) {
        Recette recetteAdaptee = this.clone();
        recetteAdaptee.nombreDeFois = this.nombreDeFois*n;
        return recetteAdaptee;
    }

    public double quantiteTotale(String nomProduit) {
        double sommeTotal = 0;
        for (Ingredient ingredient : listeIngredients) {
            if (nomProduit.equals(ingredient.getProduit().getNom())) {
                sommeTotal += ingredient.getQuantite();
            }
        }
        return sommeTotal;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < listeIngredients.size(); i++) {
//          listeIngredients.get(i).setProportion(this.nombreDeFois);
            listeIngredients.get(i).getProduit().adapter(2);
            stringBuilder.append((i + 1) + ". " + listeIngredients.get(i));
            if (i != listeIngredients.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        return "Recette " + "\"" + nom + "\"" + " x " +
                nombreDeFois + ":" + "\n" + stringBuilder;
    }
}

class ProduitCuisine extends Produit {
    private Recette recette;

    public ProduitCuisine(String nom) {
        super(nom, "portion(s)");
        this.recette = new Recette(nom);
    }

    public Recette getRecette() {
        return recette;
    }

    public void ajouterARecette(Produit produit, double quantite) {
        this.recette.ajouter(produit, quantite);
    }

    public ProduitCuisine adapter(double n) {
        ProduitCuisine newProduitCuisine = new ProduitCuisine(this.getNom());
        newProduitCuisine.recette.adapter(n);
        return newProduitCuisine;
    }

    @Override
    public double quantiteTotale(String nomProduit) {
        if (nomProduit.equals(this.getNom())) {
            return 1;
        } else {
            return this.recette.quantiteTotale(nomProduit);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + this.recette.toString();
    }
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/

class Restaurant {
    public static void main(String[] args) {

        // quelques produits de base
        Produit oeufs = new Produit("oeufs");
        Produit farine = new Produit("farine", "grammes");
        Produit beurre = new Produit("beurre", "grammes");
        Produit sucreGlace = new Produit("sucre glacé", "grammes");
        Produit chocolatNoir = new Produit("chocolat noir", "grammes");
        Produit amandesMoulues = new Produit("amandes moulues", "grammes");
        Produit extraitAmandes = new Produit("extrait d'amandes", "gouttes");

        ProduitCuisine glacage = new ProduitCuisine("glaçage au chocolat");
        // recette pour une portion de glaçage :
        glacage.ajouterARecette(chocolatNoir, 200);
        glacage.ajouterARecette(beurre, 25);
        glacage.ajouterARecette(sucreGlace, 100);

        System.out.println(glacage);
        System.out.println();

        ProduitCuisine glacageParfume = new ProduitCuisine("glaçage au chocolat parfumé");
        // besoin de 1 portions de glaçage au chocolat et de 2 gouttes
        // d'extrait d'amandes pour 1 portion de glaçage parfumé
        glacageParfume.ajouterARecette(extraitAmandes, 2);
        glacageParfume.ajouterARecette(glacage, 1);

        System.out.println(glacageParfume);
        System.out.println();

        Recette recette = new Recette("tourte glacée au chocolat");
        // recette pour une portion de tourte glacée :
        recette.ajouter(oeufs, 5);
        recette.ajouter(farine, 150);
        recette.ajouter(beurre, 100);
        recette.ajouter(amandesMoulues, 50);
        recette.ajouter(glacageParfume, 2);

        System.out.println("===  Recette finale  ======\n");
        System.out.println(recette);
        System.out.println();

        afficherQuantiteTotale(recette, beurre);
        System.out.println();

        Recette doubleRecette = recette.adapter(2);
        System.out.println("===  Recette finale x 2 ===\n");
        System.out.println(doubleRecette);
        System.out.println();

        afficherQuantiteTotale(doubleRecette, beurre);
        afficherQuantiteTotale(doubleRecette, oeufs);
        afficherQuantiteTotale(doubleRecette, extraitAmandes);
        afficherQuantiteTotale(doubleRecette, glacage);
        System.out.println();

        System.out.println("===========================\n");
        System.out.println("Vérification que le glaçage n'a pas été modifié :\n");
        System.out.println(glacage);
    }

    private static void afficherQuantiteTotale(Recette recette, Produit produit) {
        String nom = produit.getNom();
        System.out.println("Cette recette contient " +
                recette.quantiteTotale(nom) + " " + produit.getUnite() + " de " + nom);
    }
}
