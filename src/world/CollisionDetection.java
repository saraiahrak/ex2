/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package world;
import world.objects.*;
import Math.*;

import java.util.ArrayList;

/*************
 * Class Collision Detection
 * ***********/
public class CollisionDetection {

    /**
     * colDetect
     * Finds the collision detection
     * @param co1 - collision object
     * @param co2 - collision object
     * @return true if there is intersection, otherwise false
     */
    public static boolean colDetect(CollisionObject co1, CollisionObject co2) {
        // finds the instance of the given objects
        if (co1.isWall() && co2.isVector()) {
                return polyPointIntersection((Wall)co1, (Vector)co2);
        }
        return false;
    }

    /**
     * polyPointIntersection
     * Finds if point and polygon intersects
     * @param wall - collision object
     * @param position - collision object
     * @return true if there is intersection, otherwise false
     */
    public static boolean polyPointIntersection(Wall wall, Vector position) {
        ArrayList<Vector> vectors = new ArrayList<>();

        // get vectors between the corners of the wall to the position
        vectors.add(new Vector(wall.corner, position));
        vectors.add(new Vector(wall.corner1, position));
        vectors.add(new Vector(wall.corner2, position));
        vectors.add(new Vector(wall.corner3, position));

        // sum of angles
        float sum = 0;

        int i = 0;
        // get all the angles between two adjacent vectors
        for (; i <= 3; i++) {
            if (i == 3) {
                sum += vectors.get(i).getTheta(vectors.get(0));
            } else {
                sum += vectors.get(i).getTheta(vectors.get(i+1));
            }
        }

        if (sum <= 330) {
            return false;
        }
        return true;
    }
}
