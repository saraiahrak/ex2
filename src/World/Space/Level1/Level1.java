/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import Utils.Reader;
import World.CollisionDetection.CollisionObject;
import World.Drawable;
import World.Models.OBJLoader;
import World.Objects.Box;
import World.Objects.Wall;
import World.Space.World;
import Math.*;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Level 1
 * ***********/
public class Level1 {

    /**
     * createSpace
     */
    public static void createSpace() {

        MarketPlace market = new MarketPlace();
        Cave cave = new Cave();

        addDrawable(market);
        addDrawable(cave);

        addCollidable(market);
        addCollidable(cave);
    }

    /**
     * createObjects
     */
    public static void createObjects() {

 /*       Box box = new Box("wood", new Vertex(-5.5f, -2f, -13f), 1, 1, 1);
        Box box1 = new Box("LightWood", new Vertex(1f, -2f, -6f), 1, 0.5f, 1);
        Box box2 = new Box("washedWood", new Vertex(4.5f, -2f, -11f), 1, 1, 1);

        addDrawable(box);
        addDrawable(box1);
        addDrawable(box2);*/

    }


    /**
     * createModels
     *
     * @param gl - GL2
     */
    public static void createModels(GL2 gl) {
        // add street light
        createStreetLights(gl);
        // add benches
        createBenches(gl);
        // add coins
        createCoins(gl);


        OBJLoader mapScroll = new OBJLoader("models/mapScroll/map_Scroll.obj", gl);
        addDrawable(mapScroll.getModel());
        mapScroll.getModel().translate(0, 0, 0);
        mapScroll.getModel().scale(0.02f, 0.02f, 0.02f);

    }


    /**
     * createStreetLights
     *
     * @param gl - GL2
     */
    private static void createStreetLights(GL2 gl) {
        ArrayList<String> lines = Reader.readLines("resources/models/dualLight/Lights.txt");
        for (String line: lines) {
            String[] values = line.split(" ");

            OBJLoader singleLight =
                    new OBJLoader("models/dualLight/classic_dual_light.obj", gl);
            addDrawable(singleLight.getModel());
            singleLight.getModel().translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]),Float.parseFloat(values[2]));
            singleLight.getModel().scale(0.02f, 0.02f, 0.02f);
        }
    }


    /**
     * createBenches
     *
     * @param gl - GL2
     */
    private static void createBenches(GL2 gl) {
        ArrayList<String> lines = Reader.readLines("resources/models/bench/Benches.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader bench = new OBJLoader("models/bench/classic_park_bench.obj", gl);
            addDrawable(bench.getModel());
            bench.getModel().translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]),Float.parseFloat(values[2]));
            bench.getModel().scale(0.02f, 0.02f, 0.02f);
            bench.getModel().rotate(Float.parseFloat(values[3]), 0, 1, 0);
        }
    }


    /**
     * createCoins
     *
     * @param gl - GL2
     */
    private static void createCoins(GL2 gl) {
        ArrayList<String> lines = Reader.readLines("resources/models/coin/coins.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader coin = new OBJLoader("models/coin/uSOLDIER_Napoleon_Coin.obj", gl);
            addDrawable(coin.getModel());
            coin.getModel().translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]),Float.parseFloat(values[2]));
            coin.getModel().scale(0.004f, 0.004f, 0.004f);
            coin.getModel().movement(0.00000000001f, 0, 1, 0);
        }
    }


    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param c - collidable
     */
    private void addCollidable(CollisionObject c) {
        World.collisionObjects.add(c);
    }

    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param marketPlace - collidables
     */
    private static void addCollidable(MarketPlace marketPlace) {
        World.collisionObjects.add(marketPlace.back);
        World.collisionObjects.add(marketPlace.cave);
        World.collisionObjects.add(marketPlace.left);
        World.collisionObjects.add(marketPlace.right);
    }

    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param cave - collidables
     */
    private static void addCollidable(Cave cave) {
        World.collisionObjects.add(cave.genie);
        List<Wall> walls = cave.getMaze();
        for (Wall wall: walls) {
            World.collisionObjects.add(wall);
        }
    }


    /**
     * addDrawable
     * Add drawable to list of drawable objects
     *
     * @param d - drawable
     */
    private static void addDrawable(Drawable d) {
        World.drawables.add(d);
    }

}
