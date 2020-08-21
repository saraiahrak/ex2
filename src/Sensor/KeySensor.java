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
    private boolean canBuy;
    private boolean purchased;
    private float angleAmount;
    public static boolean isPressed = false;


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
        char key = e.getKeyChar();

        if (isMotionKey(key)) {
            isPressed = true;
            World.player.isInMotion = true;
        }

<<<<<<< HEAD
//
//        if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
//            World.showMenu = true;
//        }
//        if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
//            World.showMenu = false;
//        }

        if ((key == 'b' || key == 'B') && canBuy && !purchased) {
=======
        if ((e.getKeyChar() == 'b' || e.getKeyChar() == 'B') && canBuy && !purchased) {
>>>>>>> 519a1c4741f99456d95ce3851b897124e1fbde70
            purchased = true;
            World.player.reduceScore();
            World.showMessage = false;
            coordinates.init(42f, 10f, 67f);
            coordinates.onFly = true;
        }

<<<<<<< HEAD
        if ((key == 'i' || key == 'I') && coordinates.onFly) {
            if (angleAmount + angle <= 0.5) {
                angleAmount += angle;
                coordinates.rotate('X', angle);
            }
        } else if ((key == 'k' || key == 'K') && coordinates.onFly) {
            if (angleAmount - angle >= -0.2) {
                angleAmount -= angle;
                coordinates.rotate('X', -angle);
            }
        } else if (key == 'l' || key == 'L') {
            coordinates.rotate('Y', -angle);
        } else if (key == 'j' || key == 'J') {
            coordinates.rotate('Y', angle);
//        } else if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {
//            coordinates.rotate('Z', angle);
//        } else if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
//            coordinates.rotate('Z', -angle);
        } else if (key == 'w' || key == 'W') {
            coordinates.move('Z', -step);
        } else if (key == 's' || key == 'S') {
            coordinates.move('Z', step);
        } else if (key == 'd' || key == 'D') {
            coordinates.move('X', step);
        } else if (key == 'a' || key == 'A') {
            coordinates.move('X', -step);
        } else if ((key == 'e' || key == 'E') && coordinates.onFly) {
            coordinates.move('Y', step);
        } else if ((key == 'q' || key == 'Q') && coordinates.onFly) {
            coordinates.move('Y', -step);
        }


        if (key == KeyEvent.VK_F2) {
//            coordinates.init(32f, 3.5f, 86f);
            World.firstLevel = false;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            World.exit();
=======
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

        if ((e.getKeyCode() == KeyEvent.VK_F2) && World.firstLevel && !World.showMainMenu) {
            World.showMenu = true;
            World.showLevel2Menu = true;
            World.player.setScore();
>>>>>>> 519a1c4741f99456d95ce3851b897124e1fbde70
        }

<<<<<<< HEAD
        if (key == KeyEvent.VK_F1) {
            if (!World.showMenu) {
                World.showMenu = true;
            } else {
                if (World.showInstructions) {
                    World.showInstructions = false;
                }
            }
        }

        if (key == KeyEvent.VK_ENTER) {
            if (World.showMenu && !World.showInstructions) {
                World.showMenu = false;
            }
        }

        if (key == 'h' || key == 'H') {
            if (World.showMenu) {
                World.showInstructions = true;
            }
=======
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            lastPosition = coordinates.getOrigin();
            World.showMenu = true;
            World.showInstructions = true;
        }

        if ((e.getKeyChar() == KeyEvent.VK_ENTER)
                && World.showMainMenu && !World.showInstructions) {
            coordinates.init(5, 0.2f, 180);
            World.showMenu = false;
            World.showMainMenu = false;
        }

        if ((e.getKeyChar() == 'r' || e.getKeyChar() == 'R')
                && World.showMenu && !World.showLevel2Menu) {
            backTo();
>>>>>>> 519a1c4741f99456d95ce3851b897124e1fbde70
        }

        // checks if the player can buy the carpet
        if (World.secondLevel && !coordinates.onFly) {
            carpetWasFound();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            World.exit();
        }
    }

<<<<<<< HEAD
    private boolean isMotionKey(char key) {

        return (key == 'i' || key == 'I' || key == 'k' || key == 'K' || key == 'l' || key == 'L' || key == 'w'
        || key == 'W' || key == 's' || key == 'S' || key == 'd' || key == 'D' || key == 'a' || key == 'A'
        || key == 'e' || key == 'E' || key == 'q' || key == 'Q');
    }
=======
>>>>>>> 519a1c4741f99456d95ce3851b897124e1fbde70

    public void keyReleased(KeyEvent arg0) {
        if(isMotionKey(arg0.getKeyChar())) {
            isPressed = false;
            World.player.isInMotion = true;
        }
        System.out.println(isPressed);
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

        return xFlag && zFlag;
    }


    /**
     * backTo
     * Finds the last menu type or the last player position
     */
    private void backTo() {
        if (!World.showMainMenu) {
            World.showMenu = false;
        }
        if (World.showSuccess || World.showGameOver) {
            World.showMainMenu = true;
        }
        World.showInstructions = false;
        World.showGameOver = false;
        World.showSuccess = false;
        coordinates.init(lastPosition.getX(), lastPosition.getY(), lastPosition.getZ());
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