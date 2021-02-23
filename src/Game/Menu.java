/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Game;

import World.Models.ObjData;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import javax.media.opengl.GL2;
import java.io.IOException;
import java.io.InputStream;

/*************
 * Class Menu
 * ***********/
public class Menu {

    private GL2 gl;
    public ObjData data = new ObjData();

    /*****************
     * Constructor
     * ***************/
    public Menu(GL2 gl, String path) {
        this.gl = gl;
        setList();
        setTexture(path);
    }


    /**
     * setList
     * Set menu vertex list
     */
    public void setList() {
        // size
        float width = 160;
        float height = 96;

        // position
        int list = gl.glGenLists(1);
        gl.glNewList(list,GL2.GL_COMPILE);
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(0f,0f);
        gl.glVertex3f(-width,-height,200);

        gl.glTexCoord2f(0f,1);
        gl.glVertex3f(-width,height,200);

        gl.glTexCoord2f(1,1);
        gl.glVertex3f(width,height,200);

        gl.glTexCoord2f(1,0f);
        gl.glVertex3f(width,-height,200);

        gl.glEnd();
        gl.glEndList();
        data.setList(list);
    }


    /**
     * setTexture
     *
     * @param path - texture path
     */
    private void setTexture(String path) {
        try {
            data.setPath(path);
            InputStream myis =
                    ClassLoader.getSystemClassLoader().getResourceAsStream(data.getPath());
            Texture texture = TextureIO.newTexture(myis, true,"jpg");
            data.setTexture(texture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}