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

import Math.*;

/*****************
 * Class KeySensor
 * ***************/
public class KeySensor implements KeyListener {

    public static CoordinateSystem coordinates;
    private Vector lastPosition;
    private Vector lastXAxis;
    private Vector lastYAxis;
    private Vector lastZAxis;
    private boolean canBuy;
    private boolean purchased;
    private float angleAmount;
    public boolean isKeyPressed = false;


    /*****************
     * Constructor
     * ***************/
    public KeySensor(CoordinateSystem myCoordinates) {
        coordinates = myCoordinates;
        lastPosition = null;
        canBuy = false;
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
        if (coordinates.inPalace) {
            step += 0.2;
        }

        if (isMotionKey(e.getKeyChar())) {
            isKeyPressed = true;
        }

        if ((e.getKeyChar() == 'b' || e.getKeyChar() == 'B') && canBuy && !purchased) {
            if (World.player.getScore() < 200) {
                World.showMenu = true;
                World.showGameOver = true;
            } else {
                purchased = true;
                World.player.reduceScore();
                coordinates.init(42f, 10f, 67f);
                coordinates.onFly = true;
            }
            World.showMessage = false;
        }

        if (!World.showMenu) {
            if ((e.getKeyChar() == 'i' || e.getKeyChar() == 'I') && coordinates.onFly) {
                if (angleAmount + angle <= 0.5) {
                    angleAmount += angle;
                    coordinates.rotate('X', angle);
                }
            } else if ((e.getKeyChar() == 'k' || e.getKeyChar() == 'K') && coordinates.onFly) {
                if (angleAmount - angle >= -0.2) {
                    angleAmount -= angle;
                    coordinates.rotate('X', -angle);
                }
            } else if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
                coordinates.rotate('Y', -angle);
            } else if (e.getKeyChar() == 'j' || e.getKeyChar() == 'J') {
                coordinates.rotate('Y', angle);
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
        }

        if ((e.getKeyCode() == KeyEvent.VK_F2) && World.firstLevel && !World.showMenu) {
            World.showMenu = true;
            World.showLevel2Menu = true;
            World.player.setScore();
        }

        if ((e.getKeyCode() == KeyEvent.VK_F1) && !World.showSuccess && !World.showGameOver) {
            lastPosition = coordinates.getOrigin();
            lastXAxis = coordinates.getXAxis();
            lastYAxis = coordinates.getYAxis();
            lastZAxis = coordinates.getZAxis();
            World.showMenu = true;
            World.showInstructions = true;
            coordinates.onFly = false;
        }

        if ((e.getKeyChar() == KeyEvent.VK_ENTER)
                && World.showMainMenu && !World.showInstructions) {
            coordinates.init(5, 0.2f, 180);
            World.showMenu = false;
            World.showMainMenu = false;
        }

        if ((e.getKeyChar() == 'r' || e.getKeyChar() == 'R') && World.showInstructions) {
            if (purchased) {
                if (!coordinates.inPalace) {
                    coordinates.onFly = true;
                }
            }
            backTo();
        }

        // checks if the player can buy the carpet
        if (World.secondLevel && !coordinates.onFly) {
            carpetWasFound();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }
    }


    private boolean isMotionKey(char key) {
        return (key == 'w' || key == 'W' || key == 'a' || key == 'A' || key == 's' || key == 'S' | key == 'd'
                || key == 'D' || key == 'q' || key == 'Q' || key == 'e' || key == 'E');
    }

    public void keyReleased(KeyEvent arg0) {

        if (isMotionKey(arg0.getKeyChar())) {
            isKeyPressed = false;
        }
        // TODO Auto-generated method stub
    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
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


    /**
     * backTo
     * Finds the last menu type or the last player position
     */
    private void backTo() {
        if (!World.showMainMenu) {
            World.showMenu = false;
        }
        World.showInstructions = false;
        coordinates.init(lastPosition.getX(), lastPosition.getY(), lastPosition.getZ());
        coordinates.setAxes(lastXAxis, lastYAxis, lastZAxis);
    }


    /**
     * carpetWasFound
     * If the carpet was found fly, otherwise continue to search
     */
    private void carpetWasFound() {
        if (buy() && !canBuy) {
            canBuy = true;
            World.showMessage = true;
        }
    }

}