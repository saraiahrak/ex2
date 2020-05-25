/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package design;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

/*************
 * Class Texture Factory
 * ***********/

public class TextureFactory {

    private TextureFactory factory = null;

    public TextureFactory() {
    }

    public TextureFactory getInstance() {
        if (factory == null) {
            factory = new TextureFactory();
        }
        return factory;
    }

    public static Texture create(String texture, GL2 gl) {
        Texture t = null;
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try {
            String filename = "resources/textures/" + texture + ".png"; // the FileName to open
            t = TextureIO.newTexture(new File(filename), true);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
