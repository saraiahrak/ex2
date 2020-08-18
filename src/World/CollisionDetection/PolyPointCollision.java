package World.CollisionDetection;

import Math.Vector;
import Math.Vertex;
import World.Objects.Polygon;

import java.util.ArrayList;


public class PolyPointCollision implements CollisionHandler {

    public boolean handle(Collidable c1, Collidable c2) {
        Polygon polygon = (Polygon) c1;
        Vector position = (Vector) c2;

        ArrayList<Vertex> vertices = polygon.getVertices();
        ArrayList<Vector> vectors = createVectors(polygon, position);

        float sum = getAngleSum(vectors);

        return sum > 360 - polygon.getDistFactor();
    }

    private ArrayList<Vector> createVectors(Polygon p, Vector position) {
        ArrayList<Vector> vectors = new ArrayList<>();
        ArrayList<Vertex> vertices = p.getVertices();
        for (Vertex v : vertices) {
            Vector connect = new Vector(v, position);
            vectors.add(connect);
        }
        return vectors;
    }

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
}


