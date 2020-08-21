/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Models;

import View.CoordinateSystem;
import World.Drawable;
import World.Space.World;
import javax.media.opengl.GL2;
import java.util.ArrayList;

/*************
 * Class Model
 * ***********/
public class Model implements Drawable {

    private CoordinateSystem coordinates;
    private ArrayList<ObjData> dataList;

    /**
     * constructor
     */
    public Model(ArrayList<ObjData> list) {
        setDataList(list);
        coordinates = World.player.coordinates;
    }


    /**
     * setDataList
     */
    private void setDataList(ArrayList<ObjData> list) {
        this.dataList = list;
    }


    /**
     * scale
     * scale over the x,y,z axis
     *
     * @param x scale value
     * @param y scale value
     * @param z scale value
     */
    public void scale(float x, float y, float z) {
        for (ObjData data: dataList) {
            data.scale(x, y, z);
        }
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
        for (ObjData data: dataList) {
            data.translate(x, y, z);
        }
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
        for (ObjData data: dataList) {
            data.rotate(angle, x, y, z);
        }
    }


    /**
     * setMotion
     * move the OBJ over the x,y,z axis according to the given parameters
     *
     * @param xStep value
     * @param yStep value
     * @param zStep value
     */
    public void setMotion(float xStep, float yStep, float zStep) {
        for (ObjData data: dataList) {
            data.motion(xStep, yStep, zStep);
        }
    }


    @Override
    public void draw(GL2 gl) {
        for (ObjData data : dataList) {
            if (data.getPath().contains("carpet") && coordinates.onFly) {
                data.drawModelThatFollowsPlayer(gl);
                continue;
            }
            data.draw(gl);
        }
    }
}