/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Sensor;
import World.CollisionDetection.CollisionObject;
import World.CollisionDetection.CollisionDetection;
import World.Models.Model;
import World.Models.ObjData;
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
    private boolean onFly;

    /*****************
     * Constructor
     * ***************/
    public KeySensor(CoordinateSystem myCoordinates) {
        coordinates = myCoordinates;
        onFly = false;
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
            coordinates.init(32f, 3.5f, 86f);
            World.firstLevel = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }
        // checks if the carpet was found
        World.secondLevel = true;
        if (World.secondLevel && !onFly) {
            if(fly()) {
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
     * wasCollision
     * Checks if was Collision between the player and list of collision objects
     *
     * @return true if was collision, otherwise false
     */
    public boolean wasCollision() {
        for (CollisionObject c : World.collisionObjects) {
            if (CollisionDetection.colDetect(c, coordinates.getOrigin())) {
                if (c.getTextureKey().equals("caveEntry")) {
                    return !CollisionDetection.checkBoundaries
                            ("x", coordinates.getOrigin(), 0, 9);

                }
                if (c.getTextureKey().equals("palace")) {
                    if (CollisionDetection.checkBoundaries
                            ("x", coordinates.getOrigin(), 47, 52)) {
                        if (CollisionDetection.checkBoundaries
                                ("y", coordinates.getOrigin(), 34.5f, 40)) {
                            Model.wasUsed = true;
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }


    /**
     * fly
     * Checks if was Collision between the player and the carpet
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