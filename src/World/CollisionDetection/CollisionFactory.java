/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;

import Math.Vector;
import World.Objects.Box;
import World.Objects.IObject;
import World.Objects.Polygon;
import World.Objects.Wall;

/****************
 * Class Collision Factory
 * **************/
public class CollisionFactory {

    /**
     * create
     * Returns the type of the collision
     *
     * @param c1 first object
     * @return c2 second object
     */
    public static CollisionHandler create(Collidable c1, Collidable c2) {
        if ((isPolygon(c1) && isVector(c2)) || (isVector(c1) && isPolygon(c2))) {
            return new PolyPointCollision();
        }

        if ((isObject(c1) && isVector(c2)) || (isVector(c1) && isObject(c2))) {
            return new PointBoxCollision();
        }

        if ((isBox(c1) && isVector(c2)) || (isVector(c1) && isBox(c2))) {
            return new PointBoxCollision();
        }

        if (isObject(c1) && isObject(c2)) {
            return new AAABCollision();
        }

        return null;
    }


    /**
     * isObject
     * If it's an object return true, otherwise return false
     *
     * @param c collidable object
     */
    private static boolean isObject(Collidable c) {
        try {
            IObject obj = (IObject) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isBox
     * If it's a box return true, otherwise return false
     *
     * @param c collidable object
     */
    private static boolean isBox(Collidable c) {
        try {
            Box b = (Box) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isWall
     * If it's a wall return true, otherwise return false
     *
     * @param c collidable object
     */
    private static boolean isWall(Collidable c) {
        try {
            Wall w = (Wall) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isPolygon
     * If it's a polygon return true, otherwise return false
     *
     * @param c collidable object
     */
    private static boolean isPolygon(Collidable c) {
        try {
            Polygon p = (Polygon) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isVector
     * If it's a vector return true, otherwise return false
     *
     * @param c collidable object
     */
    private static boolean isVector(Collidable c) {
        try {
            Vector v = (Vector) c;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}