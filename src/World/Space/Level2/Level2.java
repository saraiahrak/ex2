/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

import Utils.Reader;
import View.CoordinateSystem;
import World.CollisionDetection.CollisionObject;
import World.Drawable;
import World.Models.OBJLoader;
import World.Objects.Box;
import World.Objects.Wall;
import World.Space.Level1.Cave;
import World.Space.World;
import Math.*;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Level 2
 * ***********/
public class Level2 {

    /**
     * constructor
     */
    public Level2(GL2 gl) {
        createObjects();
        createModels(gl);
        createSpace();
    }

    public static void createSpace() {

        Garden garden = new Garden();
        Palace palace = new Palace();

        addDrawable(garden);
        addDrawable(palace);

        addCollidable(garden);
        addCollidable(palace);
    }


    /**
     * createObjects
     */
    public static void createObjects() {

        Wall jasmine = new Wall("jasmine", new Vertex(240, 32, -16.1f),12, 12, 0);
        Box cage = new Box("cage", new Vertex(240f, 32f, -16f), 12, 12, 12);

        addDrawable(jasmine);
        addDrawable(cage);

        addCollidable(jasmine);
        addCollidable(cage);
    }


    /**
     * createModels
     */
    public static void createModels(GL2 gl2) {
        OBJLoader carpet = new OBJLoader("models/carpet/PersianCarpet.obj", gl2);
        addDrawable(carpet.getModel());
        carpet.getModel().translate(42f, 0.5f, 62f);
        //carpet.getModel().translate(coordinates.getOrigin().getX(),0.5f,
        //       coordinates.getOrigin().getZ() + 5f);
        carpet.getModel().scale(0.07f, 0.07f, 0.09f);
    }


    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param c - collidable
     */
    private static void addCollidable(CollisionObject c) {
        World.collisionObjects.add(c);
    }

    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param palace - collidables
     */
    private static void addCollidable(Palace palace) {
        List<Wall> internalWalls = palace.getInternalWalls();
        for (Wall wall: internalWalls) {
            World.collisionObjects.add(wall);
        }

        List<Wall> externalWalls = palace.getExternalWalls();
        for (Wall wall: externalWalls) {
            World.collisionObjects.add(wall);
        }
    }


    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param garden - collidables
     */
    private static void addCollidable(Garden garden) {
        World.collisionObjects.add(garden.back);
        World.collisionObjects.add(garden.front);
        World.collisionObjects.add(garden.left);
        World.collisionObjects.add(garden.right);

        List<Box> boxes = garden.getObstacles();
        for (Box box : boxes) {
            World.collisionObjects.add(box);
        }
    }


    /**
     * addDrawable
     * Add drawable to list of drawable objects
     *
     * @param d - drawable
     */
    private static void addDrawable(Drawable d) {
        World.drawables.add(d);
    }

}
