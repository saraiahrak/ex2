/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import Math.Vertex;
import World.CollisionDetection.Collidable;
import World.Drawable;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Wall;
import javax.media.opengl.GL2;
import java.util.ArrayList;

/*************
 * Class MarketPlace
 * ***********/
public class MarketPlace implements Drawable {

    private ArrayList<Collidable> collidables;
    private Floor floor;
    private Ceiling ceiling;
    private Wall cave;
    private Wall left;
    private Wall right;
    private Wall back;


    /*****************
     * Constructor
     * ***************/
    public MarketPlace() {
        floor = new Floor("floor", new Vertex(-10f, -2f, -13f), 0f, 30f, 200f);
        ceiling = new Ceiling("ceiling", new Vertex(-10f, 8f, -13f), 0f, 30f, 200f);
        left = new Wall("brick", new Vertex(-10f, -2f, -13f), 10f, 0f, 200f);
        right = new Wall("brick", new Vertex(20f, -2f, -13f), 10f, 0f, 200f);
        back = new Wall("back", new Vertex(-10f, -2f, 187f), 10f, 30f, 0f);
        cave = new Wall("caveEntry", new Vertex(-10f, -2f, -13f), 10f, 30f, 0f);

        setCollidables();
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
        collidables.add(back);
        collidables.add(left);
        collidables.add(right);
    }

    @Override
    public void draw(GL2 gl) {
        floor.draw(gl);
        ceiling.draw(gl);
        left.draw(gl);
        right.draw(gl);
        back.draw(gl);
        cave.draw(gl);
    }

}