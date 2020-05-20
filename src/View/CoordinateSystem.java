package View;
import Math.*;

public class CoordinateSystem {
    public static Vector xAxis;
    public static Vector yAxis;
    public static Vector zAxis;
    public static Vector origin;

    /**
     * constructor
     */
    public CoordinateSystem() {
        initAxes();
        initOrigin();
    }


    /**
     * initAxes
     * initialize axes vectors
     */
    private void initAxes() {
        // X-Axis
        //Vector x = View.up.cross(zAxis);
        //xAxis = x.scalar(1 / x.getSize());
        xAxis =  new Vector(1, 0, 0);
        // Y-Axis
        //yAxis = zAxis.cross(xAxis);
        yAxis = new Vector(0, 1, 0);
        // Z-Axis
        //zAxis = z.scalar(1 / z.getSize());
        zAxis =  new Vector(0, 0, 1);
    }


    /**
     * initOrigin
     * initialize origin vector
     */
    private void initOrigin() {
        origin = View.position.clone();
    }


    /**
     * rotate
     * Changing the look
     */
    public void rotate(char axis, double angle) {
        Vector xN, yN, zN;
        // look up or down
        if (axis == 'X') {
            yN = zAxis.multByScalar(Math.sin(angle)).add(yAxis.multByScalar(Math.cos(angle)));
            yAxis = yN.normalize();
            zN = zAxis.multByScalar(Math.cos(angle)).add(yAxis.multByScalar(Math.sin(angle)));
            zAxis = zN.normalize();
        // look left or right
        } if (axis == 'Y') {
            xN = xAxis.multByScalar(Math.cos(angle)).add(zAxis.multByScalar(Math.sin(angle)));
            xAxis = xN.normalize();
            zN = xAxis.multByScalar(Math.sin(angle)).add(zAxis.multByScalar(Math.cos(angle)));
            zAxis = zN.normalize();
        // divert the look to right or left
        } if (axis == 'Z') {
            xN = xAxis.multByScalar(Math.cos(angle)).add(yAxis.multByScalar(Math.sin(angle)));
            xAxis = xN.normalize();
            yN = xAxis.multByScalar(Math.sin(angle)).add(yAxis.multByScalar(Math.cos(angle)));
            yAxis = yN.normalize();
        }
    }


    public void move(char axis, double step) {
        if (axis == 'X') {
            double newX = origin.getX() + step;
            origin.getVector()[0] = newX;
        } if (axis == 'Y') {
            double newY = origin.getX() + step;
            origin.getVector()[1] = newY;
        } if (axis == 'Z') {
            double newZ = origin.getX() + step;
            origin.getVector()[2] = newZ;
        }
    }


}
