import java.util.Arrays;

/*
    Aufgabe 2) Zweidimensionale Arrays - Sortieren und Filtern
*/
public class Aufgabe2 {

    private static double[][] genMeanFilter(int n) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        double[][] outputArray = new double[n][n];
        if (n % 2 != 0 && n >= 1) {
            for (int i = 0; i < outputArray.length; i++) {
                for (int j = 0; j < outputArray[i].length; j++) {
                    outputArray[i][j] = (1.0 / (n * n));
                }
            }
            return outputArray;
        } else return null;
    }

    private static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        double[][] outputArray = new double[workArray.length][workArray[0].length];
        for (int i = 0; i <= (workArray.length - filterArray.length); i++) {
            for (int j = 0; j <= (workArray[i].length - filterArray[i].length); j++) {
                double sum = 0;
                for (int k = 0; k < filterArray.length; k++) {
                    for (int l = 0; l < filterArray[k].length; l++) {
                        sum += (filterArray[k][l] * workArray[k + i][l + j]);
                    }
                }
                if (workArray.length == filterArray.length || workArray[0].length == filterArray[i].length)
                    outputArray[(workArray.length / 2) + i][(workArray.length / 2) + j] = sum;
                else outputArray[(workArray.length / 2) + i - 1][(workArray.length / 2) + j - 1] = sum;
            }
        }
        return outputArray;
    }

    private static void print(double[][] workArray) {
        if (workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray[y].length; x++) {
                    System.out.printf("%.2f", workArray[y][x]);
                    System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] myResultArray;

        double[][] myFilter1 = genMeanFilter(3);
        print(myFilter1);
        double[][] myFilter2 = genMeanFilter(5);
        print(myFilter2);

        double[][] myArray1 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};
        print(myArray1);

        myResultArray = applyFilter(myArray1, myFilter1);
        print(myResultArray);
        myResultArray = applyFilter(myArray1, myFilter2);
        print(myResultArray);

        double[][] myArray2 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        print(myArray2);
        //TODO: Erstellen Sie einen Shiftfilter, wenden Sie ihn auf myArray2 an und geben Sie das Ergebnis mittels print() aus
        double[][] myFilter3 = {{0, 1, 0}, {0, 0, 0}, {0, 0, 0}};
        myResultArray = applyFilter(myArray2, myFilter3);
        print(myResultArray);

        double[][] myArray3 = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};
        double[][] myFilter4 = {{1, 0, 0}, {1, 2, 0}, {0, 0, 3}};
        myResultArray = applyFilter(myArray3, myFilter4);
        print(myResultArray);
    }
}