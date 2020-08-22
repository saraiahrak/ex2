/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;

import Math.*;
import World.Objects.Box;
import World.Objects.CoinObject;
import World.Objects.CopObject;
import World.Objects.IObject;
import World.Space.World;

import javax.swing.plaf.synth.SynthStyle;

/****************
 * Class Point Box Collision
 * **************/
public class PointBoxCollision implements CollisionHandler {

    private float xMax = 0;
    private float xMin = 0;
    private float yMax = 0;
    private float yMin = 0;
    private float zMax = 0;
    private float zMin = 0;

    @Override
    public boolean handle(Collidable c1, Collidable c2) {
        Box b = null;
        IObject obj = null;
        if (isBox(c1)) {
            b = (Box) c1;
        } else {
            obj = (IObject) c1;
            b = obj.getBox();
        }

        Vector position = (Vector) c2;
        init(b);

        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();

        boolean intersection =
                x - 2 < xMax && x + 2 > xMin && y - 3 < yMax && y + 3 > yMin && z - 2 < zMax && z + 2 > zMin;

        if (intersection && obj != null) {
            notifyWorld(obj);
        }

        return intersection;
    }


    /**
     * notifyWorld
     * Checks the type of the model
     *
     * @return obj - model
     */
    private void notifyWorld(IObject obj) {
        if (isCop(obj)) {
            intersection((CopObject) obj);
            World.playerDisqualified = true;
            World.player.reduceLife();
        }
        if (isCoin(obj)) {
            World.player.addScore();
            World.removeDrawable((CoinObject) obj);
            World.removeCollidable((CoinObject) obj);
        }
    }


    private void intersection(CopObject cop) {
        cop.setPlayerIntersection();
    }

    /**
     * isCop
     * If it's a cop return true, otherwise return false
     *
     * @param obj collidable model
     */
    private boolean isCop(IObject obj) {
        try {
            CopObject cop = (CopObject) obj;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isBox
     * If it's a box return true, otherwise return false
     *
     * @param obj collidable object
     */
    private boolean isBox(Collidable obj) {
        try {
            Box b = (Box) obj;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * isCoin
     * If it's a coin return true, otherwise return false
     *
     * @param obj collidable model
     */
    private boolean isCoin(IObject obj) {
        try {
            CoinObject coin = (CoinObject) obj;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * init
     *
     * @param box - to initialize
     */
    private void init(Box box) {
        float height = box.getHeight();
        float width = box.getWidth();
        float depth = box.getDepth();
        Vertex bottom = box.getBottomLeft().clone();

        initValues(bottom, height, width, depth);
    }


    /**
     * initValues
     * Initialize the size
     *
     * @param bottom
     * @param h      - height
     * @param w      - width
     * @param d      - depth
     */
    private void initValues(Vertex bottom, float h, float w, float d) {
        xMin = bottom.getX();
        yMin = bottom.getY();
        zMin = bottom.getZ();

        xMax = xMin + w;
        yMax = yMin + h;
        zMax = zMin + d;
    }

}