/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
public class Aufgabe1 {

    /*
    Alle Methoden sind nur in der Klasse aufrufbar, weil die Methoden Privat sind.

    f0 erstellt anfangs eine int und eine String Variable
    Die String variable erhält den Wert des übergebenen Strings
    Danach wird die while Schleife solange die String Länge vom String ss sprich vom String s größer
    als 0 ist durchgelaufen. Danach wird in der if Abfrage abgebrfragt, ob die int Variable xednI = 0 ist
    oder ob der character an der Stelle xednI größer gleich der character xednI-1 ist, sprich als an der
    derzeitigen Stelle im Vergleich zu der davor. Wenn eines davon zutrifft wird xednI auf den derzeitigen
    Wert von xednI plus 1 dazugerechnet. Wenn xednI != null oder der character an der Stelle xednI kleiner
    als der character an der Stelle xednI-1 ist. Dann wird der String ss neu gebaut. ss erhält die Zeichen
    von 0 bis xednI-1. Dazu wird der character an der Stelle xednI hinzugefügt. Dann noch der character
    an der Stelle xednI-1. Schlussendlich noch den substringt von xednI+1 bis der Länge des Strings von ss.
    Danach wird xednI heruntergrzählt. Am Ende wird der fertige String ss returned.

    f1 würde die Länge des gegebenen Strings s ausgeben.

    f2 gibt die 2 addierten werde n + s als ganze Zahl aus.

    f3 returned true oder false abhängig ob char 1 größer gleich char 2 ist.

    f4 gibt einen substring aus. Was so viel heißt wie, dass er den gegebenen s nimmt und
    von n1-n2, bei 0 beginnend, einen neuen String erstellt.
     */

    /*
    private static String sortierBuchstabenNachAlphabet(String s) {
        int xednI = 0;
        String ss = s;
        while (xednI < laengeDesStrings(ss)) {
            if (xednI == 0 || characterGroesserGleichAlsCharacter(ss.charAt(xednI), ss.charAt(xednI - 1))) {
                xednI = addieren(xednI, 1);
            } else {
                ss = teilstringErstellen(ss, 0, xednI - 1) + ss.charAt(xednI) + ss.charAt(xednI - 1) + teilstringErstellen(ss, xednI + 1, laengeDesStrings(ss));
                xednI = addieren(xednI, -1);
            }
        }
        return ss;
    }

    private static int laengeDesStrings(String s) {
        return s.length();
    }

    private static int addieren(int n, int s) {
        return n + s;
    }

    private static boolean characterGroesserGleichAlsCharacter(char c1, char c2) {
        return c1 >= c2;
    }

    private static String teilstringErstellen(String s, int n1, int n2) {
        return s.substring(n1, n2);
    }*/

    private static String sortierBuchstabenNachAlphabet(String text) {
        int index = 0;
        String newText = text;
        while (index < newText.length()) {
            if (index == 0 || newText.charAt(index) >= newText.charAt(index - 1)) {
                index++;
            } else {
                newText = newText.substring(0, index - 1) + newText.charAt(index) + newText.charAt(index - 1) + newText.substring(index + 1);
                index--;
            }
        }
        return newText;
    }

    public static void main(String args[]) {
        System.out.println(sortierBuchstabenNachAlphabet("ab"));
        System.out.println(sortierBuchstabenNachAlphabet("ba"));
        System.out.println(sortierBuchstabenNachAlphabet("aa"));
        System.out.println(sortierBuchstabenNachAlphabet("cba"));
        System.out.println(sortierBuchstabenNachAlphabet("abababab"));
        System.out.println(sortierBuchstabenNachAlphabet("abcfghed"));
        System.out.println(sortierBuchstabenNachAlphabet("abnasnasab"));
        System.out.println(sortierBuchstabenNachAlphabet("najskaghkkjsfvjhbavbdfsan"));
        System.out.println(sortierBuchstabenNachAlphabet("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv"));

        assert (sortierBuchstabenNachAlphabet("ab").equals("ab"));
        assert (sortierBuchstabenNachAlphabet("ba").equals("ab"));
        assert (sortierBuchstabenNachAlphabet("aa").equals("aa"));
        assert (sortierBuchstabenNachAlphabet("cba").equals("abc"));
        assert (sortierBuchstabenNachAlphabet("abababab").equals("aaaabbbb"));
        assert (sortierBuchstabenNachAlphabet("abcfghed").equals("abcdefgh"));
        assert (sortierBuchstabenNachAlphabet("abnasnasab").equals("aaaabbnnss"));
        assert (sortierBuchstabenNachAlphabet("najskaghkkjsfvjhbavbdfsan").equals("aaaabbdffghhjjjkkknnsssvv"));
        assert (sortierBuchstabenNachAlphabet("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv").equals("aaaaabbbbbbbbbdddddffggggjjjjjjjjkkkksssvvvv"));
    }
}


