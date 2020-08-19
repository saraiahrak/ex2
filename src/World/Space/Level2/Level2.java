/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level2;

import Game.Level;
import Math.Vertex;
import World.CollisionDetection.Collidable;
import World.Drawable;
import World.Models.OBJLoader;
import World.Objects.Box;
import World.Objects.CarpetObject;
import World.Objects.Wall;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class LevelText 2
 * ***********/
public class Level2 implements Level {

    private ArrayList<Drawable> drawables;
    private ArrayList<Collidable> collidables;

    /**
     * constructor
     */
    public Level2(GL2 gl) {
        init();

        createObjects();
        createModels(gl);
        createSpace();
    }


    private void init() {
        drawables = new ArrayList<>();
        collidables = new ArrayList<>();
    }


    @Override
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }


    public void createSpace() {

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
    public void createObjects() {

        Wall jasmine = new Wall("jasmine", new Vertex(240, 32, -16.1f), 12, 12, 0);
        Box cage = new Box("cage", new Vertex(240f, 32f, -16f), 12, 12, 12);

        addDrawable(jasmine);
        addDrawable(cage);

        addCollidable(jasmine);
        collidables.addAll(cage.getPolygons());
    }


    /**
     * createModels
     */
    public void createModels(GL2 gl2) {

        OBJLoader carpet = new OBJLoader("models/carpet/PersianCarpet.obj", gl2);
        CarpetObject carpetObject = new CarpetObject(carpet.getModel(), new Vertex(42f, 0.5f, 62f));
        carpetObject.translate(42f, 0.5f, 62f);
        carpetObject.scale(0.07f, 0.07f, 0.09f);
        addDrawable(carpetObject);

//        OBJLoader carpet = new OBJLoader("models/carpet/PersianCarpet.obj", gl2);
//        addDrawable(carpet.getModel());
//        carpet.getModel().translate(42f, 0.5f, 62f);
//        //carpet.getModel().translate(coordinates.getOrigin().getX(),0.5f,
//        //       coordinates.getOrigin().getZ() + 5f);
//        carpet.getModel().scale(0.07f, 0.07f, 0.09f);
    }


    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param c - collidable
     */
    private void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param palace - collidables
     */
    private void addCollidable(Palace palace) {
        List<Wall> internalWalls = palace.getInternalWalls();
        List<Wall> externalWalls = palace.getExternalWalls();

        collidables.addAll(internalWalls);
        collidables.addAll(externalWalls);

//        for (Wall wall: internalWalls) {
//            World.collidables.add(wall);
////        }
//
//        for (Wall wall: externalWalls) {
//            World.collidables.add(wall);
//        }
    }


    /**
     * addCollidable
     * Add collidable to list of collision objects
     *
     * @param garden - collidables
     */
    private void addCollidable(Garden garden) {

        collidables.add(garden.back);
        collidables.add(garden.left);
        collidables.add(garden.right);
//        //World.collidables.add(garden.palaceEntry);
//        World.collidables.add(garden.back);
//        World.collidables.add(garden.left);
//        World.collidables.add(garden.right);
//        //World.collidables.add(garden.floor);
//        //World.collidables.add(garden.ceiling);
//
//    //    List<Box> boxes = garden.getObstacles();
//   //     World.collidables.addAll(boxes);
//
////        List<Box> boxes = garden.getObstacles();
////        World.collidables.addAll(boxes);
    }


    /**
     * addDrawable
     * Add drawable to list of drawable objects
     *
     * @param d - drawable
     */
    private void addDrawable(Drawable d) {drawables.add(d);
    }

}
