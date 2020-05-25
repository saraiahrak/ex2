/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package world.Space;

import world.Drawable;
import world.objects.Ceiling;
import world.objects.Floor;
import world.objects.Wall;
import Math.*;

import javax.media.opengl.GL2;

/*************
 * Class Room
 * ***********/
public class Room implements Drawable {

    private Floor floor;
    private Ceiling ceiling;
    public Wall left;
    public Wall front;
    public Wall right;
    public Wall back;

    public Room() {
        floor = new Floor("floor");
        ceiling = new Ceiling("ceiling");
        left = new Wall("brick", new Vertex(-5.5f, -2f, -13f), 4f, 0f, 14f);
        front = new Wall("brick", new Vertex(-5.5f, -2f, -13f), 4f, 11f, 0f);
        right = new Wall("brick", new Vertex(5.5f, -2f, -13f), 4f, 0f, 14f);
        back = new Wall("brick", new Vertex(-5.5f, -2f, 1f), 4f, 11f, 0f);
    }

    @Override
    public void draw(GL2 gl) {
        floor.draw(gl);
        ceiling.draw(gl);
        left.draw(gl);
        front.draw(gl);
        right.draw(gl);
        back.draw(gl);
    }
}
