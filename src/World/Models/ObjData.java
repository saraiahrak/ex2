/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Models;

import World.Space.World;
import com.jogamp.opengl.util.texture.Texture;
import World.Drawable;
import javax.media.opengl.GL2;

/*************
 * Class ObjData
 * ***********/
public class ObjData implements Drawable {

    private String path;
    private String fly;
    public boolean wasCollision;
    private Texture texture;
    private int list;
    private Material material;
    private float[] scale = {1,1,1};
    private float[] translate = {0,0,0};
    private float[] rotate = {0,0,0,0};
    private float[] motion = {0,0,0};

    /*****************
     * Constructors
     * ***************/

    public ObjData(String modelPath) {
        path = modelPath;
        fly = null;
        wasCollision = false;
    }

    public ObjData() {
        path = null;
        fly = null;
        wasCollision = false;
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
    public void setPath(String name) { this.path = name; }


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
     * motion
     * move the OBJ over the x,y,z axis according to the given parameters
     *
     * @param x value
     * @param y value
     * @param z value
     */
    public void motion(float x, float y, float z) {
        motion[0] = x;
        motion[1] = y;
        motion[2] = z;
    }


    @Override
    public void draw(GL2 gl) {

        gl.glPushMatrix();
        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        // models movement
        if (this.wasCollision) {
            this.wasCollision = false;
            for (int i = 0; i < 3; i++) {
                this.motion[i] *= -1;
            }
        }
        this.translate(this.motion[0], this.motion[1], this.motion[2]);
        gl.glTranslatef(translate[0], translate[1], translate[2]);

        gl.glScalef(scale[0],scale[1],scale[2]);
        gl.glRotatef(rotate[0], rotate[1], rotate[2], rotate[3]);

        if (this.path.contains("coin")) {
            gl.glRotatef(90, 1, 0, 0);
            rotateCoin();
            gl.glRotatef(rotate[0], rotate[1], rotate[2], rotate[3]);
        }

        if (texture != null) {
            texture.bind(gl);
        }

        // causes the named display list to be executed
        gl.glCallList(list);
        gl.glPopMatrix();
    }


    /**
     * rotateCoin
     * update the angle
     */
    private void rotateCoin() {
        if (rotate[0] >= 360) {
            rotate[0] = 1;
        } else {
            rotate[0] += rotate[0];
        }
    }


    /**
     * drawMotionModel
     *
     * @param gl - GL2
     */
    public void drawModelThatFollowsPlayer(GL2 gl) {
        gl.glPushMatrix();

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        gl.glTranslatef(World.player.getPosition().getX(),
                World.player.getPosition().getY() - 2.5f,
                World.player.getPosition().getZ() - 1f);

        gl.glScalef(scale[0],scale[1],scale[2]);

        gl.glRotatef(World.player.coordinates.getAngleX() * 0.6f, 1, 0, 0);
        gl.glRotatef(World.player.coordinates.getAngleY() * 1.06f, 0, 1, 0);

        if (texture != null) {
            texture.bind(gl);
        }
        // causes the named display list to be executed
        gl.glCallList(list);
        gl.glPopMatrix();
    }

}