/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;

import Math.Vector;
import Math.Vertex;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Polygon;
import java.util.ArrayList;

/****************
 * Class Polygon Point Collision
 * **************/
public class PolyPointCollision implements CollisionHandler {

    @Override
    public boolean handle(Collidable c1, Collidable c2) {
        Polygon polygon = (Polygon) c1;
        Vector position = (Vector) c2;

        ArrayList<Vertex> vertices = polygon.getVertices();
        ArrayList<Vector> vectors = createVectors(polygon, position);

        float sum = getAngleSum(vectors);

        if (isFloor(polygon)) {
            Floor f = (Floor)polygon;
            Vertex bottom = f.corner.clone();

            boolean inRange =isPlayerInRange(bottom.getX(), f.width, bottom.getZ(), f.depth, position);
            float lim = vertices.get(0).getY();
            return inRange && position.getY() + 2 < lim ;
        }

        if (isCeiling(polygon)) {
            float lim = vertices.get(0).getY();
            return position.getY() > lim;
        }

        return sum > 360 - polygon.getDistFactor();
    }


    /**
     * createVectors
     * Creates the vectors of the given polygon
     *
     * @param p - polygon
     * @param position - player position
     * @return list of vectors
     */
    private ArrayList<Vector> createVectors(Polygon p, Vector position) {
        ArrayList<Vector> vectors = new ArrayList<>();
        ArrayList<Vertex> vertices = p.getVertices();
        for (Vertex v : vertices) {
            Vector connect = new Vector(v, position);
            vectors.add(connect);
        }
        return vectors;
    }


    /**
     * getAngleSum
     * Gets the angle between the player and the given vectors
     *
     * @param vectors
     * @return angles amount
     */
    private float getAngleSum(ArrayList<Vector> vectors) {
        // sum of angles
        float sum = 0;

        int i = 0;
        // get all the angles between two adjacent vectors
        for (; i <= 3; i++) {
            if (i == 3) {
                sum += vectors.get(i).getTheta(vectors.get(0));
            } else {
                sum += vectors.get(i).getTheta(vectors.get(i + 1));
            }
        }
        return sum;
    }


    /**
     * isFloor
     * If it's a floor return true, otherwise return false
     *
     * @param p - polygon
     */
    private boolean isFloor(Polygon p) {
        try {
            Floor f = (Floor) p;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isCeiling
     * If it's a ceiling return true, otherwise return false
     *
     * @param p - polygon
     */
    private boolean isCeiling(Polygon p) {
        try {
            Ceiling f = (Ceiling) p;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isPlayerInRange
     *
     * @param - game range
     */
    private boolean isPlayerInRange(float x, float w, float z, float d, Vector position) {
        float xPos = position.getX();
        float zPos = position.getZ();

        return (xPos > x && xPos < x + w && zPos > z && zPos < z + d);
    }

}