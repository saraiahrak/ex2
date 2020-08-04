/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Sensor;
import World.CollisionDetection.CollisionObject;
import World.CollisionDetection.CollisionDetection;
import World.Space.World;
import View.CoordinateSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/*****************
 * Class KeySensor
 * ***************/
public class KeySensor implements KeyListener {

    public static CoordinateSystem coordinates;

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
        float step = 0.35f;

        World.wasCollision = false;

        if (wasCollision()) {
            World.wasCollision = true;
            // if was collision stay in last position
            step = -step;
        }

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
            coordinates.move('Z', -step);
        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            coordinates.move('Z', step);
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            coordinates.move('X', step);
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            coordinates.move('X', -step);
        } else if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            coordinates.move('Y', step);
        } else if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            coordinates.move('Y', -step);
        }

        if (e.getKeyCode() == KeyEvent.VK_F1) {
            //coordinates.init(50f, 34f, 5f);
            coordinates.init(80f, 3f, 80f);
            World.firstLevel = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }
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


    /**
     * wasCollision
     * Checks if was Collision between the player and list of collision objects
     *
     * @return true if was collision, otherwise false
     */
    public boolean wasCollision() {
        for (CollisionObject c : World.collisionObjects) {
            if (CollisionDetection.colDetect(c, coordinates.getOrigin())) {
                if (c.getTextureKey().equals("caveEntry")) {
                    return CollisionDetection.checkBoundaries(coordinates.getOrigin());
                }
                return true;
            }
        }
        return false;
    }
}