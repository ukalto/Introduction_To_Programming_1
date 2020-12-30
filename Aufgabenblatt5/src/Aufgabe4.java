/*
    Aufgabe 4) Rekursion + Zweidimensionale Arrays - primitive Landschaftssimulation
*/

import java.awt.*;

public class Aufgabe4 {

    private static final int canvasSize = 500;

    private static Color[][] genLandscape(int size) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        Color[][] color = new Color[size][size];
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < color[i].length; j++) {
                if (Math.random() > 0.1) color[i][j] = Color.GREEN;
                else color[i][j] = Color.GRAY;
            }
        }
        return color;
    }

    private static void drawLandscape(Color[][] landscape) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[i].length; j++) {
                StdDraw.setPenColor(landscape[i][j]);
                StdDraw.filledSquare(
                        i * canvasSize / (float) landscape.length,
                        j * canvasSize / (float) landscape.length,
                        canvasSize / (float) landscape.length);
            }
        }
    }

    private static void simLiquidFlow(Color[][] landscape, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        if (x < 0 || y < 0) return;
        if (landscape[x][y] == Color.GREEN) {
            landscape[x][y] = Color.ORANGE;
            if (Math.random() >= 0.5) simLiquidFlow(landscape, x + 1, y - 1);
            else simLiquidFlow(landscape, x - 1, y - 1);
        } else if (landscape[x][y] == Color.GRAY) {
            if (y + 1 < landscape.length) landscape[x][y + 1] = Color.ORANGE;
            landscape[x][y] = Color.BLACK;
            simLiquidFlow(landscape, x - 1, y);
            simLiquidFlow(landscape, x + 1, y);
        } else if (landscape[x][y] == Color.BLACK) {
            return;
        }
    }

    private static void simSpreadingFire(Color[][] landscape, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        if (landscape[x][y] == Color.GREEN) landscape[x][y] = Color.RED;
        else if (landscape[x][y] == Color.ORANGE) {
            spreadFireInLiquid(landscape, x, y);
        } else return;

        //up
        if (Math.random() >= 0.4 && y + 1 < landscape[x].length) simSpreadingFire(landscape, x, y + 1);
        //left
        if (Math.random() >= 0.4 && x - 1 >= 0) simSpreadingFire(landscape, x - 1, y);
        //right
        if (Math.random() >= 0.4 && x + 1 < landscape.length) simSpreadingFire(landscape, x + 1, y);
        //down
        if (Math.random() >= 0.4 && y - 1 >= 0) simSpreadingFire(landscape, x, y - 1);
    }

    private static void spreadFireInLiquid(Color[][] landscape, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        if (x < 0 || y < 0 || x >= landscape.length || y >= landscape[x].length) return;
        if (landscape[x][y] == Color.ORANGE) {
            landscape[x][y] = Color.RED;
            //left top
            spreadFireInLiquid(landscape, x - 1, y + 1);
            //top
            spreadFireInLiquid(landscape, x, y + 1);
            //right top
            spreadFireInLiquid(landscape, x + 1, y + 1);
            //right
            spreadFireInLiquid(landscape, x + 1, y);
            //right bottom
            spreadFireInLiquid(landscape, x + 1, y - 1);
            //bottom
            spreadFireInLiquid(landscape, x, y - 1);
            //left bottom
            spreadFireInLiquid(landscape, x - 1, y - 1);
            // left
            spreadFireInLiquid(landscape, x - 1, y);
        }
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setScale(0, canvasSize);
        StdDraw.enableDoubleBuffering();

        int size = 100;
        Color[][] landscape = genLandscape(size);

        simLiquidFlow(landscape, size / 2, size - 1);
        drawLandscape(landscape);
        StdDraw.show();
        StdDraw.pause(1000);

        landscape[75][25] = Color.GREEN;
        //simSpreadingFire(landscape, 25, 75);
        drawLandscape(landscape);
        StdDraw.show();
    }
}
