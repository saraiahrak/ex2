/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World;
import Sensor.KeySensor;
import View.*;
import Math.*;

/*************
 * Class Player
 * ***********/
public class Player {

    public CoordinateSystem coordinates;
    private Vector position;
    private Vector lookAt;
    private Vector up;

    /*************
     * Constructors
     * ***********/

    public Player(float xPos, float yPos, float zPos) {
        init(xPos, yPos, zPos);
    }

    private void init(float xPos, float yPos, float zPos) {
        coordinates = new CoordinateSystem(xPos, yPos, zPos);
        position = coordinates.getOrigin();
        lookAt = coordinates.getzAxis().sub(position);
        lookAt.normalize();
        up = new Vector(0, 1, 0);
    }


    public void update() {
        coordinates = KeySensor.coordinates;
        position = coordinates.getOrigin();
        lookAt = coordinates.getzAxis().add(position);
        lookAt.normalize();
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
}