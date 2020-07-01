/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Objects;

import World.CollisionDetection.CollisionObject;
import com.jogamp.opengl.util.texture.Texture;
import Design.TextureFactory;
import World.Drawable;
import Math.*;
import javax.media.opengl.GL2;

/*************
 * Class Wall
 * ***********/
public class Wall extends CollisionObject implements Drawable {

    public String textureKey;
    public Texture texture = null;
    public float height;
    public float depth;
    public float width;
    public Vertex corner;
    public Vertex corner1;
    public Vertex corner2;
    public Vertex corner3;


    /*****************
     * Constructor
     * ***************/
    public Wall(String key, Vertex c, float h, float w, float d) {
        textureKey = key;
        corner = c.clone();
        height = h;
        width = w;
        depth = d;
        setCorners();
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
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(x, y + height, z);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(x + width, y, z + depth);

        gl.glEnd();
    }

    public void setCorners() {
        corner1 = new Vertex(corner.getX(), corner.getY() + height, corner.getZ());
        corner2 = new Vertex(corner.getX() + width,
                corner.getY() + height, corner.getZ() + depth);
        corner3 = new Vertex(corner.getX() + width, corner.getY(), corner.getZ() + depth);
    }

    @Override
    public String getTextureKey() {
        return textureKey;
    }

    @Override
    public boolean isWall() {
        return true;
    }

}