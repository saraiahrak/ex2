/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

import Utils.Reader;
import World.Drawable;
import World.Objects.Box;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Wall;
import Math.*;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

public class Garden implements Drawable {

    private Floor floor;
    private Ceiling ceiling;
    public Wall left;
    public Wall front;
    public Wall right;
    public Wall back;

    public static List<Box> boxes;

    /*****************
     * Constructor
     * ***************/
    public Garden() {
        floor = new Floor("grass", new Vertex(0f, 0f, 0f), 0f, 100f, 100f);
        ceiling = new Ceiling("ceiling3", new Vertex(0f, 80f, 0f), 0f, 100f, 100f);
        front = new Wall("palace", new Vertex(0f, 0f, 0f), 80f, 100f, 0f);
        left = new Wall("gardenWall", new Vertex(0f, 0f, 0f), 80f, 0f, 100f);
        right = new Wall("gardenWall", new Vertex(100f, 0f, 0f), 80f, 0f, 100f);
        back = new Wall("gardenWall", new Vertex(0f, 0f, 100f), 80f, 100f, 0f);

        boxes = new ArrayList<>();
        initObstacles();
    }


    /**
     * initObstacles
     * Parse obstacle file, initialize boxes
     */
    private static void initObstacles() {
        ArrayList<String> lines = Reader.readLines("resources/garden/obstacles.txt");
        boxes = Box.createBoxes(lines);
    }

    @Override
    public void draw(GL2 gl) {
        floor.draw(gl);
        ceiling.draw(gl);
        left.draw(gl);
        right.draw(gl);
        back.draw(gl);
        front.draw(gl);
        for (Box box : boxes) {
            box.draw(gl);
        }
    }


    /**
     * Getter
     * get list of boxes
     */
    public List<Box> getObstacles() {
        return boxes;
    }


}
