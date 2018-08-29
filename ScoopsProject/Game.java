package ScoopsProject;

import java.awt.*;
import sedgewick.StdDraw;

import java.util.ArrayList;

public class Game {

    private ArrayList<Scoop> scoops;
    private Cone cone;
    private int strikes;
    private Color[] palette = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK};

    Game() {
        scoops = new ArrayList<>();
        cone = new Cone();
        strikes = 0;
    }

    public void start() {
        StdDraw.setCanvasSize(600, 800);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 800);

        redraw();

        new Thread(() -> {
            while(strikes < 3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                scoops.add(new Scoop(800, 600, palette[(int)(Math.random()*5)]));
            }
        }).start();

        while (strikes < 3) {
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
            checkForCollision();
            redraw();
        }
    }

    private void redraw() {
        StdDraw.show(5);
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(600, 800, Integer.toString(strikes));
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.filledRectangle(cone.getXPosition(),
                -17,
                cone.getBaseWidth() * 2,
                cone.getBaseHeight() * 2);
        for (Scoop s : scoops) {
            StdDraw.setPenColor(s.getColor());
            StdDraw.filledCircle(s.getX(), s.getY(), s.getRadius());
            s.moveScoop(4);
        }
        StdDraw.show();
    }

    private void checkForCollision() {

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
