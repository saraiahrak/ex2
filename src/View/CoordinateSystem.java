package View;

import Math.*;


/*****************
 * Class CoordinateSystem
 * ***************/
public class CoordinateSystem {
    private Vector xAxis;
    private Vector yAxis;
    private Vector zAxis;
    private Vertex origin;

    /**
     * constructor
     */
    public CoordinateSystem() {
        init();
    }

    /**
     * init
     * Call initializers
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
        //Vector x = View.up.cross(zAxis);
        //xAxis = x.scalar(1 / x.getSize());
        xAxis = new Vector(1, 0, 0);
        // Y-Axis
        //yAxis = zAxis.cross(xAxis);
        yAxis = new Vector(0, 1, 0);
        // Z-Axis
        //zAxis = z.scalar(1 / z.getSize());
        zAxis = new Vector(0, 0, 1);
    }


    /**
     * initOrigin
     * initialize origin vector
     */
    private void initOrigin() {
        origin = new Vertex(1, 1, 1);
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

    public Vertex getOrigin() {
        return origin;
    }

    /**
     * rotate
     * Changing the look
     */
//    public void rotate(char axis, double angle) {
//        Vector xNew, yNew, zNew;
//        // look up or down
//        if (axis == 'X') {
//            yNew = zAxis.multByScalar(Math.sin(angle)).add(yAxis.multByScalar(Math.cos(angle)));
//            yAxis = yNew.normalize();
//            zNew = zAxis.multByScalar(Math.cos(angle)).add(yAxis.multByScalar(Math.sin(angle)));
//            zAxis = zNew.normalize();
//            // look left or right
//        }
//        if (axis == 'Y') {
//            xNew = xAxis.multByScalar(Math.cos(angle)).add(zAxis.multByScalar(Math.sin(angle)));
//            xAxis = xNew.normalize();
//            zNew = xAxis.multByScalar(Math.sin(angle)).add(zAxis.multByScalar(Math.cos(angle)));
//            zAxis = zNew.normalize();
//            // divert the look to right or left
//        }
//        if (axis == 'Z') {
//            xNew = xAxis.multByScalar(Math.cos(angle)).add(yAxis.multByScalar(Math.sin(angle)));
//            xAxis = xNew.normalize();
//            yNew = xAxis.multByScalar(Math.sin(angle)).add(yAxis.multByScalar(Math.cos(angle)));
//            yAxis = yNew.normalize();
//        }
//    }

//
//    public void move(char axis, double step) {
//        if (axis == 'X') {
//            double newX = origin.getX() + step;
//            origin.getVector()[0] = newX;
//        }
//        if (axis == 'Y') {
//            double newY = origin.getX() + step;
//            origin.getVector()[1] = newY;
//        }
//        if (axis == 'Z') {
//            double newZ = origin.getX() + step;
//            origin.getVector()[2] = newZ;
//        }
//    }


}
