/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View;
import Math.*;

/*****************
 * Class CoordinateSystem
 * ***************/
public class CoordinateSystem {
    private Vector xAxis;
    private Vector yAxis;
    private Vector zAxis;
    private Vector origin;

    /**
     * constructor
     */
    public CoordinateSystem() {
        init();
    }

    /**
     * init
     * Call initializer
     */
    private void init() {
        initAxes();
        initOrigin();
    }

    /**
     * initAxes
     * initialize axes vectors
     */
    private void initAxes() {
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
    private void initOrigin() {
        origin = new Vector(0f, 0f, -1f);
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
            yNew = zAxis.multByScalar((float) Math.sin(angle)).add(yAxis.multByScalar((float) Math.cos(angle)));
            yAxis = yNew.normalize();
            zNew = zAxis.multByScalar((float) Math.cos(angle)).sub(yAxis.multByScalar((float) Math.sin(angle)));
            zAxis = zNew.normalize();
        }
        if (axis == 'Y') {
            zNew = xAxis.multByScalar((float) Math.sin(angle)).add(zAxis.multByScalar((float) Math.cos(angle)));
            zAxis = zNew.normalize();
            xNew = xAxis.multByScalar((float) Math.cos(angle)).sub(zAxis.multByScalar((float) Math.sin(angle)));
            xAxis = xNew.normalize();
            // divert the look to right or left
        }
        if (axis == 'Z') {
            xNew = xAxis.multByScalar((float) Math.cos(angle)).sub(yAxis.multByScalar((float) Math.sin(angle)));
            xAxis = xNew.normalize();
            yNew = xAxis.multByScalar((float) Math.sin(angle)).add(yAxis.multByScalar((float) Math.cos(angle)));
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
        if (axis == 'X') {
            origin = origin.add(xAxis.multByScalar(step));
        }
        if (axis == 'Y') {
            origin = origin.add(yAxis.multByScalar(step));
        }
        if (axis == 'Z') {
            origin = origin.add(zAxis.multByScalar(step));
        }
    }

}
