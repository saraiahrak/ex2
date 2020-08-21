/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/
package World.Objects;

import Math.Vertex;
import World.CollisionDetection.Collidable;
import World.Drawable;
import java.util.ArrayList;

/*************
 * Interface Polygon
 * ***********/
public interface Polygon extends Collidable, Drawable {

    /**
     * getVertices
     */
    ArrayList<Vertex> getVertices();
    /**
     * getDistFactor
     */
    float getDistFactor();

}