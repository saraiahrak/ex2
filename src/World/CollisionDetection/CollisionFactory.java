package World.CollisionDetection;

import Math.*;
import World.Objects.Polygon;


public class CollisionFactory {

    public static CollisionHandler create(Collidable c1, Collidable c2) {
        if ((isPolygon(c1) && isVector(c2)) || (isVector(c1) && isPolygon(c2))) {
            return new PolyPointCollision();
        }
        return null;
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
