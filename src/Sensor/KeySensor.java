/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Sensor;
import View.CoordinateSystem;
import world.CollisionDetection;
import world.CollisionObject;
import world.Space.World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/*****************
 * Class KeySensor
 * ***************/
public class KeySensor implements KeyListener {

    public static CoordinateSystem coordinates;
    public static boolean frontCollision = false;
    public static boolean backCollision = false;
    public static boolean rightCollision = false;
    public static boolean leftCollision = false;

    /*****************
     * Constructor
     * ***************/
    public KeySensor(CoordinateSystem myCoordinates) {
        coordinates = myCoordinates;
    }

    /*********
     * Events
     * ********/


    /**
     * keyPressed
     * Key pressed event
     *
     * @param e KeyEvent
     */
    public void keyPressed(KeyEvent e) {
        double angle = 0.1;
        float step = 0.3f;
        World.wasCollision = false;

        if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
            coordinates.rotate('X', angle);
        } else if (e.getKeyChar() == 'k' || e.getKeyChar() == 'K') {
            coordinates.rotate('X', -angle);
        } else if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
            coordinates.rotate('Y', -angle);
        } else if (e.getKeyChar() == 'j' || e.getKeyChar() == 'J') {
            coordinates.rotate('Y', angle);
        } else if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {
            coordinates.rotate('Z', angle);
        } else if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
            coordinates.rotate('Z', -angle);
        } else if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            frontCollision = wasCollision();
            if (frontCollision) {
                World.wasCollision = true;
                coordinates.move('Z', step);
                frontCollision = false;
                return;
            }
            coordinates.move('Z', -step);
        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            backCollision = wasCollision();
            if (backCollision) {
                World.wasCollision = true;
                coordinates.move('Z', -step);
                backCollision = false;
                return;
            }
            coordinates.move('Z', step);
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            rightCollision = wasCollision();
            if (rightCollision) {
                World.wasCollision = true;
                coordinates.move('X', -step);
                rightCollision = false;
                return;
            }
            coordinates.move('X', step);
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            leftCollision = wasCollision();
            if (leftCollision) {
                World.wasCollision = true;
                coordinates.move('X', step);
                leftCollision = false;
                return;
            }
            coordinates.move('X', -step);
        } else if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            coordinates.move('Y', step);
        } else if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            coordinates.move('Y', -step);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }
    }

    public boolean wasCollision() {
        for (CollisionObject c : World.collisionObjects) {
            if (CollisionDetection.colDetect(c, coordinates.getOrigin())) {
                return true;
            }
        }
        return false;
    }


    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
}