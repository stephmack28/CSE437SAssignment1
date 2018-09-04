package ScoopsProject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;
import java.util.*;


public class ScoopsGameTest {

    @Test
    public void testInitialStateG() {
        Game g = new Game();
        assertEquals(0, g.scoops.size());
        assertEquals(0, g.score);
        assertEquals(0, g.strikes);
        assertEquals(0, g.cone.getWidthLevel());
    }

    @Test
    public void testInitialStateG2() {
        Game g2 = new Game(20, 2, new ArrayList<Scoop>());
        assertEquals(0, g2.scoops.size());
        assertEquals(20, g2.score);
        assertEquals(2, g2.strikes);
    }

    @Test
    public void testCollision() {
        ArrayList<Scoop> scoops = new ArrayList<Scoop>();
        scoops.add(new Scoop(800, 600, Color.red, 0));
        Game g3 = new Game(0, 0, scoops);
        assertEquals(false, scoops.get(0).getScored());
        while (scoops.get(0).getY() > (-17 + (g3.cone.getBaseHeight() * 2))) {
            scoops.get(0).moveScoopDown(4);
        }
        g3.checkForCollision();
        if (g3.cone.getXPosition()==scoops.get(0).getX()) {
            assertEquals(true, scoops.get(0).getScored());
            assertEquals(10, g3.score);
        } else {
            scoops.get(0).moveScoopDown(4);
            assertEquals(1, g3.strikes);
        }
    }


}