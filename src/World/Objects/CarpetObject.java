/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Objects;

import World.Drawable;
import World.Models.*;
import Math.*;
import javax.media.opengl.GL2;

/*************
 * Class Carpet Object
 * ***********/
public class CarpetObject implements IObject, Drawable {

    private Vertex position;
    private Model model;
    private final float depth = 2;
    private final float height = 2;
    private final float width = 2;
    private float[] motion = {0f, 0f, 0f};

    /*****************
     * Constructor
     * ***************/
    public CarpetObject(Model m, Vertex p) {
        position = p;
        model = m;
    }

    /*************
     * Getters
     * **********/

    @Override
    public float getDepth() {
        return depth;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public Box getBox() {
        return null;
    }

    @Override
    public Vertex getPosition() {
        return position;
    }

    @Override
    public void draw(GL2 gl) {
        model.draw(gl);
    }

    @Override
    public void setMotion(float dx, float dy, float dz) {
        motion = new float[]{dx, dy, dz};
        model.setMotion(dx, dy, dz);
    }

    @Override
    public void translate(float x, float y, float z) {
        model.translate(x, y, z);
    }

    @Override
    public void scale(float x, float y, float z) {
        model.scale(x, y, z);
    }

    @Override
    public void rotate(float angle, float x, float y, float z) {
        model.rotate(angle, x, y, z);
    }

}
