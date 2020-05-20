//package View;
//import Math.*;
//
//public class CoordinateSystem {
//    public static Vector xAxis;
//    public static Vector yAxis;
//    public static Vector zAxis;
//    public static Vector origin;
//
//    public CoordinateSystem() {
//        initAxes();
//        initOrigin();
//    }
//
//
//    /**
//     * initAxes
//     * initialize axes vectors
//     */
//    private void initAxes() {
//        Vector z = View.position.sub(View.lookAt);
//        zAxis = z.scalar(1 / z.getSize());
//        // X-Axis
//        Vector x = View.up.cross(zAxis);
//        xAxis = x.scalar(1 / x.getSize());
//        // Y-Axis
//        yAxis = zAxis.cross(xAxis);
//    }
//
//    private void initOrigin() {
//        origin = View.position.clone();
//    }
//
//
//}
