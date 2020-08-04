/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.CollisionDetection;

/*************
 * Class Collision object
 * ***********/
public class CollisionObject implements Collidable {

    public boolean isVector() {
        return false;
    }

    public boolean isWall() {
        return false;
    }

    public boolean isBox() {
        return false;
    }

    public String getTextureKey() {return null; }
}