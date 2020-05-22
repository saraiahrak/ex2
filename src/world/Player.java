package world;

import View.*;
import Math.*;

public class Player {
    private CoordinateSystem coordinates;
    private Vector position;
    private Vector lookAt;
    private Vector up;


    public Player() {
        init();
    }

    private void init() {
        coordinates = new CoordinateSystem();
        position = new Vector(0.5, 0, 0.5);
        lookAt = new Vector(-0.5, 0.5, 1).normalize();
        up = new Vector(0, 1, 0);
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


