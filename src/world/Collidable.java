/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package world;

/*************
 * Class Collidable
 * ***********/
public interface Collidable {

    /**
     * isVector
     * @return true if is a vector, otherwise false
     */
    public boolean isVector();

    /**
     * isWall
     * @return true if is a wall, otherwise false
     */
    public boolean isWall();
}
