import java.util.ArrayList;
import java.util.Calendar;

public class ex_01_EPFLien {

    public static void main(String[] args) {

        ArrayList<Personne> listPersonneEPFL = new ArrayList<>();

        EtudiantRegulier etudReg1 = new EtudiantRegulier("Gaston Peutimide",2013,"SSC",6.0);
        listPersonneEPFL.add(etudReg1);
        EtudiantRegulier etudReg2 = new EtudiantRegulier("Yvan Rattrapeur", 2011, "SSC", 2.5);
        listPersonneEPFL.add(etudReg2);
        EtudiantExchange etudExch1 = new EtudiantExchange("Björn Borgue",2012, "Informatique","KTH");
        listPersonneEPFL.add(etudExch1);
        Enseignent enseignent1 = new Enseignent("Mathieu Matheu",1998,"LPEM",10000,"Physique");
        listPersonneEPFL.add(enseignent1);
        Secretaire secretaire1 = new Secretaire("Sophie Scribona", 2005,"LMT",5000);
        listPersonneEPFL.add(secretaire1);

        int counterEtudiants = 0;
        double totalAnnees = 0;
        int totalPersonnes = listPersonneEPFL.size();

        for (Personne personne : listPersonneEPFL) {
            if (personne.estEtudinat()){
                counterEtudiants++;
            }
            totalAnnees += personne.annesEnEPF();
        }

        System.out.printf("Parmi les %d EPFLiens, %d sont des etudiants.",
                          totalPersonnes,counterEtudiants).println();
        System.out.printf("Ils sont à l'EPFL depuis en moyenne %.1f ans",
                           totalAnnees/totalPersonnes).println();
        System.out.println("Liste des EPFLiens:");
        for (Personne personne : listPersonneEPFL) {
            System.out.println(personne);
        }

    }

    public static class Personne{
        protected String nom;
        protected int anneArrive;

        public Personne(String nom, int anneArrive){
            this.nom = nom;
            this.anneArrive = anneArrive;
        }

        public boolean estEtudinat(){
            return false;
        }

        public int annesEnEPF(){
            return Calendar.getInstance().get(Calendar.YEAR) - anneArrive;
        }

        @Override
        public String toString() {
            return  "Nom : " + nom + "\n" +
                    "Anne : " + anneArrive +"\n";
        }
    }

    public static class Secretaire extends Personne{
        protected String institutLabo;
        protected double salaire;

        public Secretaire(String nom, int anneArrive, String institutLabo, double salaire) {
            super(nom, anneArrive);
            this.institutLabo = institutLabo;
            this.salaire = salaire;
        }

        @Override
        public String toString() {
            return "Secretaire:" + "\n" + super.toString()+
                    "Laboratoire : " + institutLabo + "\n" +
                    "Salaire : " + salaire + "\n";
        }
    }

    public static class Enseignent extends Secretaire{
        protected String section;

        public Enseignent(String nom, int anneArrive, String institutLabo, double salaire, String section) {
            super(nom, anneArrive, institutLabo, salaire);
            this.section = section;
        }

        @Override
        public String toString() {
            return super.toString().replaceAll("Secretaire:", "Enseignent")+
                    "Section d'enseignement : " + section + "\n";
        }
    }

    public static class EtudiantExchange extends Personne{
        protected String section;
        protected String uniOrigine;

        public EtudiantExchange(String nom, int anneArrive, String section, String uniOrigine) {
            super(nom, anneArrive);
            this.section = section;
            this.uniOrigine = uniOrigine;
        }

        @Override
        public boolean estEtudinat() {
            return true;
        }

        @Override
        public String toString() {
            return "Etudiant d'echange:" +"\n"+ super.toString()+
                    "Section : "+section+"\n"+
                    "Uni d'origine : " + uniOrigine + "\n";
        }
    }

    public static class EtudiantRegulier extends Personne{
        protected String section;
        protected double noteMoyenne;


        public EtudiantRegulier(String nom, int anneArrive, String section, double noteMoyenne) {
            super(nom, anneArrive);
            this.section = section;
            this.noteMoyenne = noteMoyenne;
        }

        @Override
        public boolean estEtudinat() {
            return true;
        }

        @Override
        public String toString() {
            return  "Etudiant:" +"\n"+ super.toString() +
                    "Section : "+section+"\n"+
                    "Moyenne : "+noteMoyenne+"\n";
        }
    }


}
