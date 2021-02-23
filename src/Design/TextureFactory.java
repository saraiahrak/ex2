/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Design;

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

    /*************
     * Constructor
     * ***********/
    public TextureFactory() { }


    /**
     * create
     * Create texture to objects
     *
     * @param texture name
     * @param gl - Gl2
     * @return texture if success, otherwise exception
     */
    public static Texture create(String texture, GL2 gl) {
        Texture t;
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try {
            String filename = "resources/textures/" + texture + ".jpg"; // the FileName to open
            t = TextureIO.newTexture(new File(filename), true);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}