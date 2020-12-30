/*
    Aufgabe 3) Zweidimensionale Arrays und StdDraw - Bildverarbeitung "Finding Waldo"
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Aufgabe3 {

    // converts RGB image into a grayscale array
    private static int[][] convertImg2Array(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int) (tempColor.getRed() * 0.3 + tempColor.getGreen() * 0.59 + tempColor.getBlue() * 0.11);
            }

        }
        return imgArray;
    }

    // converts RGB image into a 3D color array
    private static int[][][] convertImg2ColorArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][][] imgArray = new int[3][height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[0][row][col] = tempColor.getRed();
                imgArray[1][row][col] = tempColor.getGreen();
                imgArray[2][row][col] = tempColor.getBlue();
            }
        }
        return imgArray;
    }

    //draws the image array specified by color channels imgArrayR, imgArrayG and imgArrayB into the canvas
    private static void drawImage(int[][] imgArrayR, int[][] imgArrayG, int[][] imgArrayB) {
        // draw color image on the StdDraw window
        StdDraw.enableDoubleBuffering();
        for (int y = 0; y < imgArrayR.length; y++) {
            for (int x = 0; x < imgArrayR[y].length; x++) {
                StdDraw.setPenColor(imgArrayR[y][x], imgArrayG[y][x], imgArrayB[y][x]);
                StdDraw.filledSquare(x, imgArrayR.length - y, 0.5);
            }
        }
        StdDraw.show();
        StdDraw.disableDoubleBuffering();
    }

    //detect waldo by template matching and return its bounding box values
    private static int[] detectWaldo(int[][] imgArrayGrayscale, int[][] templateArray) {
        int lengthx = templateArray.length;
        int lengthy = templateArray[0].length;
        int minSAD = calcSAD(
                trimMainPicture(
                        imgArrayGrayscale,
                        lengthx,
                        lengthy,
                        0,
                        0),
                templateArray);
        int[] boundingBox = new int[4];

        for (int i = 0; i < imgArrayGrayscale.length - lengthx; i++) {
            for (int j = 0; j < imgArrayGrayscale[i].length - lengthy; j++) {
                int currentSAD = calcSAD(
                        trimMainPicture(
                                imgArrayGrayscale,
                                lengthx,
                                lengthy,
                                i,
                                j),
                        templateArray);
                if (minSAD > currentSAD) {
                    minSAD = currentSAD;
                    boundingBox[0] = i;
                    boundingBox[1] = j;
                    boundingBox[2] = i + lengthx - 1;
                    boundingBox[3] = j + lengthy - 1;
                }
            }
        }

        for (int i = 0; i < boundingBox.length; i++) {
            System.out.println(boundingBox[i]);
        }

        return boundingBox;
    }

    //Trims the main picture to the same size as the template picture to calculate the SAD after
    private static int[][] trimMainPicture(int[][] workArray, int height, int width, int distanceTop, int distanceLeft) {
        int[][] resultArray = new int[height][width];
        for (int i = distanceTop, x = 0; i < height + distanceTop; i++, x++) {
            for (int j = distanceLeft, y = 0; j < width + distanceLeft; j++, y++) {
                resultArray[x][y] = workArray[i][j];
            }
        }
        return resultArray;
    }

    //Calculates the SAD, by subtracting all values of both pictures (template - trimmed main picture)
    private static int calcSAD(int[][] pic1, int[][] pic2) {
        int sad = 0;
        for (int i = 0; i < pic1.length; i++) {
            for (int j = 0; j < pic1[i].length; j++) {
                sad += Math.abs(pic2[i][j] - pic1[i][j]);
            }
        }
        return sad;
    }

    public static void main(String[] args) {
        //waldo1
        //String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/lht2cy0GFclxbl2/download"; //waldo1.png
        //String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/f9onCE9vf89ZYLJ/download"; //template1.png

        //waldo2
        //String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/3HYvf4xBkiZUYr1/download"; //waldo2.png
        //String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/spG8LoK4x6HqOkf/download"; //template2.png

        //waldo3
        String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/9RmCwGkOjgwwkzh/download"; //waldo3.png
        String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/CDVrqihS7t9lfvm/download"; //template3.png


        BufferedImage img = null;
        // try to open image file
        try {
            URL url_img_waldo = new URL(linkWaldo);
            img = ImageIO.read(url_img_waldo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        BufferedImage template = null;
        // try to open template image file
        try {
            URL url_img_template = new URL(linkTemplate);
            template = ImageIO.read(url_img_template);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // set StdDraw window size based on the image size
        int width = img.getWidth();
        int height = img.getHeight();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        //extract color channels R,G,B
        int[][][] imgArray = convertImg2ColorArray(img);
        int[][] imgArrayR = imgArray[0];
        int[][] imgArrayG = imgArray[1];
        int[][] imgArrayB = imgArray[2];
        //convert input image and template image to grayscale, because detection of Waldo works by gray value comparison
        int[][] imgArrayGrayscale = convertImg2Array(img);
        int[][] templateArray = convertImg2Array(template);

        int[] boundingBox = detectWaldo(imgArrayGrayscale, templateArray);

        if (boundingBox != null) {
            //TODO: heben Sie die gefundene Waldo Bounding Box im Bild hervor, indem Sie den Rest des Bildes verdunkeln
            for (int i = 0; i < imgArrayGrayscale.length; i++) {
                for (int j = 0; j < imgArrayGrayscale[i].length; j++) {
                    if (i < boundingBox[0] || j < boundingBox[1] || i > boundingBox[2] || j > boundingBox[3]) {
                        if (imgArrayR[i][j] < 150) imgArrayR[i][j] = 0;
                        else imgArrayR[i][j] -= 150;
                        if (imgArrayG[i][j] < 150) imgArrayG[i][j] = 0;
                        else imgArrayG[i][j] -= 150;
                        if (imgArrayB[i][j] < 150) imgArrayB[i][j] = 0;
                        else imgArrayB[i][j] -= 150;
                    }
                }
            }
        }

        drawImage(imgArrayR, imgArrayG, imgArrayB);
    }
}





