/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Objects;

import Math.*;
import World.CollisionDetection.CollisionObject;
import com.jogamp.opengl.util.texture.Texture;
import Design.TextureFactory;
import World.Drawable;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Box
 * ***********/
public class Box extends CollisionObject implements Drawable {

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

        //float x = bottomLeft.getX();
        //float y = bottomLeft.getY();
        //float z = bottomLeft.getZ();
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


    /**
     * createBoxes
     * Create boxes from string of coordinates
     *
     * @param lines string of coordinates
     * @param texture name
     * @return boxes array
     */

    public static ArrayList<Box> createBoxes(List<String> lines, String texture) {
        ArrayList<Box> boxesList = new ArrayList<>();

        for (String line : lines) {
            String[] coordinate = line.split(" ");
            float x = Float.parseFloat(coordinate[0]);
            float y = Float.parseFloat(coordinate[1]);
            float z = Float.parseFloat(coordinate[2]);
            float h = Float.parseFloat(coordinate[3]);
            float w = Float.parseFloat(coordinate[4]);
            float d = Float.parseFloat(coordinate[5]);

            boxesList.add(new Box(texture, new Vertex(x, y, z), h, w, d));
        }
        return boxesList;
    }

    @Override
    public boolean isBox() {
        return true;
    }
}
