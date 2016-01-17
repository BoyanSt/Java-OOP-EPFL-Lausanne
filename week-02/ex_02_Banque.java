
public class ex_02_Banque {
    public static void main(String[] args) {

        Client firstClient = new Client("Pedro", "Geneve", 1000, 2000);
        Client secondCliet = new Client("Alexandra", "Lausanne", 3000, 4000);
        firstClient.printInfo();
        secondCliet.printInfo();
        firstClient.calcInterest();
        secondCliet.calcInterest();
        firstClient.printInfo();
        secondCliet.printInfo();

    }

    public static class Client{
        private String name;
        private String city;
        private privateAccount privateAccount;
        private savingAccount savingAccount;

        public Client(String name, String city, double amount1, double amount2){
            this.name = name;
            this.city = city;
            this.privateAccount = new privateAccount();
            privateAccount.setAmount(amount1);
            this.savingAccount = new savingAccount();
            savingAccount.setAmount(amount2);
        }

        public void calcInterest(){
            privateAccount.amount *= (privateAccount.interest + 1);
            savingAccount.amount *= (savingAccount.interest + 1);
        }

        public void printInfo(){
            System.out.println("Client " + name + " de " + city );
            System.out.println("Private account: " + privateAccount.amount + " Francs");
            System.out.println("Saving account: " + savingAccount.amount + " Francs");
            System.out.println();
        }
    }

    public static class savingAccount{
        private double amount = 0;
        private double interest = 0.02;

        public void setAmount(double amount1){

            amount = amount1;
        }
    }

    public static class privateAccount{
        private double amount = 0;
        private double interest = 0.01;

        public void setAmount(double amount1){

            amount = amount1;
        }
    }
}
