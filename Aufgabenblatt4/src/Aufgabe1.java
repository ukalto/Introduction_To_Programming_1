import java.lang.reflect.Array;

/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {
    
    private static void genArray(int[] filledArray){
        int value = 5;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = value;
            value += 5;
        }
    }
    
    private static void printFilteredArrayContent(int[] workArray){
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if(copiedArray[i] % 4 == 0){
                copiedArray[i] = 0;
            }
        }
        printArray(copiedArray);
    }
    
    private static void genNewArrayContent(int[] workArray){
        int[] helpArray = new int[15];
        int value = 7;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = value;
            value += 7;
        }
        workArray = helpArray;
        printArray(workArray);
    }
    
    private static void printArray(int[] workArray){
        for (int i = 0; i < workArray.length; i++) {
            System.out.print(workArray[i]+ " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] filledArray = new int[15];
        genArray(filledArray);
        printArray(filledArray);
        
        printFilteredArrayContent(filledArray);
        printArray(filledArray);
        
        filledArray[0] = 2020;
        printArray(filledArray);
        
        genNewArrayContent(filledArray);
        printArray(filledArray);
    }
    
    //**************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ***
    //**************************************************************************
    //Frage 1: Weil einerseits die i Variable in der For-Schleife ins negative zählt durch -- und andererseits wird es durch <= nie das letzte Element erreichen deswegen muss man < schreiben.
    //         Beides würde eine OutOfBoundsException werfen bzw. wirft eine.
    //
    //Frage 2: Das Array wird auch befüllt. Da es nur eine Referenz ist würde es keinen Sinn machen diese zu returnen, da sich das return lediglich auf die Referenznummer beziehen würde anstatt auf die erstellten Objekte selber.
    //
    //Frage 3: Das kopierte Array referenziert lediglich auf das eigentliche Array. Das wird shallow clone genannt. In dem Fall brauchen wir ein .clone nach dem workArray um das Array wirklich zu klonen. Das nennt man dann deep clone.
    //
    //Frage 4: Im genNewArrayContent(filledArray); wird das filledArray nicht endgültig geändert und nur die Änderungen innerhalb der Methode werden ausgeprintet. Allerdings verweist das filled Array in der Main Methode in der darauffolgenden Zeile auf den
    //         vorherigen Stand, da sich die Objekte nicht durch die Methode genNewArrayContent endgültig verändern.
    //
    //Zusatzfragen:
    //
    //1. int/Integer
    //2. Nein, ein Array muss nicht direkt initialisiert werden es kann auch einfach erstellt werden und später initialisiert werden. BSP.: int[] arr;
    //3. Per se kann die Länge eines Arrays nicht verändert werden. Sie wird entweder beim deklarieren initialisiert oder kann auch erst später initialisiert werden. Andernfalls muss ich ein neues Array erstellen.
    //4. Ich muss es deep klonen. Was so viel bedeuted wie: Ich initialisiere ein neues Array und dieses klone ich dann auf das Array meiner begirde. Mit der ".clone" Methode. Man könnte auch noch die ".copyOf" Methode verwenden.
    //5. Ja, die Indexzählung beginnt immer bei 0.
    //6. Die Arrays per se vergleicht man meist nicht mit == sondern mit .equals. Sonst vergleicht man die Bezeichnung(id) des Arrays. Meist vergleicht man allerdings die Objekte in einem Array.
}