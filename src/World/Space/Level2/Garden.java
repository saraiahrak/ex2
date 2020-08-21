/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

import Math.Vertex;
import Utils.Reader;
import World.Drawable;
import World.Objects.Box;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Wall;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Garden
 * ***********/
public class Garden implements Drawable {

    public Floor floor;
    public Ceiling ceiling;
    public Wall left;
    public Wall palaceEntry;
    public Wall right;
    public Wall back;

    public static List<Box> bushes;

    /*****************
     * Constructor
     * ***************/
    public Garden() {
        floor = new Floor("grass", new Vertex(0f, 0f, 0f), 0f, 100f, 100f);
        ceiling = new Ceiling("gardenCeiling", new Vertex(0f, 80f, 0f), 0f, 100f, 100f);
        palaceEntry = new Wall("palace", new Vertex(0f, 0f, 0f), 80f, 100f, 0f);
        left = new Wall("gardenWall", new Vertex(0f, 0f, 0f), 80f, 0f, 100f);
        right = new Wall("gardenWall", new Vertex(100f, 0f, 0f), 80f, 0f, 100f);
        back = new Wall("gardenWall", new Vertex(0f, 0f, 100f), 80f, 100f, 0f);

        bushes = new ArrayList<>();
        initObstacles();
    }


    /**
     * initObstacles
     * Parse obstacle file, initialize boxes
     */
    private static void initObstacles() {
        ArrayList<String> lines = Reader.readLines("resources/garden/obstacles.txt");
        bushes = Box.createBoxes(lines, "bush");
    }

    @Override
    public void draw(GL2 gl) {
        floor.draw(gl);
        ceiling.draw(gl);
        left.draw(gl);
        right.draw(gl);
        back.draw(gl);
        palaceEntry.draw(gl);
        for (Box bush : bushes) {
            bush.draw(gl);
        }
    }


    /**
     * Getter
     * get list of boxes
     */
    public List<Box> getObstacles() {
        return bushes;
    }

}