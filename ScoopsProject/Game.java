package ScoopsProject;

import java.awt.*;
import sedgewick.StdDraw;

import java.util.ArrayList;

public class Game {

    private ArrayList<Scoop> scoops;
    private Cone cone;
    private int strikes;
    private int score;
    private Color[] palette = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK};
    private long time;
    private long endTime;
    private long delta = 1000;
    private boolean paused = false;

    Game() {
        scoops = new ArrayList<>();
        cone = new Cone();
        strikes = 0;
        score = 0;
        time = System.currentTimeMillis();
        endTime = time;
    }

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
                StdDraw.pause(200);
                paused = true;
                long delayTime = endTime - time;
                while (paused) {
                    if (StdDraw.isKeyPressed(27)) {
                        StdDraw.pause(200);
                        paused = false;
                        endTime = System.currentTimeMillis() + delayTime;
                    }
                }
            }
            checkForCollision();
            redraw();
        }

        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(300, 400, "GAME OVER");
        StdDraw.pause(3000);
    }

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
            StdDraw.setPenColor(s.getColor());
            StdDraw.filledCircle(s.getX(), s.getY(), s.getRadius());
        }
        StdDraw.show();
    }

    private void checkForCollision() {
        for (Scoop s : scoops) {
            s.moveScoop(4);
            if (s.isScoopOnGround()) {
                if (!s.getScored()) {
                    strikes++;
                }
                s.setScored(true);
                //scoops.remove(s);
                // May have unintended effects (see ArrayList ordering)
                // If so, use a listIterator instead
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
            StdDraw.show();
            if (StdDraw.isKeyPressed(32)) {
                Game game = new Game();
                game.start();
            }
        }
    }
}
