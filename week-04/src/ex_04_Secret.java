import java.util.Random;


class Utils {
    // genere un entier entre 1 et max (compris)
    public static int randomInt(int max) {
        Random r = new Random();
        int val = r.nextInt();
        val = Math.abs(val);
        val = val % max;
        val += 1;
        return val;
    }
}

abstract class Code {
    private String nom;

    public Code(String nom, String... autreStr) {
        this.nom = nom;
        for (String string : autreStr) {
            this.nom += string;
        }
    }

    public void affiche() {
        System.out.print(this.nom);
    }

    abstract String code(String str);

    abstract String decode(String str);
}

class ACle extends Code {
    private String cle;


    public ACle(String nom, String cle, String... autreStr) {
        super(nom, autreStr);
        this.cle = cle.trim().replace("\\s+", "");
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public int longeur() {
        return this.cle.length();
    }

    @Override
    public void affiche() {
        super.affiche();
        System.out.println(" avec " + this.cle + " comme cle.");
    }

    @Override
    String code(String message) {
        int messageLongeur = message.length();
        String messageCode = "";

        int[] numsMessage = new int[messageLongeur];

        for (int i = 0; i < messageLongeur; i++) {
            numsMessage[i] = (int) message.charAt(i) - 64;
        }
        for (int i = 0; i < messageLongeur; i++) {
            numsMessage[i] += (int) this.cle.charAt(i % this.longeur()) - 64;
            if (numsMessage[i] > 26) {
                numsMessage[i] -= 26;
            }
        }
        for (int i = 0; i < messageLongeur; i++) {
            messageCode += (char) (numsMessage[i] + 64);
        }
        return messageCode;
    }

    @Override
    String decode(String codage) {
        String message = "";
        int charCodage;
        int charKey;
        int difference;
        for (int i = 0; i < codage.length(); ++i) {
            charCodage = codage.charAt(i) - 'A' + 1;
            charKey = cle.charAt(i % this.longeur()) - 'A' + 1;
            difference = (charCodage - charKey + 26) % 26;
            if (difference == 0)
                message += 'Z';
            else
                message += (char) ('A' + difference - 1);
        }
        return message;
    }
}

class ACleAleatoire extends ACle {
    private int lenght;

    public void genereCle() {
        String newKey = "";
        int randomPosition = 0;

        for (int i = 0; i < this.lenght; i++) {
            randomPosition = Utils.randomInt(26);
            newKey += (char) (randomPosition + 'A' - 1);
        }
        this.setCle(newKey);
    }


    public ACleAleatoire(int cle) {
        super("a cle aleatoire", "");
        this.lenght = cle;
        this.genereCle();
    }
}


public class ex_04_Secret {
    public static void main(String[] args) {
        String message = "COURAGEFUYONS";
        String cryptage;


//        // PARTIES A DECOMMENTER AU FUR ET A MESURE SELON l'ENONCE
//		/*
//		// TEST A CLE
        Code acle1 = new ACle("a cle", "EQUINOXE");
        System.out.print("Avec le code : ");
        acle1.affiche();
        cryptage = acle1.code(message);
        System.out.print("Codage de " + message + " : ");
        System.out.println(cryptage);
        System.out.print("Decodage de " + cryptage + " : ");
        System.out.println(acle1.decode(cryptage));
        System.out.println("-----------------------------------");
        System.out.println();
//		// FIN TEST A CLE
////        */
//		/*
//		// TEST A CLE ALEATOIRE
        Code acle2 = new ACleAleatoire(5);
        System.out.print("Avec le code : ");
        acle2.affiche();
        cryptage = acle2.code(message);
        System.out.print("Codage de " + message + " : ");
        System.out.println(cryptage);
        System.out.print("Decodage de " + cryptage + " : ");
        System.out.println(acle2.decode(cryptage));
        System.out.println("-----------------------------------");
        System.out.println();
        // FIN TEST A CLE ALEATOIRE

		/*
        // TEST CESAR
		Code cesar1 = new Cesar("Cesar", 5);
		System.out.print("Avec le code : " );
		cesar1.affiche();
		cryptage = cesar1.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(cesar1.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println();
		// FIN TEST CESAR
		*/
		/*
		// TEST CODAGES
		System.out.println("Test CODAGES: ");
		System.out.println("------------- ");
		System.out.println();


		Code[] tab = {   // Decommentez la ligne suivante
				// si vous avez fait la classe Cesar
				new Cesar("cesar", 5),
				new ACle("a cle", "EQUINOXE") ,
				new ACleAleatoire(5),
				new ACleAleatoire(10)};

		Codages  codes = new Codages(tab);
		codes.test(message);
		// FIN TEST CODAGE
		*/
    }
}
