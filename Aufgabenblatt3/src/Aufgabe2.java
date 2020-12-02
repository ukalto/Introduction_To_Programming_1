/*
    Aufgabe 2) Überladen von Methoden
*/
public class Aufgabe2 {

    private static void addSeparator(String text, char separator) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        String newText = "";
        if (text != null && text.length() >= 2) {
            for (int i = 0; i < text.length(); i++) {
                if (i != 0) {
                    newText += separator;
                    newText += text.charAt(i);
                } else {
                    newText += text.charAt(i);
                }
            }
            System.out.println(newText);
        } else {
            System.out.println(text);
        }
    }

    private static void addSeparator(int number, char separator) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        if (number > 0) {
            addSeparator(Integer.toString(number), separator);
        }
    }

    private static void addSeparator(String text, String separators) {
        if (text != null && separators != null) {
            for (int i = 0; i < separators.length(); i++) {
                addSeparator(text, separators.charAt(i));
            }
        }
    }

    private static void addSeparator(String text) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        addSeparator(text, "$");
    }

    public static void main(String[] args) {
        String text0 = "A";
        String text1 = "AB";
        String text2 = "Hello!";
        String text3 = "-Java-";
        String text4 = " TEST ";

        addSeparator(text0, '?');
        addSeparator(text1, ',');
        addSeparator(text2, ':');
        addSeparator(text3, '-');
        addSeparator(text4, '+');

        addSeparator(1, '$');
        addSeparator(35, '*');
        addSeparator(657, ':');
        addSeparator(2048, '#');
        addSeparator(26348, '+');

        addSeparator(text1, "+#$");
        addSeparator(text2, ":*&!");

        addSeparator(text0);
        addSeparator(text1);
        addSeparator(text2);
    }
}