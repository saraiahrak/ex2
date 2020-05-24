package Sensor;
import View.CoordinateSystem;
import world.Space.World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import static java.lang.System.exit;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


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
        float step = 0.5f;
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
}