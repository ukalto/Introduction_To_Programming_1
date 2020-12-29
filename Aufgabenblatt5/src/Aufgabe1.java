/*
    Aufgabe 1) Zweidimensionale Arrays - Diverse Methoden
*/

import java.util.Arrays;

public class Aufgabe1 {

    private static int[][] genFilledArray(int n) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[][] array = new int[n][n];
        boolean check = false;
        for (int i = 0, start = 1; i < array.length; i++, start++) {
            for (int j = 0, x = start; j < array[i].length; j++) {
                if (x == n) check = true;
                array[i][j] = x;
                if (!check) {
                    x++;
                } else {
                    x--;
                }
            }
            check = false;
        }
        return array;
    }

    private static void shiftLinesInArray(int[][] workArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[] helparray = workArray[workArray.length - 1];
        for (int i = workArray.length - 1; i > 0; i--) {
            workArray[i] = workArray[i - 1];
        }
        workArray[0] = helparray;
    }

    private static int[][] extendArray(int[][] inputArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int highestLength = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (highestLength < inputArray[i].length) highestLength = inputArray[i].length;
        }
        int[][] outputArray = new int[inputArray.length][highestLength];

        for (int i = 0; i < outputArray.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0, x = highestLength - inputArray[i].length, y = 0; j < highestLength; j++, x--) {
                    if (x > 0) outputArray[i][j] = 0;
                    else {
                        outputArray[i][j] = inputArray[i][y];
                        y++;
                    }
                }
            } else {
                for (int j = 0; j < highestLength; j++) {
                    if ((j - inputArray[i].length) < 0) outputArray[i][j] = inputArray[i][j];
                    else outputArray[i][j] = 0;
                }
            }
        }
        return outputArray;
    }

    private static int[] reformatArray(int[][] inputArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[] result = new int[inputArray.length];
        for (int i = 0, sum = 0; i < result.length; i++, sum = 0) {
            for (int j = inputArray[i].length - 1, x = 0; j >= 0; j--, x++) {
                sum += inputArray[i][j] * Math.pow(2, x);
            }
            result[i] = sum;
        }
        return result;
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[i] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] array = genFilledArray(2);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2}, {2, 1}}));
        System.out.println();

        array = genFilledArray(4);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2, 3, 4}, {2, 3, 4, 3}, {3, 4, 3, 2}, {4, 3, 2, 1}}));
        System.out.println();

        array = genFilledArray(7);
        printArray(array);
        System.out.println();


        int[][] array1 = new int[][]{{1, 3, 5}, {6, 2, 1}, {0, 7, 9}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{0, 7, 9}, {1, 3, 5}, {6, 2, 1}}));
        printArray(array1);
        System.out.println();

        array1 = new int[][]{{1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}, {6, 3, 0}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{6, 3, 0}, {1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}}));
        printArray(array1);
        System.out.println();


        int[][] array2 = new int[][]{{4}, {1, 2, 3}, {5, 6}, {7, 8, 9, 1}};
        int[][] array2new1 = extendArray(array2);
        printArray(array2new1);
        assert (Arrays.deepEquals(array2new1, new int[][]{{0, 0, 0, 4}, {1, 2, 3, 0}, {0, 0, 5, 6}, {7, 8, 9, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {1, 1}, {1, 0, 0, 0}, {1, 1, 0, 1}, {1}, {1}};
        int[][] array2new2 = extendArray(array2);
        printArray(array2new2);
        assert (Arrays.deepEquals(array2new2, new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 3, 2}, {5, 1}, {6, 8, 5, 4}, {9, 4, 1, 9, 2}, {1, 8, 7, 5, 3, 2, 5}, {3}};
        int[][] array2new3 = extendArray(array2);
        printArray(array2new3);
        assert (Arrays.deepEquals(array2new3, new int[][]{{0, 0, 0, 0, 1, 3, 2}, {5, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 6, 8, 5, 4}, {9, 4, 1, 9, 2, 0, 0}, {1, 8, 7, 5, 3, 2, 5}, {3, 0, 0, 0, 0, 0, 0}}));
        System.out.println();


        int[][] array3 = new int[][]{{1, 0, 1, 1}, {0, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0}, {1, 1, 1, 1, 1}};
        int[] array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{11, 3, 24, 2, 2, 31}));
        System.out.println();

        array3 = array2new2.clone();
        array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{176, 124, 3, 128, 13, 128, 1}));
        System.out.println();
    }
}