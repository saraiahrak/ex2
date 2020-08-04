/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import Utils.Reader;
import World.Drawable;
import World.Models.OBJLoader;
import World.Objects.Wall;
import World.Space.World;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Level 1
 * ***********/
public class Level1 {

    public Level1(GL2 gl) {
        createSpace();
        createModels(gl);
    }

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
        // add cops
        createCops(gl);
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
            singleLight.getModel().scale(0.014f, 0.014f, 0.014f);
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
            bench.getModel().scale(0.014f, 0.014f, 0.014f);
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

            OBJLoader coin = new OBJLoader("models/coin/coin.obj", gl);
            addDrawable(coin.getModel());
            coin.getModel().translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]),Float.parseFloat(values[2]));
            coin.getModel().scale(0.2f, 0.2f, 0.2f);
            coin.getModel().rotate(90, 1, 0, 0);
            coin.getModel().movement(0.01f, 0, 0, 1);
        }
    }


    /**
     * createCops
     *
     * @param gl - GL2
     */
    private static void createCops(GL2 gl) {
        ArrayList<String> lines = Reader.readLines("resources/models/cops/cops.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader cop = new OBJLoader("models/cops/Dusty_2.obj", gl);
            addDrawable(cop.getModel());
            cop.getModel().translate(Float.parseFloat(values[0]),
                    -2, Float.parseFloat(values[1]));
            cop.getModel().scale(0.0007f, 0.0007f, 0.0007f);
        }
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
