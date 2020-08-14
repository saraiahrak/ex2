/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World;

import Math.Vector;
import Sensor.KeySensor;
import View.CoordinateSystem;
import View.Life;
import World.Space.World;

/*************
 * Class Player
 * ***********/
public class Player {

    public CoordinateSystem coordinates;
    private Vector position;
    private Vector lookAt;
    private Vector up;
    private Life life;

    /*************
     * Constructors
     * ***********/

    public Player(float xPos, float yPos, float zPos) {
        life = new Life();
        init(xPos, yPos, zPos);
    }

    private void init(float xPos, float yPos, float zPos) {
        coordinates = new CoordinateSystem(xPos, yPos, zPos);
        position = coordinates.getOrigin();
        lookAt = position.sub(coordinates.getzAxis());
        up = new Vector(0, 1, 0);
    }


    public void update() {
        coordinates = KeySensor.coordinates;
        position = coordinates.getOrigin();
        lookAt = position.sub(coordinates.getzAxis());
        up = coordinates.getyAxis();
    }

    /*************
     * Getters
     * ***********/

    public Vector getLookAt() {
        return lookAt;
    }

    public CoordinateSystem getCoordinates() {
        return coordinates;
    }

    public Vector getUp() {
        return up;
    }

    public Vector getPosition() {
        return position;
    }

    /*************
     * Setters
     * ***********/

    public void setUp(Vector up) {
        this.up = up;
    }

    public void updateLife() {
        life.display();
    }
}