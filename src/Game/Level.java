/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Game;

import World.CollisionDetection.Collidable;
import World.Drawable;
import java.util.ArrayList;

/*************
 * Interface Level
 * ***********/
public interface Level {

    /**
     * getCollidables
     *
     * @return collidables list
     */
    ArrayList<Collidable> getCollidables();

    /**
     * getDrawables
     *
     * @return drawables list
     */
    ArrayList<Drawable> getDrawables();

}
