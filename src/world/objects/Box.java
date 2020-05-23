package world.objects;

import Math.*;
import com.jogamp.opengl.util.texture.Texture;
import design.TextureFactory;
import world.Drawable;

import javax.media.opengl.GL2;

public class Box implements Drawable {

    private String textureKey;
    private Texture texture;
    private Vertex bottomLeft;

    private float depth = 1;
    private float height = 1;
    private float width = 1;

    public Box(String key, Vertex left, float d, float h, float w) {
        textureKey = key;
        bottomLeft = left.clone();
        depth = d;
        height = h;
        width = w;
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
