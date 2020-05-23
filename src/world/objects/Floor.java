package world.objects;

import com.jogamp.opengl.util.texture.Texture;
import design.TextureFactory;
import world.Drawable;

import javax.media.opengl.GL2;

public class Floor implements Drawable {

    private String textureKey;
    private Texture texture;

    public Floor(String key) {
        textureKey = key;
    }

    @Override
    public void draw(GL2 gl) {
        if (texture == null)
            texture = TextureFactory.create(textureKey, gl);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        texture.bind(gl);

        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-5.5f, -2f, -13.0f);
        gl.glTexCoord2f(0f, 7.0f);
        gl.glVertex3f(-5.5f, -2f, 1.0f);
        gl.glTexCoord2f(7f, 7.0f);
        gl.glVertex3f(5.5f, -2f, 1.0f);
        gl.glTexCoord2f(7.0f, 0f);
        gl.glVertex3f(5.5f, -2f, -13.0f);

        gl.glEnd();

    }
}
