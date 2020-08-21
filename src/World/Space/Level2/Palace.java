/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

import Math.Vertex;
import Utils.Reader;
import World.Drawable;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Wall;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Palace
 * ***********/
public class Palace implements Drawable {

    public List<Wall> externalWalls;
    public List<Wall> internalWalls;
    public Wall palaceEntry;
    private Floor floor;
    private Ceiling ceiling;

    /*****************
     * Constructor
     * ***************/
    public Palace() {
        floor = new Floor("palaceFloor", new Vertex(40f, 32f, -160f), 0f, 240f, 159.5f);
        ceiling = new Ceiling("palaceCeiling", new Vertex(40f, 57f, -160f), 0f, 240f, 159.5f);
        palaceEntry = new Wall("palaceView", new Vertex(40, 32, -0.5f), 25, 240, 0);

        internalWalls = new ArrayList<>();
        externalWalls = new ArrayList<>();

        initPalace();
    }

    @Override
    public void draw(GL2 gl) {
        floor.draw(gl);
        ceiling.draw(gl);
        palaceEntry.draw(gl);
        for (Wall wall : internalWalls) {
            wall.draw(gl);
        }
        for (Wall wall : externalWalls) {
            wall.draw(gl);
        }
    }


    /**
     * initPalace
     * Parse walls file, initialize palace walls
     */
    private void initPalace() {
        ArrayList<String> internalLines = Reader.readLines("resources/palace/internalWalls.txt");
        internalWalls = Wall.createWalls(internalLines, "palaceWall");

        ArrayList<String> externalLines = Reader.readLines("resources/palace/externalWalls.txt");
        externalWalls = Wall.createWalls(externalLines, "palaceView");
    }


    /**
     * Getter
     * get list of the internal walls
     */
    public List<Wall> getInternalWalls() {
        return internalWalls;
    }


    /**
     * Getter
     * get list of the external walls
     */
    public List<Wall> getExternalWalls() {
        return externalWalls;
    }

}