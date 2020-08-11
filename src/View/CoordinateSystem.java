/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View;

import Math.Vector;
import World.CollisionDetection.Collidable;
import World.CollisionDetection.CollisionFactory;
import World.CollisionDetection.CollisionHandler;
import World.Space.World;

/*****************
 * Class CoordinateSystem
 * ***************/
public class CoordinateSystem {
    private Vector xAxis;
    private Vector yAxis;
    private Vector zAxis;
    private Vector origin;
    private float angleX;
    private float angleY;
    private float angleZ;
    private boolean traced;

    /**
     * constructor
     */
    public CoordinateSystem(float xPos, float yPos, float zPos) {
        init(xPos, yPos, zPos);
        traced = false;
    }

    /**
     * init
     * Call initializer
     */
    public void init(float xPos, float yPos, float zPos) {
        initAxes();
        initOrigin(xPos, yPos, zPos);
        angleX = 0;
        angleY = 0;
        angleZ = 0;
    }

    /**
     * initAxes
     * initialize axes vectors
     */
    public void initAxes() {
        // X-Axis
        xAxis = new Vector(1.0f, 0, 0);
        // Y-Axis
        yAxis = new Vector(0, 1.0f, 0);
        // Z-Axis
        zAxis = new Vector(0, 0, 1.0f);
    }


    /**
     * initOrigin
     * initialize origin vector
     */
    public void initOrigin(float xPos, float yPos, float zPos) {
        origin = new Vector(xPos, yPos, zPos);
    }

    /********
     * Getters
     * ******/

    public Vector getxAxis() {
        return xAxis;
    }

    public Vector getyAxis() {
        return yAxis;
    }

    public Vector getzAxis() {
        return zAxis;
    }

    public Vector getOrigin() {
        return origin;
    }

    public float getAngleX() { return angleX; }

    public float getAngleY() { return angleY; }

    public float getAngleZ() { return angleZ; }

    public boolean getTraced() { return traced; }

    /********
     * Setters
     * ******/

    public void setAngleXFromPlayer(float x) {
        this.angleX += 150 * x;
    }

    public void setAngleYFromPlayer(float y) {
        this.angleY += 50 * y;
    }

    public void setAngleZFromPlayer(float z) {
        this.angleZ += 150 * z;
    }

    public void setTraced() {
        traced = true;
    }

    /**
     * rotate
     * Changing the look
     *
     * @param axis - x, y, or z axis
     * @param angle - rotate angle
     */
    public void rotate(char axis, double angle) {
        Vector xNew, yNew, zNew;
        // look up or down
        if (axis == 'X') {
            setAngleXFromPlayer((float)angle);
            yNew = zAxis.multByScalar((float) Math.sin(angle)).add(yAxis.multByScalar((float) Math.cos(angle)));
            zNew = zAxis.multByScalar((float) Math.cos(angle)).sub(yAxis.multByScalar((float) Math.sin(angle)));
            yAxis = yNew.normalize();
            zAxis = zNew.normalize();
        }
        if (axis == 'Y') {
            setAngleYFromPlayer((float)angle);
            zNew = xAxis.multByScalar((float) Math.sin(angle)).add(zAxis.multByScalar((float) Math.cos(angle)));
            xNew = xAxis.multByScalar((float) Math.cos(angle)).sub(zAxis.multByScalar((float) Math.sin(angle)));
            zAxis = zNew.normalize();
            xAxis = xNew.normalize();
            // divert the look to right or left
        }
        if (axis == 'Z') {
            setAngleZFromPlayer((float)angle);
            xNew = xAxis.multByScalar((float) Math.cos(angle)).sub(yAxis.multByScalar((float) Math.sin(angle)));
            yNew = xAxis.multByScalar((float) Math.sin(angle)).add(yAxis.multByScalar((float) Math.cos(angle)));
            xAxis = xNew.normalize();
            yAxis = yNew.normalize();
        }
    }

    /**
     * move
     * Multiply the step with the correct axis and add it to the origin
     *
     * @param axis - x, y or z axis
     * @param step - size of movement
     */
    public void move(char axis, float step) {
        Vector next = null;

        if (axis == 'X') {
            next = origin.add(xAxis.multByScalar(step));
        }
        if (axis == 'Y') {
            next = origin.add(yAxis.multByScalar(step));
        }
        if (axis == 'Z') {
            next = origin.add(zAxis.multByScalar(step));
        }

        CollisionHandler handler = CollisionFactory.create();

        for (Collidable c : World.collidables) {
            boolean intersection = handler.handle(c, next);
            if (intersection) {
                return;
            }
        }

        origin = next;
    }

}