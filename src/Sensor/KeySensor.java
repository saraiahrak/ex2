/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Sensor;

import View.CoordinateSystem;
import World.CollisionDetection.Collidable;
import World.CollisionDetection.CollisionDetection;
import World.Space.World;
import Math.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*****************
 * Class KeySensor
 * ***************/
public class KeySensor implements KeyListener {

    public static CoordinateSystem coordinates;
    private boolean onFly;
    private float angleAmount;

    /*****************
     * Constructor
     * ***************/
    public KeySensor(CoordinateSystem myCoordinates) {
        coordinates = myCoordinates;
        onFly = false;
        angleAmount = 0;
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

//
//        if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
//            World.showMenu = true;
//        }
//        if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
//            World.showMenu = false;
//        }

        if ((e.getKeyChar() == 'i' || e.getKeyChar() == 'I') && onFly) {
            World.playerDisqualified = true;
            if (angleAmount + angle <= 1) {
                angleAmount += angle;
                coordinates.rotate('X', angle);
            }
        } else if ((e.getKeyChar() == 'k' || e.getKeyChar() == 'K') && onFly) {
            if (angleAmount - angle >= -1) {
                angleAmount -= angle;
                coordinates.rotate('X', -angle);
            }
        } else if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
            coordinates.rotate('Y', -angle);
        } else if (e.getKeyChar() == 'j' || e.getKeyChar() == 'J') {
            coordinates.rotate('Y', angle);
//        } else if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {
//            coordinates.rotate('Z', angle);
//        } else if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
//            coordinates.rotate('Z', -angle);
        } else if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            coordinates.move('Z', -step);
        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            coordinates.move('Z', step);
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            coordinates.move('X', step);
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            coordinates.move('X', -step);
        } else if ((e.getKeyChar() == 'e' || e.getKeyChar() == 'E') && onFly) {
            coordinates.move('Y', step);
        } else if ((e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') && onFly) {
            coordinates.move('Y', -step);
        }

        if (e.getKeyCode() == KeyEvent.VK_F2) {
            //coordinates.init(32f, 35f, 5f);
            coordinates.init(32f, 3.5f, 86f);
            World.firstLevel = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }
        // checks if the carpet was found
        if (World.secondLevel && !onFly) {
            if (fly()) {
                coordinates.init(42f, 10f, 67f);
                coordinates.setTraced();
                onFly = true;
            }
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
     * fly
     * Checks if was Collision between the player and the carpet
     * Models collision!!!
     *
     * @return true if was collision, otherwise false
     */
    public boolean fly() {
        boolean xFlag = CollisionDetection.checkBoundaries
                ("x", coordinates.getOrigin(), 35, 48);
        boolean zFlag = CollisionDetection.checkBoundaries
                ("z", coordinates.getOrigin(), 50, 70);
        if (xFlag && zFlag) {
            return true;
        }
        return false;
    }
}