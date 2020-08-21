/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;
import Math.Vector;

/*************
 * Class Collision Detection
 * ***********/
public class CollisionDetection {

    /**
     * checkBoundaries
     * Checks if the player in the axis boundaries
     *
     * @param axis
     * @param origin - player position
     * @param min boundary
     * @param max boundary
     * @return true if the player is in the given boundaries, otherwise false
     */
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