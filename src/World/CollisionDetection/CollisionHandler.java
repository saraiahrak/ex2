/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;

/****************
 * Interface Collision Handler
 * **************/
public interface CollisionHandler {

    /**
     * handle
     * Checks if there is a collision between two objects
     *
     * @param c1 first object
     * @return c2 second object
     */
    boolean handle(Collidable c1, Collidable c2);

}
