import java.util.ArrayList;

class Time
/* Représente le jour et l'heure d'un évènement.
 * Les heures sont représentées en double depuis minuit.
 * Par exemple 14:30 est représenté 14.5.
 */
{
    private final String day_;
    private final double hour_;

    // Constructeur à partir du jour et de l'heure
    public Time(String jour, double heure) {
        day_ = jour;
        hour_ = heure;
    }

    // Affichage
    public void print() {
        System.out.print(day_ + " à ");
        Time.printTime(hour_);
    }

    // Pour connaître le jour
    public String day() {
        return day_;
    }

    // Pour connaître l'heure
    public double hour()  {
        return hour_;
    }

    // Affiche un double en format heures:minutes
    public static void printTime(double hour) {
        int hh = (int)hour;
        int mm = (int)(60. * (hour - hh));
        System.out.format("%02d:%02d", hh, mm);
    }
}

/*******************************************
 * Complétez le programme à partir d'ici.
 *******************************************/
class Activity{
    private final String lieu;
    private final double duree;
    private final Time horaire;

    public Activity(String lieu,String day, double hour, double duree){
        this.lieu = lieu;
        this.duree = duree;
        this.horaire = new Time(day,hour);
    }

    public String getLocation() {
        return lieu;
    }

    public double getDuration() {
        return duree;
    }

    public Time getTime() {
        return horaire;
    }

    public boolean conflicts(Activity activity){

        double hourDebutPremier = this.getTime().hour();
        double hourFinPremier = hourDebutPremier + this.duree;
        double hourDebutDeuxieme = activity.getTime().hour();
        double hourFinDuexieme = hourDebutDeuxieme + activity.getDuration();


        if (this.getTime().day().equals(activity.getTime().day())){
            if (hourDebutPremier<hourDebutDeuxieme && hourDebutDeuxieme<hourFinPremier){
                return true;
            }
            if (hourDebutPremier<hourFinDuexieme && hourFinDuexieme<hourFinPremier){
                return true;
            }
            if (hourDebutDeuxieme<hourDebutPremier && hourFinPremier<hourFinDuexieme){
                return true;
            }
            if (hourDebutPremier==hourDebutDeuxieme && hourFinPremier==hourFinDuexieme){
                return true;
            }
            if (hourDebutPremier==hourDebutDeuxieme && hourFinPremier<hourFinDuexieme){
                return  true;
            }
            if (hourDebutPremier==hourDebutDeuxieme && hourFinDuexieme<hourFinDuexieme){
                return  true;
            }
            if (hourFinDuexieme==hourFinPremier && hourDebutPremier<hourDebutDeuxieme){
                return true;
            }
            if (hourFinDuexieme==hourFinPremier && hourDebutPremier>hourDebutDeuxieme){
                return true;
            }

        }
        return false;
    }

    public void print(){
        System.out.print("le ");
        this.horaire.print();
        System.out.print(" en " + lieu + ", durée ");
        Time.printTime(duree);

    }
}

class Course{
    private final String id;
    private final String nom;
    private final Activity lecture;
    private final Activity exercice;
    private final int credits;

    public Course(String id, String nom, Activity lecture, Activity exercice, int credits) {
        System.out.printf("Nouveau cours : %s", id).println();
        this.id = id;
        this.nom = nom;
        this.lecture = lecture;
        this.exercice = exercice;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return nom;
    }

    public int getCredits() {
        return credits;
    }

    public Activity getLecture() {
        return lecture;
    }

    public Activity getExercice() {
        return exercice;
    }

    public double workload(){
        return this.lecture.getDuration() + this.exercice.getDuration();
    }

    public boolean conflicts(Activity activity){
        return this.lecture.conflicts(activity)||this.exercice.conflicts(activity);
    }
    public boolean conflicts(Course course){
        return  course.getLecture().conflicts(this.lecture)||
                course.getLecture().conflicts(this.exercice)||
                course.getExercice().conflicts(this.lecture)||
                course.getExercice().conflicts(this.exercice);
    }
    public void print(){
        System.out.print(id + ": " + nom + " - cours ");
        this.lecture.print();
        System.out.print(", exercices ");
        this.exercice.print();
        System.out.print(". crédits : " + credits);
        System.out.println();
    }
}

class StudyPlan {
    private ArrayList<Course> listCours ;

    public StudyPlan() {
        this.listCours = new ArrayList<>();
    }

    protected ArrayList<Course> getListCours() {
        return listCours;
    }

    public StudyPlan(StudyPlan studyPlan) {
        this();
        for (Course course : studyPlan.getListCours()) {
            this.listCours.add(course);
        }
    }

    public void addCourse(Course course){
        this.listCours.add(course);
    }

    public boolean conflicts(String id, ArrayList<String> idsCourses){

        boolean estInStudyplan = false;
        ArrayList<Course> courseDonee = new ArrayList<>();

        for (Course course : this.listCours) {
            if (id.equals(course.getId())){
                estInStudyplan = true;
                courseDonee.add(course);
            }
        }

        if (!estInStudyplan){
            return false;
        }

        ArrayList<Course> coursesInSelectionAndPlan = new ArrayList<>();

        for (String currID : idsCourses) {
            for (Course course : this.listCours) {
                if (currID.equals(course.getId())){
                    coursesInSelectionAndPlan.add(course);
                }
            }
        }

        for (Course course : courseDonee) {
            for (Course course1 : coursesInSelectionAndPlan) {
                if (course.conflicts(course1)){
                    return true;
                }
            }
        }
        return false;
    }

    public void print(String id){
        for (Course course : listCours) {
            if (id.equals(course.getId())){
                course.print();
                break;
            }
        }
    }

    public int credits(String id){
        for (Course course : listCours) {
            if (id.equals(course.getId())){
                return course.getCredits();
            }
        }
        return 0;
    }

    public double workload( String id){
        for (Course course : listCours) {
            if (id.equals(course.getId())){
                return course.workload();
            }
        }
        return 0;
    }

    public void printCourseSuggestions(ArrayList<String> idsCourses){

        boolean estExistValid = false;

        for (Course course : listCours) {
            String id = course.getId();
            boolean estEnConflict = this.conflicts(id,idsCourses);
            if (!estEnConflict){
                course.print();
                estExistValid = true;
            }
        }

        if (!estExistValid){
            System.out.println("Aucun cours n’est compatible avec la sélection de cours.");
        }

        ArrayList<Course> courseDansLaSelection = new ArrayList<>();


        for (Course course : this.listCours) {
            for (String idsCourse : idsCourses) {
                if (idsCourse.equals(course.getId())){
                    courseDansLaSelection.add(course);
                }
            }
        }

        if (courseDansLaSelection.size()==0){
            System.out.println("Aucun cours n’est compatible avec la sélection de cours.");
        }
    }


}
class Schedule{
    private ArrayList<String> idCourses;
    private StudyPlan studyPlan;

    protected ArrayList<String> getIdCourses() {
        return idCourses;
    }

    protected StudyPlan getStudyPlan() {
        return studyPlan;
    }

    public Schedule(StudyPlan studyPlan) {
        this.studyPlan = new StudyPlan(studyPlan);
        this.idCourses = new ArrayList<>();
    }

    public Schedule(Schedule schedule){
        this(schedule.getStudyPlan());
        this.idCourses = new ArrayList<>();
        for (String ids : schedule.getIdCourses()) {
            idCourses.add(ids);
        }
    }

    public boolean addCourse(String id){
        boolean estEnconflict = false;

        estEnconflict = this.studyPlan.conflicts(id,this.idCourses);

        if (!estEnconflict){
            this.idCourses.add(id);
            return true;
        } else {
            return false;
        }
    }

    public double computeDailyWorkload(){
        double totalWorkLoad = 0;

        for (String idCourse : idCourses) {
            totalWorkLoad += this.studyPlan.workload(idCourse);
        }
        return totalWorkLoad/5.0;
    }

    public int computeTotalCredits(){
        int totalCredits = 0;
        for (String idCourse : this.idCourses) {
            totalCredits +=this.studyPlan.credits(idCourse);
        }
        return totalCredits;
    }

    public void print(){
        for (String idCourse : idCourses) {
            studyPlan.print(idCourse);
        }
        System.out.printf("Total de crédits : %d",this.computeTotalCredits()).println();

        System.out.print("Charge journalière : ");
        Time.printTime(this.computeDailyWorkload());
        System.out.println();

        System.out.println("Suggestions :");
        this.studyPlan.printCourseSuggestions(this.idCourses);
    }
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
class Planning {
    public static void main(String[] args) {

        Activity myActivity1 = new Activity("1", "mercredi", 16.50, 1.75);
        myActivity1.print();
        System.out.println();
        Activity myActivity2 = new Activity("2", "mercredi", 15.50, 3.00);
        myActivity2.print();
        System.out.println();
        System.out.println(myActivity1.conflicts(myActivity2));
        Activity physicsLecture  = new Activity("Central Hall", "lundi",  9.25, 1.75); //1.75
        Activity physicsExercises = new Activity("Central 101" , "lundi", 14.00, 2.00);

        Activity historyLecture  = new Activity("North Hall", "lundi", 10.25, 1.75);
        Activity historyExercises = new Activity("East 201"  , "mardi",  9.00, 2.00);

        Activity financeLecture  = new Activity("South Hall",  "vendredi", 14.00, 2.00);
        Activity financeExercises = new Activity("Central 105", "vendredi", 16.00, 1.00);

        // On affiche quelques informations sur ces activités
        System.out.print("L'activité physicsLecture a lieu ");
        physicsLecture.print();
        System.out.println(".");

        System.out.print("L'activité historyLecture a lieu ");
        historyLecture.print();
        System.out.println(".");

        if (physicsLecture.conflicts(historyLecture)) {
            System.out.println("physicsLecture est en conflit avec historyLecture.");
        } else {
            System.out.println("physicsLecture n'est pas en conflit avec historyLecture.");
        }
        System.out.println();

         //Création d'un plan d'étude
          StudyPlan studyPlan = new StudyPlan();
          Course physics = new Course("PHY-101", "Physique", physicsLecture, physicsExercises, 4);
          studyPlan.addCourse(physics);
          physics.print();
        System.out.println("==================================");
          physics.print();
        System.out.println("==================================");
          Course history = new Course("HIS-101", "Histoire", historyLecture, historyExercises, 4);
          studyPlan.addCourse(history);
          Course finance = new Course("ECN-214", "Finance" , financeLecture, financeExercises, 3);
          studyPlan.addCourse(finance);

        // Première tentative d'emploi du temps
        Schedule schedule1 = new Schedule(studyPlan);
        schedule1.addCourse(finance.getId());
        System.out.println("Emploi du temps 1 :");
        schedule1.print();
        /* On ne sait pas encore très bien quoi faire : on essaye donc
         * sur une copie de l'emploi du temps précédent.
         */
        Schedule schedule2 = new Schedule(schedule1);
        schedule2.addCourse(history.getId());
        System.out.println("Emploi du temps 2 :");
        schedule2.print();

        // Un troisième essai
        Schedule schedule3 = new Schedule(studyPlan);
        schedule3.addCourse(physics.getId());
        System.out.println("Emploi du temps 3 :");
        schedule3.print();
    }
}

