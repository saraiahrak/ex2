/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import World.Drawable;
import World.Objects.Ceiling;
import World.Objects.Floor;
import World.Objects.Wall;
import Math.*;

import javax.media.opengl.GL2;

/*************
 * Class MarketPlace
 * ***********/
public class MarketPlace implements Drawable {

    private Floor floor;
    private Ceiling ceiling;
    public Wall left;
    public Wall cave;
    public Wall right;
    public Wall back;


    /*****************
     * Constructor
     * ***************/
    public MarketPlace() {
        floor = new Floor("floor", new Vertex(-10f, -2f, -13f), 0f, 30f, 187f);
        ceiling = new Ceiling("ceiling", new Vertex(-10f, 8f, -13f), 0f, 30f, 187f);
        left = new Wall("brick", new Vertex(-10f, -2f, -13f), 10f, 0f, 200f);
        right = new Wall("brick", new Vertex(20f, -2f, -13f), 10f, 0f, 200f);
        back = new Wall("back", new Vertex(-10f, -2f, 187f), 10f, 30f, 0f);
        cave = new Wall("caveEntry", new Vertex(-10f, -2f, -13f), 10f, 30f, 0f);
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
