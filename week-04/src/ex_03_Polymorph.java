import java.util.TreeMap;

public class ex_03_Polymorph {
    public static void main(String[] args) {
        Forme[] tabFormes =
                {
                        new Cercle("rouge"),
                        new Triangle("jaune")
                };

        Collect formes = new Collect(10);

        // Une collection de formes
        // contenant une copie des objets definis
        // dans le tableau tabFormes
        for (int i = 0; i < tabFormes.length; ++i)
            formes.add(tabFormes[i].clone());
        formes.dessine();
    }
}

class Forme {
    private String couleur;

    public String getCouleur() {
        return couleur;
    }

    public Forme(String uneCouleur) {
        couleur = uneCouleur;
    }

    public Forme(Forme other) {
        this.couleur = other.couleur;
    }

    public Forme clone() {
        return new Forme(this);
    }

    public void dessine() {
        System.out.println("Une forme " + couleur);
    }
}


class Triangle extends Forme {
    public Triangle(String uneCouleur) {
        super(uneCouleur);
    }

    public Triangle(Triangle autreTriangle) {
        super(autreTriangle);
    }

    public Triangle clone() {
        return new Triangle(this);
    }

    public void dessine() {
        super.dessine();
        System.out.println("toute pointue");
    }
}

class Cercle extends Forme {

    public Cercle(String uneCouleur) {
        super(uneCouleur);
    }

    public Cercle(Cercle autreCercle) {
        super(autreCercle);
    }

    public Cercle clone() {
        return new Cercle(this);
    }

    public void dessine() {
        super.dessine();
        System.out.println("toute ronde");
    }
}


class Collect {
    private Forme collect[];

    private int index;

    public Collect(int indexMax) {
        collect = new Forme[indexMax];
        index = -1;
    }

    public void add(Forme a) {
        if (index < collect.length - 1) {
            ++index;
            collect[index] = a;
        }

    }

    public void dessine() {
        for (int i = 0; i <= index; ++i)
            collect[i].dessine();
    }
}

