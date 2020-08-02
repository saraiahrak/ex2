/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

import Utils.Reader;
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

    public Level2(GL2 gl) {
        createSpace();
        createObjects();
        createModels(gl);
    }

    /**
     * constructor
     */
    public Level2() { }

    public static void createSpace() {

        Garden garden = new Garden();
        addDrawable(garden);
        // collision!!!

        Palace palace = new Palace();
        addDrawable(palace);
        addCollidable(palace);

/*        MarketPlace market = new MarketPlace();
        Cave cave = new Cave();

        addDrawable(market);
        addDrawable(cave);

        addCollidable(market);
        addCollidable(cave);*/
    }


    /**
     * createObjects
     */
    public static void createObjects() {

        Box box = new Box("wood", new Vertex(5.5f, 0f, 13f), 1, 1, 1);
        Box box1 = new Box("LightWood", new Vertex(1f, 0f, 6f), 1, 0.5f, 1);
        Box box2 = new Box("washedWood", new Vertex(4.5f, 0f, 11f), 1, 1, 1);


        addDrawable(box);
        addDrawable(box1);
        addDrawable(box2);

    }


    /**
     * createModels
     */
    public static void createModels(GL2 gl2) {

        OBJLoader carpet = new OBJLoader("models/carpet/PersianCarpet.obj", gl2);
        addDrawable(carpet.getModel());
        carpet.getModel().translate(15f, 5f, 15f);
        carpet.getModel().scale(0.014f, 0.014f, 0.014f);

    }


    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param c - collidable
     */
    private void addCollidable(CollisionObject c) {
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
     * addDrawable
     * Add drawable to list of drawable objects
     *
     * @param d - drawable
     */
    private static void addDrawable(Drawable d) {
        World.drawables.add(d);
    }

}
