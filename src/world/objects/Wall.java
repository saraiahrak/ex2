package world.objects;

import com.jogamp.opengl.util.texture.Texture;
import design.TextureFactory;
import world.Collidable;
import world.Drawable;
import Math.*;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class Wall implements Drawable {

    private String textureKey;
    private Texture texture = null;
    private float height;
    private float depth;
    private float width;
    private Vertex corner;



    public Wall(String key, Vertex c, float h, float w, float d) {
        textureKey = key;
        corner = c.clone();
        height = h;
        width = w;
        depth = d;
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
        gl.glTexCoord2f(0f, 4.0f);
        gl.glVertex3f(x, y + height, z);
        gl.glTexCoord2f(4f, 4f);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glTexCoord2f(4.0f, 0.0f);
        gl.glVertex3f(x + width, y, z + depth);

        gl.glEnd();

    }
}
