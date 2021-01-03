/*
    Aufgabe 1) Zweidimensionale Arrays und Gameplay - Sokoban
*/

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Aufgabe1 {
    private static final int SQUARE_SIZE = 40;

    public static void main(String[] args) {
        String[] allLevels = readLevels();
        int levelId = 0;
        int[][] goals = new int[numberOfGoals(allLevels[levelId])][];
        char[][] level = newLevel(goals, allLevels[levelId]);
        boolean gameRunning = true;
        int moveDirection = 0;
        int stepsLevel = 0;
        int stepsTotal = 0;

        setWindowSize(level.length, level[0].length);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        drawGame(level, goals);

        while (gameRunning) {
            // up -> right up
            // down -> left down
            // left -> left up
            // right -> right down
            // restart -> r
            // to next level -> t
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                moveDirection = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDirection = 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                moveDirection = 3;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                moveDirection = 4;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                stepsLevel = 0;
                goals = new int[numberOfGoals(allLevels[levelId])][];
                level = newLevel(goals, allLevels[levelId]);
                drawGame(level, goals);
                StdDraw.pause(200);
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
                if (levelId < allLevels.length - 1) { // skip to next level
                    stepsLevel = 0;
                    levelId++;
                    goals = new int[numberOfGoals(allLevels[levelId])][];
                    level = newLevel(goals, allLevels[levelId]);
                    setWindowSize(level.length, level[0].length);
                    drawGame(level, goals);
                    StdDraw.pause(200);
                } else { // end game
                    gameRunning = false;
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                }
            }

            if (moveDirection != 0) {
                if (move(level, moveDirection)) {
                    stepsLevel++;
                }
                moveDirection = 0;
                drawGame(level, goals);
                if (won(level, goals)) {
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "Steps: " + stepsLevel);
                    StdDraw.pause(2000);
                    stepsTotal += stepsLevel;
                    stepsLevel = 0;
                    if (levelId < allLevels.length - 1) { // load next level
                        levelId++;
                        goals = new int[numberOfGoals(allLevels[levelId])][];
                        level = newLevel(goals, allLevels[levelId]);
                        setWindowSize(level.length, level[0].length);
                        drawGame(level, goals);
                    } else { // end game
                        gameRunning = false;
                        showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                    }
                }
                StdDraw.pause(200);
            }
        }
    }

    // reads levels from file / first line is number of levels
    private static String[] readLevels() {
        In reader = new In("sokoban_level.csv");
        int numberOfLevels = reader.readInt();
        int counter = -1; // starts at -1 because first line is empty after reading int
        String[] levels = new String[numberOfLevels];
        while (!reader.isEmpty()) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                counter++;
                levels[counter] = "";
            } else {
                levels[counter] += line + "\n";
            }
        }
        return levels;
    }

    // returns level as char array and fills goal positions into goals array
    private static char[][] newLevel(int[][] goals, String levelString) {
        // calculate array size
        int xSize = 0;
        int ySize = 0;
        int counter = 0;

        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == '\n') {
                ySize++;
                if (counter > xSize) {
                    xSize = counter;
                }
                counter = 0;
            } else {
                counter++;
            }
        }

        // fill array and goals
        char[][] levelArr = new char[ySize][xSize];
        int goalCounter = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < levelString.length(); i++) {
            char item = levelString.charAt(i);
            switch (item) {
                case '.':
                    levelArr[y][x] = ' ';
                    goals[goalCounter] = new int[]{x, y};
                    goalCounter++;
                    x++;
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                case '#':
                case '$':
                case '@':
                case ' ':
                    levelArr[y][x] = item;
                    x++;
                    break;
                default:
                    break;
            }
        }

        return levelArr;
    }

    // returns the total number of goals in the level
    private static int numberOfGoals(String levelString) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int amountOfGoals = 0;
        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == '.') amountOfGoals++;
        }
        return amountOfGoals;
    }

    // calculates based on the current position and the direction the new position coordinates
    private static int[] adjacentPosition(int[] position, int direction) {
        switch (direction) {
            case 1:
                return new int[]{position[0], position[1] - 1};
            case 2:
                return new int[]{position[0], position[1] + 1};
            case 3:
                return new int[]{position[0] - 1, position[1]};
            case 4:
                return new int[]{position[0] + 1, position[1]};
        }
        return new int[]{-1, -1};
    }

    // returns position of the figure. [0] = x, [1] = y
    private static int[] figurePosition(char[][] level) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[] currentPosition = new int[2];
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == '@') {
                    currentPosition[0] = j;
                    currentPosition[1] = i;
                }
            }
        }
        return currentPosition;
    }

    // moves figure and box if they don't hit an obstacle
    // returns true if figure was moved
    private static boolean move(char[][] level, int direction) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[] oldPosition = figurePosition(level);
        int[] newPosition = adjacentPosition(oldPosition, direction);
        int[] boxNewPosition = adjacentPosition(newPosition, direction);
        switch (level[newPosition[1]][newPosition[0]]) {
            case '#':
                return false;
            case ' ':
            case '.':
                level[newPosition[1]][newPosition[0]] = level[oldPosition[1]][oldPosition[0]];
                level[oldPosition[1]][oldPosition[0]] = ' ';
                return true;
            case '$':
                if (level[boxNewPosition[1]][boxNewPosition[0]] != '#' && level[boxNewPosition[1]][boxNewPosition[0]] != '$') {
                    level[boxNewPosition[1]][boxNewPosition[0]] = level[newPosition[1]][newPosition[0]];
                    level[newPosition[1]][newPosition[0]] = level[oldPosition[1]][oldPosition[0]];
                    level[oldPosition[1]][oldPosition[0]] = ' ';
                    return true;
                } else return false;
        }
        return false;
    }

    // returns current position of all boxes
    private static int[][] boxPositions(char[][] level, int numberOfBoxes) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[][] boxes = new int[numberOfBoxes][2];
        for (int i = 0, x = 0, y = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == '$') {
                    boxes[x][0] = j;
                    boxes[x][1] = i;
                    x++;
                    if (y == 0) y = 1;
                    else y = 0;
                }
            }
        }
        return boxes;
    }

    // returns true if all boxes are on a goal
    private static boolean won(char[][] level, int[][] goals) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        boolean check = true;
        int[][] boxes = boxPositions(level, goals.length);
        for (int i = 0; check && i < goals.length; ++i) {
            check = Arrays.equals(boxes[i], goals[i]);
        }
        return check;
    }

    // helping method to set the StdDraw window size and the scaling of the axis
    private static void setWindowSize(int ySquares, int xSquares) {
        StdDraw.setCanvasSize(SQUARE_SIZE * xSquares, SQUARE_SIZE * ySquares);
        StdDraw.setXscale(0, SQUARE_SIZE * xSquares);
        StdDraw.setYscale(0, SQUARE_SIZE * ySquares);
    }

    // helping method for writing text in the StdDraw window
    private static void showText(double x, double y, String text) {
        StdDraw.clear(Color.white);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(x, y, text);
        StdDraw.show();
    }

    // draws the current level with all elements
    private static void drawGame(char[][] level, int[][] goals) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        for (int i = 0; i < goals.length; i++) {
            if (level[goals[i][1]][goals[i][0]] == ' ') level[goals[i][1]][goals[i][0]] = '.';
        }
        for (int i = level.length - 1, y = 0; i >= 0; i--, y++) {
            for (int x = 0; x < level[i].length; x++) {
                switch (level[i][x]) {
                    case '#':
                        StdDraw.picture(20 + x * SQUARE_SIZE, 20 + y * SQUARE_SIZE, "wall.png", SQUARE_SIZE, SQUARE_SIZE);
                        break;
                    case '$':
                        StdDraw.picture(20 + x * SQUARE_SIZE, 20 + y * SQUARE_SIZE, "box.png", SQUARE_SIZE, SQUARE_SIZE);
                        break;
                    case '@':
                        StdDraw.picture(20 + x * SQUARE_SIZE, 20 + y * SQUARE_SIZE, "figure.png", SQUARE_SIZE, SQUARE_SIZE);
                        break;
                    case '.':
                        StdDraw.picture(20 + x * SQUARE_SIZE, 20 + y * SQUARE_SIZE, "endpoint.png", SQUARE_SIZE, SQUARE_SIZE);
                        break;
                    case ' ':
                        StdDraw.setPenColor(Color.white);
                        StdDraw.filledSquare(20 + x * SQUARE_SIZE, 20 + y * SQUARE_SIZE, 20);
                        break;
                }
            }
        }
        StdDraw.show();
    }
}