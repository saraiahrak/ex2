/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View;

import Math.Vector;
import World.CollisionDetection.Collidable;
import World.CollisionDetection.CollisionDetection;
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
    public boolean onFly;
    public boolean inPalace;


    /**
     * constructor
     */
    public CoordinateSystem(float xPos, float yPos, float zPos) {
        init(xPos, yPos, zPos);
        onFly = false;
        inPalace = false;
    }


    /**
     * init
     * Call initializer
     */
    public void init(float xPos, float yPos, float zPos) {
        initOrigin(xPos, yPos, zPos);
        initAxes();
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

    public Vector getXAxis() {
        return xAxis;
    }

    public Vector getYAxis() {
        return yAxis;
    }

    public Vector getZAxis() {
        return zAxis;
    }

    public Vector getOrigin() {
        return origin;
    }

    public float getAngleX() {
        return angleX;
    }

    public float getAngleY() {
        return angleY;
    }

    public float getAngleZ() {
        return angleZ;
    }


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

    public void setAxes(Vector x, Vector y, Vector z) {
        // X-Axis
        xAxis = x;
        // Y-Axis
        yAxis = y;
        // Z-Axis
        zAxis = z;
    }

    /**
     * rotate
     * Changing the look
     *
     * @param axis  - x, y, or z axis
     * @param angle - rotate angle
     */
    public void rotate(char axis, double angle) {
        Vector xNew, yNew, zNew;
        // look up or down
        if (axis == 'X') {
            setAngleXFromPlayer((float) angle);
            yNew = zAxis.multByScalar((float) Math.sin(angle)).add(yAxis.multByScalar((float) Math.cos(angle)));
            zNew = zAxis.multByScalar((float) Math.cos(angle)).sub(yAxis.multByScalar((float) Math.sin(angle)));
            yAxis = yNew.normalize();
            zAxis = zNew.normalize();
        }
        if (axis == 'Y') {
            setAngleYFromPlayer((float) angle);
            zNew = xAxis.multByScalar((float) Math.sin(angle)).add(zAxis.multByScalar((float) Math.cos(angle)));
            xNew = xAxis.multByScalar((float) Math.cos(angle)).sub(zAxis.multByScalar((float) Math.sin(angle)));
            zAxis = zNew.normalize();
            xAxis = xNew.normalize();
            // divert the look to right or left
        }
        if (axis == 'Z') {
            setAngleZFromPlayer((float) angle);
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

        if (World.firstLevel) {
            // check
            firstLevelBoundaries(next);
        }

        if (World.secondLevel) {
            // check
            if (secondLevelBoundaries(next) == 0) {
                return;
            }

            if (jasmineWasFound(next)) {
                World.showMenu = true;
                World.showSuccess = true;
                return;
            }
        }

        for (Collidable c : World.collidables) {
            CollisionHandler handler = CollisionFactory.create(c, next);
            boolean intersection = handler.handle(c, next);
            if (intersection) {
                return;
            }
        }

        // move to the next step
        origin = next;
    }


    /**
     * jasmineWasFound
     * Checks if the player can rescue jasmine
     *
     * @param next - next step
     */
    private boolean jasmineWasFound(Vector next) {
        for (Collidable c : World.jasmineCollidables) {
            CollisionHandler handler = CollisionFactory.create(c, next);
            boolean intersection = handler.handle(c, next);
            if (intersection) {
                return true;
            }
        }
        return false;
    }


    /**
     * firstLevelBoundaries
     *
     * @param next - next step
     */
    private void firstLevelBoundaries(Vector next) {
        if (!CollisionDetection.checkBoundaries("z", next, -113.02f, 190)) {
            World.showMenu = true;
            World.showLevel2Menu = true;
        }
    }


    /**
     * secondLevelBoundaries
     *
     * @param next - next step
     */
    private int secondLevelBoundaries(Vector next) {
        if (!CollisionDetection.checkBoundaries("y", next, 3f, 78)) {
            return 0;
        }

        // if the player enters the palace
        if ((next.getZ() <= 0) && onFly && !inPalace) {
            origin.setVector(50, 40, -5);
            initAxes();
            onFly = false;
            inPalace = true;
            return 0;
        }

        // if the player leaves the palace
        if ((next.getZ() > 0) && !onFly && inPalace) {
            onFly = true;
            inPalace = false;
        }
        return 1;
    }

}