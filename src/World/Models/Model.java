/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Models;

import World.Drawable;

import javax.media.opengl.GL2;
import java.util.ArrayList;

/*************
 * Class Model
 * ***********/
public class Model implements Drawable {

    private ArrayList<ObjData> dataList;

    public Model(ArrayList<ObjData> list) {
        setDataList(list);
    }

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
     * movement
     * rotate the OBJ over the x,y,z axis consecutively according to the given parameters
     *
     * @param angle value
     * @param x value
     * @param y value
     * @param z value
     */
    public void movement(float angle, float x, float y, float z) {
        for (ObjData data: dataList) {
            data.movement(angle, x, y, z);
        }
    }


    @Override
    public void draw(GL2 gl) {
        for (ObjData data: dataList) {
            data.draw(gl);
        }
    }
}