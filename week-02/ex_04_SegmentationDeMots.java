import java.util.Scanner;

public class ex_04_SegmentationDeMots {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String phrase;
        System.out.println("Entrez une chaine :");
        phrase = scanner.nextLine();
        TokenizableString toToken = new TokenizableString(phrase);
        toToken.tokenize();
    }

    public static class TokenizableString{
        private String contenu;
        private int debut = 0;
        private int len;

        public TokenizableString(String txt){
            contenu = txt;
        }

        public boolean issep(char c){
            return (c==' ');
        }

        public boolean nextToken(){
            int taille = contenu.length();

            while ((debut<taille)&&issep(contenu.charAt(debut))) debut++;
            
            len = 0;

            for (int i = debut; ((i < taille)&&!issep(contenu.charAt(i))); len++, i++);

            return (len!=0);
        }

        public void tokenize(){
            System.out.println("Les mots de \""+contenu+"\" sont:");
            debut = 0;
            len = 0;

            while (nextToken()){
                System.out.println("'"+contenu.substring(debut,debut+len)+"'");
                debut +=len;

            }
        }
    }
}
