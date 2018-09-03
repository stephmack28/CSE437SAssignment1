package ScoopsProject;

import java.awt.*;
import sedgewick.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Scoop> scoops;
    private Cone cone;
    private int strikes;
    private int score;
    private static Color[] palette = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK};
    private long time;
    private long endTime;
    private long delta = 1000;
    private boolean paused = false;
    private boolean quit = false;

    /**
     * Creates a new game instance
     */
    Game() {
        scoops = new ArrayList<>();
        cone = new Cone();
        strikes = 0;
        score = 0;
        time = System.currentTimeMillis();
        endTime = time;
    }

    Game(int scoreIn, int strikesIn, ArrayList<Scoop> scoopsIn) {
        scoops = scoopsIn;
        cone = new Cone();
        strikes = strikesIn;
        score = scoreIn;
        time = System.currentTimeMillis();
        endTime = time;
    }

    /**
     * This method is called when the player presses 'space' to start playing the game
     */
    public void start() {
        redraw();

        while (strikes < 3) {
            time = System.currentTimeMillis();
            if (time >= endTime) {
                scoops.add(new Scoop(800, 600, palette[(int)(Math.random()*5)]));
                endTime += delta;
            }
            if (StdDraw.isKeyPressed(37)) {
                if (cone.getXPosition() - (cone.getBaseWidth() / 2) > 0) {
                    cone.moveLeft();
                }
            }
            else if (StdDraw.isKeyPressed(39)) {
                if (cone.getXPosition() + (cone.getBaseWidth() / 2) < 600) {
                    cone.moveRight();
                }
            }
            if (StdDraw.isKeyPressed(27)) {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(300, 400, "PAUSED");
                StdDraw.text(300, 300, "Press 'S' to Save Game");
                StdDraw.text(300, 275, "Press 'Q' to Save Game");
                StdDraw.pause(200);
                paused = true;
                long delayTime = endTime - time;
                while (paused) {
                    if (StdDraw.isKeyPressed(27)) {
                        StdDraw.pause(200);
                        paused = false;
                        endTime = System.currentTimeMillis() + delayTime;
                    }
                    if (StdDraw.isKeyPressed(83)) {
                        try (PrintWriter out = new PrintWriter("savegame.txt")) {
                            out.println(score + " " + strikes + " ");
                            for (Scoop s : scoops) {
                                if (!s.getScored()) {
                                    out.println(s.getX() + " " + s.getY());
                                }
                            }
                            out.close();
                        }
                        catch (FileNotFoundException ex) {

                        }
                        StdDraw.text(300, 250, "Game Saved!");
                    }
                    if (StdDraw.isKeyPressed(81)) {
                        strikes = 3;
                        quit = true;
                        paused = false;
                    }
                }
            }
            checkForCollision();
            redraw();
        }

        if (!quit) {
            StdDraw.clear();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(300, 400, "GAME OVER");
            StdDraw.pause(3000);
        }
    }

    /**
     * Method to draw cone and scoops
     */
    private void redraw() {
        StdDraw.show(5);
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(580, 800, "Strikes: " + Integer.toString(strikes));
        StdDraw.text(10, 800, "Score: " + Integer.toString(score));
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.filledRectangle(cone.getXPosition(),
                -17,
                cone.getBaseWidth() * 2,
                cone.getBaseHeight() * 2);

        for (Scoop s : scoops) {
            if (!s.getScored()) {
                StdDraw.setPenColor(s.getColor());
                StdDraw.filledCircle(s.getX(), s.getY(), s.getRadius());
            }
        }
        StdDraw.show();
    }

    /**
     * Method to check for collision between objects
     */
    private void checkForCollision() {
        for (Scoop s : scoops) {
            s.moveScoop(4);
            if (s.isScoopOnGround()) {
                if (!s.getScored()) {
                    strikes++;
                }
                s.setScored(true);
            } else {
                // Check for collision with cone
                if (
                    ((s.getX() + s.getRadius()) > cone.getXPosition() - (cone.getBaseWidth() * 2)) &&
                    ((s.getX() - s.getRadius()) < (cone.getXPosition() + (cone.getBaseWidth() * 2))) &&
                    ((s.getY() - s.getRadius()) < (-17 + (cone.getBaseHeight() * 2)))
                ) {
                    // Collision!
                    if (!s.getScored()) {
                        score++;
                    }
                    s.setScored(true);
                }
            }
        }
    }

    /**
     * Game inserts from this method
     * Set game window size and draw initial main screen
     * @param args Input parameters (not needed in this case)
     */
    public static void main(String[] args) {
        boolean playGame = true;
        StdDraw.setCanvasSize(600, 800);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 800);
        while (playGame) {
            StdDraw.show(5);
            StdDraw.clear();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(300, 400, "Scoops");
            StdDraw.text(300, 300, "Press SPACE to play");
            StdDraw.text(300, 275, "Press ESC to pause in game");
            StdDraw.text(300, 250, "Press 'L' to load a saved game");
            StdDraw.show();
            if (StdDraw.isKeyPressed(32)) {
                Game game = new Game();
                game.start();
            }
            if (StdDraw.isKeyPressed(76)) {
                try (Scanner in = new Scanner(new File("savegame.txt"))) {
                    int scoreIn = in.nextInt();
                    int strikesIn = in.nextInt();
                    ArrayList<Scoop> scoopsIn = new ArrayList<>();
                    while (!in.nextLine().isEmpty()) {
                            Scoop scoop = new Scoop(800, 600, palette[(int) (Math.random() * 5)]);
                            scoop.setX(in.nextInt());
                            scoop.setY(in.nextInt());
                            scoopsIn.add(scoop);
                    }
                    Game game = new Game(scoreIn, strikesIn, scoopsIn);
                    game.start();
                }
                catch (FileNotFoundException ex) {
                    StdDraw.text(300, 225, "Saved game not found!");
                    StdDraw.pause(1000);
                }
            }
        }
    }
}
