/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;

import World.Objects.Box;
import Math.*;
import World.Objects.IObject;

/****************
 * Class AAABCollision
 * **************/
public class AAABCollision implements CollisionHandler {

    @Override
    public boolean handle(Collidable c1, Collidable c2) {
        IObject obj1 = (IObject) c1;
        IObject obj2 = (IObject) c2;

        Box b1 = obj1.getBox();
        Box b2 = obj2.getBox();

        Vertex bottom1 = b1.getBottomLeft().clone();
        Vertex bottom2 = b2.getBottomLeft().clone();

        float h1 = b1.getHeight();
        float w1 = b1.getWidth();
        float d1 = b1.getDepth();

        float h2 = b2.getHeight();
        float w2 = b2.getWidth();
        float d2 = b2.getDepth();

        return checkOverlap(bottom1.getX(), bottom1.getX() + w1, bottom1.getY(), bottom1.getY() + h1,
                bottom1.getZ(), bottom1.getZ() + d1, bottom2.getX(), bottom2.getX() + w2, bottom2.getY(),
                bottom2.getY() + h2, bottom2.getZ(), bottom2.getZ() + d2);

    }


    /**
     * checkOverlap
     * Checks if there is an overlap between two boxes
     *
     * @param - boxes vertex
     * @return true if there is an overlap, otherwise false
     */
    private boolean checkOverlap(float minX1, float maxX1, float minY1, float maxY1, float minZ1, float maxZ1,
                                 float minX2, float maxX2, float minY2, float maxY2, float minZ2, float maxZ2) {
        return (minX1 <= maxX2 && maxX1 >= minX2) && (minY1 <= maxY2 && maxY1 >= minY2)
                && (minZ1 <= maxZ2 && maxZ1 >= minZ2);
    }

}
