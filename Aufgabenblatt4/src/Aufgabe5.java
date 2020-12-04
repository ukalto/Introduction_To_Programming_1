/*
    Aufgabe 5) Eindimensionale Arrays und File I/O
*/

import java.awt.*;

public class Aufgabe5 {

    private static String[] readFileData(String fileName, int lineStart, int lineEnd) {
        In fileReader = new In(fileName);
        String[] content = new String[lineEnd - 1];
        fileReader.readLine();
        for (int i = 0; i < lineEnd - 1; i++) {
            content[i] = fileReader.readLine();
        }
        return content;
    }

    private static void extractData(String[] dataArray, int[] resultArray, int numColumn, int entriesPerYear) {
        String[] columns = new String[0];
        for (int i = 0; i < resultArray.length; i++) {
            int sumSunYear = 0;
            for (int j = 0; j < entriesPerYear; j++) {
                for (int k = 0; k < dataArray[i].length(); k++) {
                    columns = dataArray[entriesPerYear * i + j].split(";");
                }
                sumSunYear += Integer.parseInt(columns[numColumn]);
            }
            resultArray[i] = sumSunYear;
        }
    }

    private static void drawChart(int[] sunHours) {
        int width = 1400;
        int height = 600;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        //Set the Font and its size
        StdDraw.setFont(new Font("Times", Font.PLAIN, 10));
        //Set the Color
        StdDraw.setPenColor(Color.ORANGE);

        //Bars
        for (int i = 0; i < sunHours.length; i++) {
            // side distance(30)+barPosition(i)*(BarGab(5)+BarLength(15))+BarLength(15)/2 <-- to get the mid coordinate
            // LengthOfBar(i)/number to lower the total height, because of no space+the space from the bottom
            // LengthOfBar(i)/number to lower the total height, because of no space
            // barPosition(i)
            StdDraw.filledRectangle(30 + i * 20 + 15 / 2f,
                    sunHours[i] / 8f + 5,
                    15 / 2f,
                    sunHours[i] / 8f);
        }

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        //BarToBar
        for (int i = 0; i < sunHours.length - 1; i++) {
            StdDraw.line(30 + i * 20 + 15 / 2f, sunHours[i] / 4f + 5, 30 + (i + 1) * 20 + 15 / 2f, sunHours[i + 1] / 4f + 5);
        }

        //Get max and min
        int max = 0;
        int min = 0;
        for (int i = 0; i < sunHours.length; i++) {
            if (i == 0) min = sunHours[0];
            max = Math.max(max, sunHours[i]);
            min = Math.min(min, sunHours[i]);
        }

        StdDraw.setPenRadius(0.002);

        //Draw max and min line
        StdDraw.line(30, max / 4f + 5, width - 30, max / 4f + 5);
        StdDraw.line(30, min / 4f + 5, width - 30, min / 4f + 5);

        //Max and min text
        for (int i = 15; i < width; i += width - 30) {
            StdDraw.text(i, max / 4f + 5, Integer.toString(max));
            StdDraw.text(i, min / 4f + 5, Integer.toString(min));
        }

        //Years
        for (int i = 1955; i < 2020; i++) {
            StdDraw.text((30 + (i - 1955) * 20 + 15 / 2f), 15, Integer.toString(i).substring(2));

        }
    }

    public static void main(String[] args) {
        String[] data = readFileData("weather_data.csv", 2, 781);
        int[] resultArray = new int[65];
        extractData(data, resultArray, 16, 12);
        drawChart(resultArray);
    }
}