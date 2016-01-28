
public class ex_01_VariableStatique {
        public static void main(String[] args) {
            FigureGeometrique f1, f2, f3, f4;
            f1 = new FigureGeometrique("Rouge", "Noir");
            f2 = new FigureGeometrique("Bleu", "Blanc");
            f3 = new FigureGeometrique();
            f4 = new FigureGeometrique("Violet", "Orange");

            f2.changerCouleurContour("Noir");

            System.out.println(f3.couleurSurface);
            System.out.println(f3.couleurContour);

            System.out.println(f2.couleurContour);

            System.out.println(FigureGeometrique.nbFigures);
            System.out.println(f2.nbFigures);

            System.out.println(f1.nbFigContNoir);
            System.out.println(f4.nbFigSurfBlanc);
        }
}

class FigureGeometrique {
    String couleurContour = "Jaune";
    String couleurSurface = "Blanc";

    static int nbFigures = 0;
    static int nbFigContNoir = 0;
    static int nbFigSurfBlanc = 0;

    FigureGeometrique() {
    }

    FigureGeometrique(String coulSurf, String coulCont) {
        couleurSurface = coulSurf;
        couleurContour = coulCont;
        if (couleurContour.equals("Noir")) {
            nbFigContNoir++;
        }
        if (couleurSurface.equals("Blanc")) {
            nbFigSurfBlanc++;
        }
        nbFigures++;
    }

    void changerCouleurContour(String couleur) {
        couleurContour = couleur;
    }

    void changerCouleurSurface(String couleur) {
        couleurSurface = couleur;
    }
}