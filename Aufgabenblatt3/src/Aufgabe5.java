/*
    Aufgabe 5) Kreuzmuster mit Rechtecken => Rekursiv vs. Iterativ
*/
public class Aufgabe5 {

    static int counter = 0;
    private static void drawPatternRecursive(int x, int y, int l, boolean c) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (x == 0 && y == 0) {
            StdDraw.enableDoubleBuffering();
            StdDraw.setCanvasSize(512, 512);
            StdDraw.setScale(-512, 512);
        }
        if (l >= 16) {
            drawPatternRecursive(x + l / 2, y - l / 2, l / 2, !c);
            drawPatternRecursive(x - l / 2, y + l / 2, l / 2, !c);
            drawPatternRecursive(x - l / 2, y - l / 2, l / 2, !c);
            //Die nachfolgende Code-Line muss auskommentiert werden um das dargestellte Muster 1b nachzustellen: Zusatzfrage 3.
            counter ++;
            drawPatternRecursive(x + l / 2, y + l / 2, l / 2, !c);
            StdDraw.setPenColor(c ? StdDraw.ORANGE : StdDraw.BLUE);
            StdDraw.filledRectangle(x, y, l, l * 0.05);
            StdDraw.filledRectangle(x, y, l * 0.05, l);
        }

        StdDraw.show();
    }

    private static void drawPatternIterative(int width) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        //x = -512;
        //y = 512;
        double secondW = 16;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setScale(-512, 512);

        //4^5
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int y = width; y > -width; y -= 16) {
            for (int x = -width; x < width; x += 16) {
                StdDraw.filledRectangle(x, y, secondW, secondW * 0.05);
                StdDraw.filledRectangle(x, y, secondW * 0.05, secondW);
            }
        }
        secondW *= 2;
        width -= 32;
        //4^4
        StdDraw.setPenColor(StdDraw.ORANGE);
        for (int y = width; y >= -width; y -= 32) {
            for (int x = -width; x <= width; x += 32) {
                StdDraw.filledRectangle(x, y, secondW, secondW * 0.05);
                StdDraw.filledRectangle(x, y, secondW * 0.05, secondW);
            }
        }
        secondW *= 2;
        width -= 32;

        //4^3
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int y = width; y >= -width; y -= 64) {
            for (int x = -width; x <= width; x += 64) {
                StdDraw.filledRectangle(x, y, secondW, secondW * 0.05);
                StdDraw.filledRectangle(x, y, secondW * 0.05, secondW);
            }
        }
        secondW *= 2;
        width -= 64;
        //4^4
        StdDraw.setPenColor(StdDraw.ORANGE);
        for (int y = width; y >= -width; y -= 128) {
            for (int x = -width; x <= width; x += 128) {
                StdDraw.filledRectangle(x, y, secondW, secondW * 0.05);
                StdDraw.filledRectangle(x, y, secondW * 0.05, secondW);
            }
        }
        secondW *= 2;
        width -= 128;
        //4^3
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int y = width; y >= -width; y -= width) {
            for (int x = -width; x <= width; x += width) {
                StdDraw.filledRectangle(x, y, secondW, secondW * 0.05);
                StdDraw.filledRectangle(x, y, secondW * 0.05, secondW);
            }
        }
        //4^0
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.filledRectangle(0, 0, width * 2, width * 0.1);
        StdDraw.filledRectangle(0, 0, width * 0.1, width * 2);

        StdDraw.show();
    }

    public static void main(String[] args) {
        drawPatternRecursive(0, 0, 512, true);
        System.out.println(counter);
        drawPatternIterative(512);
    }
}


//Zusatzfrage 1: 1+4+4^2+4^3+4^4+4^5=1365
//Zustazfrage 2: 4^5= 1024