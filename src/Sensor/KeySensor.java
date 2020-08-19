/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Sensor;

import View.CoordinateSystem;
import World.CollisionDetection.CollisionDetection;
import World.Space.World;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*****************
 * Class KeySensor
 * ***************/
public class KeySensor implements KeyListener {

    public static CoordinateSystem coordinates;
    private boolean purchased;
    private float angleAmount;

    /*****************
     * Constructor
     * ***************/
    public KeySensor(CoordinateSystem myCoordinates) {
        coordinates = myCoordinates;
        purchased = false;
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

        if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
            World.player.reduceScore();
            World.showMessage = false;
            coordinates.init(42f, 10f, 67f);
            coordinates.onFly = true;
        }

        if ((e.getKeyChar() == 'i' || e.getKeyChar() == 'I') && coordinates.onFly) {
            if (angleAmount + angle <= 1) {
                angleAmount += angle;
                coordinates.rotate('X', angle);
            }
        } else if ((e.getKeyChar() == 'k' || e.getKeyChar() == 'K') && coordinates.onFly) {
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
        } else if ((e.getKeyChar() == 'e' || e.getKeyChar() == 'E') && coordinates.onFly) {
            coordinates.move('Y', step);
        } else if ((e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') && coordinates.onFly) {
            coordinates.move('Y', -step);
        }

        if (e.getKeyCode() == KeyEvent.VK_F2) {
//            coordinates.init(32f, 3.5f, 86f);
            World.firstLevel = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }

        // checks if the player can buy the carpet
        if (World.secondLevel && !coordinates.onFly) {
            carpetWasFound();
        }
    }

    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }


    /**
     * carpetWasFound
     * If the carpet was found fly, otherwise continue to search
     */
    private void carpetWasFound() {
        if (buy() && !purchased) {
            purchased = true;
            World.showMessage = true;
        }
    }


    /**
     * buy
     * Checks if the player gets to the carpet and can buy it
     *
     * @return true if the player sees the carpet, otherwise false
     */
    public boolean buy() {
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