/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World;

import Math.Vector;
import Sensor.KeySensor;
import View.CoordinateSystem;
import View.Text.Coins;
import View.Text.Life;

/*************
 * Class Player
 * ***********/
public class Player {

    public CoordinateSystem coordinates;
    private Vector position;
    private Vector lookAt;
    private Vector up;
    private Life life;
    private Coins coins;

    /*************
     * Constructors
     * ***********/

    public Player(float xPos, float yPos, float zPos) {
        life = new Life();
        coins = new Coins();
        init(xPos, yPos, zPos);
    }

    public void init(float xPos, float yPos, float zPos) {
        coordinates = new CoordinateSystem(xPos, yPos, zPos);
        position = coordinates.getOrigin();
        lookAt = position.sub(coordinates.getZAxis());
        up = new Vector(0, 1, 0);
    }


    public void update() {
        coordinates = KeySensor.coordinates;
        position = coordinates.getOrigin();
        lookAt = position.sub(coordinates.getZAxis());
        up = coordinates.getYAxis();
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

    public void setScore() { coins.setCoins(); }


    /**
     * displayLife
     */
    public void displayLife() {
        life.display();
    }

    /**
     * reduceLife
     */
    public void reduceLife() {
        life.reduceLife();
    }

    /**
     * displayCoins
     */
    public void displayCoins() {
        coins.display();
    }

    /**
     * getScore
     */
    public int getScore() {
        return coins.counter;
    }

    /**
     * addScore
     */
    public void addScore() {
        coins.addCoin();
    }

    /**
     * reduceScore
     */
    public void reduceScore() { coins.reduceCoin(); }

}