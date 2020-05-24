package world;
import Sensor.KeySensor;
import View.*;
import Math.*;

public class Player {
    public CoordinateSystem coordinates;
    private Vector position;
    private Vector lookAt;
    private Vector up;


    public Player() {
        init();
    }

    private void init() {
        coordinates = new CoordinateSystem();
        position = new Vector(0f, 0f, 0f);
        lookAt = coordinates.getzAxis().sub(position);
        //lookAt = new Vector(0.0f, 0.0f, 1f);
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

    public void setCoordinates(CoordinateSystem coordinates) {
        this.coordinates = coordinates;
    }

    public void setLookAt(Vector lookAt) {
        this.lookAt = lookAt;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setUp(Vector up) {
        this.up = up;
    }
}