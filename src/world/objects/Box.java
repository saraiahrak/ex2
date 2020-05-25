/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package world.objects;

import Math.*;
import com.jogamp.opengl.util.texture.Texture;
import design.TextureFactory;
import world.Drawable;
import javax.media.opengl.GL2;

/*************
 * Class Box
 * ***********/
public class Box implements Drawable {

    private String textureKey;
    private Texture texture;
    private Vertex bottomLeft;
    public Wall frontWall;
    public Wall backWall;
    public Wall leftWall;
    public Wall rightWall;

    private float depth = 1;
    private float height = 1;
    private float width = 1;

    public Box(String key, Vertex left, float d, float h, float w) {
        textureKey = key;
        bottomLeft = left.clone();
        depth = d;
        height = h;
        width = w;

        float x = bottomLeft.getX();
        float y = bottomLeft.getY();
        float z = bottomLeft.getZ();

        // collision objects
        frontWall = new Wall("box", new Vertex(x,y,z),height, width, 0);
        rightWall = new Wall("box", new Vertex(x,y,z),height, 0, width);
        leftWall = new Wall("box", new Vertex(x,y,z),height, 0, width);
        backWall = new Wall("box", new Vertex(x,y,z),height, width, 0);
    }

    @Override
    public void draw(GL2 gl) {
        if (texture == null)
            texture = TextureFactory.create(textureKey, gl);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        texture.bind(gl);

        gl.glBegin(GL2.GL_QUADS);

        float x = bottomLeft.getX();
        float y = bottomLeft.getY();
        float z = bottomLeft.getZ();

        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x, y, z + depth);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(x + width, y + height, z + depth);

        // Back Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, y + height, z);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3f(x + width, y, z);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(x + width, y + height, z);

        // Top Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, y + height, z);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(x + width, y + height, z);

        // Bottom Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x, y, z + depth);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(x + width, y, z);

        // Left face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x, y, z + depth);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3f(x, y, z);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(x, y + height, z);

        // Right Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glTexCoord2f(0f, 1.0f);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3f(x + width, y, z);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(x + width, y + height, z);

        gl.glEnd();
    }
}
