/*
    Aufgabe 2) Eindimensionale Arrays
*/
public class Aufgabe2 {

    private static void printArrayWithComma(int[] workArray) {
        for (int i = 0; i < workArray.length; i++) {
            System.out.print(i < workArray.length - 1 ? workArray[i] + "," : workArray[i]);
        }
        System.out.println();
    }

    private static void printArray(int[] workArray) {
        for (int i = 0; i < workArray.length; i++) {
            System.out.print(workArray[i] + " ");
        }
        System.out.println();
    }

    private static void genArray(int[] filledArray) {
        int value = 12;
        for (int i = 0; i < filledArray.length; i++) {
            if (value % 9 == 0)
                filledArray[i] = 0;
            else {
                filledArray[i] = value;
            }
            value += 4;
        }
    }

    private static void genNewArrayContent(int[] workArray) {
        int[] copiedArray = new int[workArray.length + 2];
        for (int i = 0; i < copiedArray.length; i++) {
            if (i == 0 || i == copiedArray.length - 1) {
                copiedArray[0] = 100;
                copiedArray[copiedArray.length - 1] = 200;
            } else {
                copiedArray[i] = workArray[i - 1];
            }
        }
        printArray(copiedArray);
    }

    private static void flipArrayFor(int[] filledArray) {
        String output = "";
        for (int i = filledArray.length - 1; i >= 0; i--) {
            output += filledArray[i] + " ";
        }
        System.out.println("for-schleife: " + output);
    }

    private static void flipArrayWhile(int[] filledArray) {
        String output = "";
        int i = filledArray.length;
        while (i > 0) {
            output += filledArray[i - 1] + " ";
            i--;
        }
        System.out.println("while-schleife: " + output);
    }

    private static void searchMinMaxAverage(int[] filledArray) {
        int smallestNumber = filledArray[0];
        int highestNumber = filledArray[0];
        int sum = 0;
        for (int i = 0; i < filledArray.length; i++) {
            if (smallestNumber > filledArray[i]) {
                smallestNumber = filledArray[i];
            }
            if (highestNumber < filledArray[i]) {
                highestNumber = filledArray[i];
            }
            sum += filledArray[i];
        }

        System.out.println(smallestNumber + " " + (sum / filledArray.length) + " " + highestNumber);
    }


    public static void main(String[] args) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        int[] filledArray1 = {1, 4, 7, 0, 3, 6, 2, 8};
        int[] filledArray2 = {4, 8, 1, 5, 2};
        int[] filledArray3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] filledArray4 = {61, 13, 19, 10, 2, 33, 41, 73, 0, 56, 94, 6, 45, 84, 23};
        int[] emptyArray = new int[20];

        printArrayWithComma(filledArray1);

        genArray(emptyArray);
        printArray(emptyArray);

        genNewArrayContent(filledArray2);

        flipArrayWhile(filledArray3);
        flipArrayFor(filledArray3);
        searchMinMaxAverage(filledArray4);
    }
}