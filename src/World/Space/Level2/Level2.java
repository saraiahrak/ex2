/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

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
 * Class Level 2
 * ***********/
public class Level2 {
    public GL2 gl;

    /**
     * constructor
     */
    public Level2(GL2 g) {
        gl = g;
    }

    public static void createSpace() {

/*        MarketPlace market = new MarketPlace();
        Cave cave = new Cave();

        addDrawable(market);
        addDrawable(cave);

        addCollidable(market);
        addCollidable(cave);*/
    }

    /**
     * createObjects
     */
    public static void createObjects() {

        Box box = new Box("wood", new Vertex(-5.5f, -2f, -13f), 1, 1, 1);
        Box box1 = new Box("LightWood", new Vertex(1f, -2f, -6f), 1, 0.5f, 1);
        Box box2 = new Box("washedWood", new Vertex(4.5f, -2f, -11f), 1, 1, 1);

        addDrawable(box);
        addDrawable(box1);
        addDrawable(box2);
    }


    /**
     * createModels
     */
    public static void createModels() {
        // add street light
/*        ArrayList<String> lines = Reader.readLines("resources/models/Lights.txt");
        for (String line: lines) {
            String[] values = line.split(" ");

            OBJLoader singleLight = new OBJLoader("models/dualLight/classic_dual_light.obj", gl);
            addDrawable(singleLight.getModel());
            singleLight.getModel().translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]),Float.parseFloat(values[2]));
            singleLight.getModel().scale(Float.parseFloat(values[3]),
                    Float.parseFloat(values[4]), Float.parseFloat(values[5]));
        }

        OBJLoader bench = new OBJLoader("models/bench/classic_park_bench.obj", gl);
        addDrawable(bench.getModel());
        bench.getModel().translate(-5f,-2f,32f);
        bench.getModel().scale(0.02f,0.02f,0.02f);
        bench.getModel().rotate(270, 0, 1, 0);


        OBJLoader coin = new OBJLoader("models/coin/uSOLDIER_Napoleon_Coin.obj", gl);
        addDrawable(coin.getModel());
        coin.getModel().translate(0f,0.5f,32f);
        coin.getModel().scale(0.01f,0.01f,0.01f);
        coin.getModel().movement(0.00000000001f, 0, 1, 0);*/
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
     * addDrawable
     * Add drawable to list of drawable objects
     *
     * @param d - drawable
     */
    private static void addDrawable(Drawable d) {
        World.drawables.add(d);
    }

}
