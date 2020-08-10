/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Models;

import View.CoordinateSystem;
import World.Space.World;
import com.jogamp.opengl.util.texture.Texture;
import World.Drawable;
import sun.plugin.services.WPlatformService;

import javax.media.opengl.GL2;

/*************
 * Class ObjData
 * ***********/
public class ObjData implements Drawable {

    private String path;
    private String fly = null;
    private Texture texture;
    private int list;
    private Material material;
    private float[] scale = {1,1,1};
    private float[] translate = {0,0,0};
    private float[] rotate = {0,0,0,0};
    private float[] movement = {0,0,0,0};

    /*****************
     * Constructor
     * ***************/

    public ObjData(String modelPath) {
        path = modelPath;
    }

    /*************
     * Getters
     * ***********/

    public String getPath() {
        return path;
    }

    public String getFly() {
        return fly;
    }

    /*************
     * Setters
     * ***********/

    public void setTexture(Texture t) { this.texture = t; }
    public void setList(int l){
        this.list = l;
    }
    public void setMaterial(Material m) { this.material = m; }


    /**
     * scale
     * scale over the x,y,z axis
     *
     * @param x scale value
     * @param y scale value
     * @param z scale value
     */
    public void scale(float x, float y, float z) {
        scale[0] *= x;
        scale[1] *= y;
        scale[2] *= z;
    }


    /**
     * translate
     * move the OBJ over the x,y,z axis according to the given parameters
     *
     * @param x value
     * @param y value
     * @param z value
     */
    public void translate(float x, float y, float z) {
        translate[0] += x;
        translate[1] += y;
        translate[2] += z;
    }


    /**
     * rotate
     * rotate the OBJ over the x,y,z axis according to the given parameters
     *
     * @param angle value
     * @param x value
     * @param y value
     * @param z value
     */
    public void rotate(float angle, float x, float y, float z) {
        rotate[0] += angle;
        rotate[1] += x;
        rotate[2] += y;
        rotate[3] += z;
    }


    /**
     * movement
     * rotate the OBJ over the x,y,z axis consecutively according to the given parameters
     *
     * @param angle value
     * @param x value
     * @param y value
     * @param z value
     */
    public void movement(float angle, float x, float y, float z) {
        movement[0] += angle;
        movement[1] += x;
        movement[2] += y;
        movement[3] += z;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        gl.glTranslatef(translate[0],translate[1],translate[2]);
        gl.glScalef(scale[0],scale[1],scale[2]);
        gl.glRotatef(rotate[0], rotate[1], rotate[2], rotate[3]);
        if (movement[0] != 0) {
            if (movement[0] >= 360) {
                movement[0] = 1;
            } else {
                movement[0] += movement[0];
            }
            gl.glRotatef(movement[0], movement[1], movement[2], movement[3]);
        }

        if (texture != null) {
            texture.bind(gl);
        }
        // causes the named display list to be executed
        gl.glCallList(list);
        gl.glPopMatrix();
    }


    /**
     * drawMotionModel
     *
     * @param gl - GL2
     */
    public void drawMotionModel(GL2 gl) {
        gl.glPushMatrix();

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        gl.glTranslatef(World.player.getPosition().getX(),
                World.player.getPosition().getY() - 2.5f,
                World.player.getPosition().getZ() - 2f);

        gl.glScalef(scale[0],scale[1],scale[2]);

        gl.glRotatef(World.player.coordinates.getAngleX(), 1, 0, 0);
        gl.glRotatef(World.player.coordinates.getAngleY(), 0, 1, 0);
        gl.glRotatef(World.player.coordinates.getAngleZ(), 0, 0, 1);

        if (texture != null) {
            texture.bind(gl);
        }
        // causes the named display list to be executed
        gl.glCallList(list);
        gl.glPopMatrix();
    }

}