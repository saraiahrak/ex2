/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import World.Drawable;
import World.Objects.*;
import Math.*;

import javax.media.opengl.GL2;
import java.util.*;
import Utils.Reader;

/*************
 * Class Cave
 * ***********/
public class Cave implements Drawable {

    private Floor floor;
    private Ceiling ceiling;
    public Wall exit;
    public Wall genie;
    public List<Wall> maze;


    /*****************
     * Constructor
     * ***************/
    public Cave() {
        floor = new Floor("ceiling2", new Vertex(-40.5f, -2f, -113.02f), 0f, 90f, -13.02f);
        ceiling = new Ceiling("ceiling2", new Vertex(-40.5f, 8f, -113.02f), 0f, 90f, -13.02f);
        exit = new Wall("exit", new Vertex(-0.5f, -2, -13.5f), 10, 10, 0);
        genie = new Wall("genie", new Vertex(-0.5f, -2, -113.02f), 10, 10, 0);

        maze = new ArrayList<>();
        initMaze();
    }

    @Override
    public void draw(GL2 gl) {
        floor.draw(gl);
        ceiling.draw(gl);
        exit.draw(gl);
        genie.draw(gl);
        for (Wall wall : maze) {
            wall.draw(gl);
        }
    }


    /**
     * initMaze
     * Parse walls file, initialize maze walls
     */
    private void initMaze() {
        ArrayList<String> lines = Reader.readLines("resources/maze/walls.txt");
        maze = createWalls(lines);
    }

    /**
     * Getter
     * get list of walls
     */
    public List<Wall> getMaze() {
        return maze;
    }

    /**
     * createWalls
     * Create walls from string of coordinates
     *
     * @param lines string of coordinates
     * @return walls array
     */
    private ArrayList<Wall> createWalls(List<String> lines) {
        ArrayList<Wall> wallsList = new ArrayList<>();

        for (String line : lines) {
            String[] coordinate = line.split(" ");
            float x = Float.parseFloat(coordinate[0]);
            float y = Float.parseFloat(coordinate[1]);
            float z = Float.parseFloat(coordinate[2]);
            float h = Float.parseFloat(coordinate[3]);
            float w = Float.parseFloat(coordinate[4]);
            float d = Float.parseFloat(coordinate[5]);

            wallsList.add(new Wall("cave", new Vertex(x, y, z), h, w, d));
        }
        return wallsList;
    }
}