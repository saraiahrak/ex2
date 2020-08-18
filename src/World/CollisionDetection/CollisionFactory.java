package World.CollisionDetection;

import Math.Vector;
import World.Objects.Box;
import World.Objects.IObject;
import World.Objects.Polygon;
import World.Objects.Wall;


public class CollisionFactory {

    public static CollisionHandler create(Collidable c1, Collidable c2) {
        if ((isPolygon(c1) && isVector(c2)) || (isVector(c1) && isPolygon(c2))) {
            return new PolyPointCollision();
        }
//
//        if ((isBox(c1) && isWall(c2)) || (isWall(c1) && isBox(c2))) {
//            return new PolyBoxCollision();
//        }

        if ((isObject(c1) && isVector(c2)) || (isVector(c1) && isObject(c2))) {
            return new PointBoxCollision();
        }

        if (isObject(c1) && isObject(c2)) {
            return new AAABCollision();
        }

        return null;
    }

    private static boolean isObject(Collidable c) {
        try {
            IObject obj = (IObject) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isBox(Collidable c) {
        try {
            Box b = (Box) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isWall(Collidable c) {
        try {
            Wall w = (Wall) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isPolygon(Collidable c) {
        try {
            Polygon p = (Polygon) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isVector(Collidable c) {
        try {
            Vector v = (Vector) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
