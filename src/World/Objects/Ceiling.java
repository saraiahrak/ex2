/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Objects;

import Design.TextureFactory;
import Math.Vertex;
import World.Drawable;
import com.jogamp.opengl.util.texture.Texture;
import javax.media.opengl.GL2;
import java.util.ArrayList;

/*************
 * Class Ceiling
 * ***********/
public class Ceiling implements Drawable, Polygon {

    private String textureKey;
    private Texture texture;
    public float height;
    public float depth;
    public float width;
    public Vertex corner;
    public Vertex corner1;
    public Vertex corner2;
    public Vertex corner3;

    private ArrayList<Vertex> vertices;

    /*****************
     * Constructor
     * ***************/
    public Ceiling(String key, Vertex c, float h, float w, float d) {
        textureKey = key;
        corner = c.clone();
        height = h;
        width = w;
        depth = d;
        setCorners();
        setVertices();
    }

    /*************
     * Getters
     * **********/

    @Override
    public float getDistFactor() {
        return ((float) Math.sqrt(height) + (float) Math.sqrt(width) + (float) Math.sqrt(depth)) / 2;
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }


    @Override
    public void draw(GL2 gl) {
        if (texture == null)
            texture = TextureFactory.create(textureKey, gl);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        texture.bind(gl);

        gl.glBegin(GL2.GL_QUADS);

        float x = corner.getX();
        float y = corner.getY();
        float z = corner.getZ();

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x, y, z + depth);
        gl.glTexCoord2f(1f, 1.0f);
        gl.glVertex3f((x + width), y, z + depth);
        gl.glTexCoord2f(1.0f, 0f);
        gl.glVertex3f((x + width), y, z);

        gl.glEnd();
    }


    /**
     * setCorners
     */
    public void setCorners() {
        corner1 = new Vertex(corner.getX(), corner.getY() + height, corner.getZ());
        corner2 = new Vertex(corner.getX() + width,
                corner.getY() + height, corner.getZ() + depth);
        corner3 = new Vertex(corner.getX() + width, corner.getY(), corner.getZ() + depth);
    }


    /**
     * setVertices
     */
    public void setVertices() {
        vertices = new ArrayList<>();
        vertices.add(corner);
        vertices.add(corner1);
        vertices.add(corner2);
        vertices.add(corner3);
    }

}