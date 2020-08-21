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
 * Class Bench Object
 * ***********/
public class BenchObject implements IObject, Drawable {

    private Vertex position;
    private Model model;
    private final float depth = 4;
    private final float height = 2;
    private final float width = 2;
    private float[] motion;

    private Box wrap = null;

    /*****************
     * Constructor
     * ***************/
    public BenchObject(Model m, Vertex p) {
        model = m;
        position = p;
        createWrap();
    }

    /*************
     * Getters
     * **********/

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getDepth() {
        return depth;
    }

    @Override
    public Vertex getPosition() {
        return position;
    }

    @Override
    public Box getBox() {
        return wrap;
    }

    private void createWrap() {
        Vertex v = new Vertex(position.getX() - (width / 4), position.getY(), position.getZ() - (depth / 2));
        wrap = new Box("wood", v, depth, height, width);
    }

    @Override
    public void draw(GL2 gl) {
        model.draw(gl);
    }


    private void drawLeft(GL2 gl) {
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        Vertex v = new Vertex(position.getX() - (width / 4), position.getY(), position.getZ() - (depth / 2));

        float x = v.getX();
        float y = v.getY();
        float z = v.getZ();

        gl.glBegin(GL2.GL_LINES);

        // Bottom Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);


        // Left Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);

        // Back Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);

        gl.glEnd();

    }

    private void drawWrap(GL2 gl) {
        //
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        Vertex v = new Vertex(position.getX() - (width / 4), position.getY(), position.getZ() - (depth / 2));

        gl.glBegin(GL2.GL_LINES);

        float x = v.getX();
        float y = v.getY();
        float z = v.getZ();


        // top Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);


        // Right Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);

        // Front Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);


        gl.glEnd();
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