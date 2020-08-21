/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Objects;

import Math.*;
import World.CollisionDetection.Collidable;
import World.Models.*;

/*************
 * Interface IObject
 * ***********/
public interface IObject extends Collidable {

    /**********
     * Getters
     * ********/

    Model getModel();

    Vertex getPosition();

    float getDepth();

    float getHeight();

    float getWidth();

    Box getBox();

    /**
     * rotate
     */
    void rotate(float angle, float x, float y, float z);

    /**
     * translate
     */
    void translate(float x, float y, float z);

    /**
     * scale
     */
    void scale(float x, float y, float z);

    /**
     * setMotion
     */
    void setMotion(float dx, float dy, float dz);

}