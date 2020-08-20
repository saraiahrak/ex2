package World.CollisionDetection;

import Math.Vector;
import Math.Vertex;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Polygon;
import World.Space.World;

import java.util.ArrayList;


public class PolyPointCollision implements CollisionHandler {

    public boolean handle(Collidable c1, Collidable c2) {
        Polygon polygon = (Polygon) c1;
        Vector position = (Vector) c2;

        ArrayList<Vertex> vertices = polygon.getVertices();
        ArrayList<Vector> vectors = createVectors(polygon, position);

        float sum = getAngleSum(vectors);

        if (isFloor(polygon)) {
            System.out.println("x: " + World.player.getPosition().getX() + " y: " + World.player.getPosition().getY() + " z: " + World.player.getPosition().getZ());
            System.out.println("sum of angles: " + sum);
            System.out.println("floor y: " + vertices.get(0).getY());
            System.out.println(((Floor)polygon).getTextureKey());

            Floor f = (Floor)polygon;
            Vertex bottom = f.corner.clone();

            boolean inRange =isPlayerInRange(bottom.getX(), f.width, bottom.getZ(), f.depth, position);
            float lim = vertices.get(0).getY();
            return inRange && position.getY() + 2 < lim ;
//            if () {
//            }

        }

        if (isCeiling(polygon)) {
            float lim = vertices.get(0).getY();
            return position.getY() > lim;
        }

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

    private boolean isFloor(Polygon p) {
        try {
            Floor f = (Floor) p;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCeiling(Polygon p) {
        try {
            Ceiling f = (Ceiling) p;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPlayerInRange(float x, float w, float z, float d, Vector position) {
        float xPos = position.getX();
        float zPos = position.getZ();

        return (xPos > x && xPos < x + w && zPos > z && zPos < z + d);
    }
}


