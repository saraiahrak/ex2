/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/
package World.CollisionDetection;

import Math.Vector;
import World.Objects.Box;
import World.Objects.Polygon;
import World.Objects.Wall;
import World.*;

import java.util.ArrayList;

/*************
 * Class Collision Detection
 * ***********/
public class CollisionDetection {

    public static boolean checkBoundaries(String axis, Vector origin, float min, float max) {
        if (axis == "x") {
            if (origin.getX() > min && origin.getX() < max) {
                return true;
            }
        } else if (axis == "y") {
            if (origin.getY() > min && origin.getY() < max) {
                return true;
            }
        } else if (axis == "z") {
            if (origin.getZ() > min && origin.getZ() < max) {
                return true;
            }
        }
        return false;
    }
}