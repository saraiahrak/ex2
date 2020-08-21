/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import Math.Vertex;
import Utils.Reader;
import World.CollisionDetection.Collidable;
import World.Drawable;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Wall;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Cave
 * ***********/
public class Cave implements Drawable {

    private Floor floor;
    private Ceiling ceiling;
    private Wall exit;
    private Wall genie;
    private List<Wall> maze;
    private ArrayList<Collidable> collidables;

    /*****************
     * Constructor
     * ***************/
    public Cave() {
        floor = new Floor("cave1", new Vertex(-40.5f, -2f, -113.6f), 0f, 130.5f, 100f);
        ceiling = new Ceiling("cave1", new Vertex(-40.5f, 8f, -113.6f), 0f, 130.5f, 100f);
        exit = new Wall("exit", new Vertex(-0.5f, -2, -13.6f), 10, 10, 0);
        genie = new Wall("genie", new Vertex(-0.5f, -2, -113.02f), 10, 10, 0);

        maze = new ArrayList<>();
        initMaze();
        setCollidables();
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
     * getCollidables
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }


    /**
     * setCollidables
     */
    private void setCollidables() {
        collidables = new ArrayList<>();
        collidables.addAll(maze);
    }


    /**
     * initMaze
     * Parse walls file, initialize maze walls
     */
    private void initMaze() {
        ArrayList<String> lines = Reader.readLines("resources/maze/walls.txt");
        maze = Wall.createWalls(lines, "cave");
    }


    /**
     * Getter
     * get list of walls
     */
    public List<Wall> getMaze() {
        return maze;
    }

}