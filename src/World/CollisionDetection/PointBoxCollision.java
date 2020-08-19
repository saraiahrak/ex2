package World.CollisionDetection;

import Math.*;
import World.Objects.Box;
import World.Objects.CoinObject;
import World.Objects.CopObject;
import World.Objects.IObject;
import World.Space.World;

public class PointBoxCollision implements CollisionHandler {

    private float xMax = 0;
    private float xMin = 0;
    private float yMax = 0;
    private float yMin = 0;
    private float zMax = 0;
    private float zMin = 0;

    @Override
    public boolean handle(Collidable c1, Collidable c2) {

        IObject obj = (IObject) c1;
        Box box = obj.getBox();

        Vector position = (Vector) c2;
        init(box);

        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();

        boolean intersection = x < xMax && x > xMin && y < yMax && y > yMin && z < zMax && z > zMin;

        if (intersection) {
            notifyWorld(position, obj);
        }

        return intersection;
//        return (x < xMax && x > xMin && y < yMax && y > yMin && z < zMax && z > zMin);

    }

    private void notifyWorld(Vector v, IObject obj) {
        if (isCop(obj)) {
            World.playerDisqualified = true;
//            World.removeLife();
        }
        if (isCoin(obj)) {
            World.player.addScore();
            World.removeDrawable((CoinObject) obj);
            World.removeCollidable((CoinObject) obj);
            System.out.println("score: " + World.player.getScore());
        }
    }


    private boolean isCop(IObject obj) {
        try {
            CopObject cop = (CopObject) obj;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private boolean isCoin(IObject obj) {
        try {
            CoinObject coin = (CoinObject) obj;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void init(Box box) {
        float height = box.getHeight();
        float width = box.getWidth();
        float depth = box.getDepth();
        Vertex bottom = box.getBottomLeft().clone();

        initValues(bottom, height, width, depth);
    }

    private void initValues(Vertex bottom, float h, float w, float d) {
        xMin = bottom.getX();
        yMin = bottom.getY();
        zMin = bottom.getZ();

        xMax = xMin + w;
        yMax = yMin + h;
        zMax = zMin + d;

    }


}
