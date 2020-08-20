/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space.Level1;

import Game.Level;
import Utils.Reader;
import World.CollisionDetection.Collidable;
import World.Drawable;
import World.Models.OBJLoader;
import World.Objects.BenchObject;
import World.Objects.CoinObject;
import World.Objects.CopObject;
import World.Objects.LightObject;
import Math.*;
import javax.media.opengl.GL2;
import java.util.ArrayList;

/*************
 * Class LevelText 1
 * ***********/
public class Level1 implements Level {


    private ArrayList<Collidable> collidables;
    private ArrayList<Drawable> drawables;


    public Level1(GL2 gl) {
        init();
        createSpace();
        createModels(gl);
    }


    private void init() {
        collidables = new ArrayList<>();
        drawables = new ArrayList<>();
    }


    @Override
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }


    /**
     * createSpace
     */
    public void createSpace() {

        MarketPlace market = new MarketPlace();
        Cave cave = new Cave();

        addCollidables(market.getCollidables());
        addCollidables(cave.getCollidables());

        addDrawable(market);
        addDrawable(cave);
    }


    /**
     * createModels
     *
     * @param gl - GL2
     */
    public void createModels(GL2 gl) {
        // add street light
        createStreetLights(gl);
        // add benches
        createBenches(gl);
        // add coins
        createCoins(gl);
        // add cops
        createCops(gl);
    }


    /**
     * createStreetLights
     *
     * @param gl - GL2
     */
    private void createStreetLights(GL2 gl) {

        ArrayList<String> lines = Reader.readLines("resources/models/dualLight/Lights.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader singleLight =
                    new OBJLoader("models/dualLight/classic_dual_light.obj", gl);
            LightObject light = new LightObject(singleLight.getModel(), new Vertex(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]), Float.parseFloat(values[2])));
            light.translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]), Float.parseFloat(values[2]));
            light.scale(0.014f, 0.014f, 0.014f);
            addDrawable(light);
            addCollidable(light);
//        ArrayList<String> lines = Reader.readLines("resources/models/dualLight/Lights.txt");
//        for (String line : lines) {
//            String[] values = line.split(" ");
//
//            OBJLoader singleLight =
//                    new OBJLoader("models/dualLight/classic_dual_light.obj", gl);
//            addDrawable(singleLight.getModel());
//            singleLight.getModel().translate(Float.parseFloat(values[0]),
//                    Float.parseFloat(values[1]), Float.parseFloat(values[2]));
//            singleLight.getModel().scale(0.014f, 0.014f, 0.014f);
        }
    }


    /**
     * createBenches
     *
     * @param gl - GL2
     */
    private void createBenches(GL2 gl) {
        ArrayList<String> lines = Reader.readLines("resources/models/bench/Benches.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader bench = new OBJLoader("models/bench/classic_park_bench.obj", gl);
            BenchObject benchObject = new BenchObject(bench.getModel(), new Vertex(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]), Float.parseFloat(values[2])));
            benchObject.translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]), Float.parseFloat(values[2]));
            benchObject.scale(0.014f, 0.014f, 0.014f);
            benchObject.rotate(Float.parseFloat(values[3]), 0, 1, 0);
            addDrawable(benchObject);
            addCollidable(benchObject);
//
//        ArrayList<String> lines = Reader.readLines("resources/models/bench/Benches.txt");
//        for (String line : lines) {
//            String[] values = line.split(" ");
//
//            OBJLoader bench = new OBJLoader("models/bench/classic_park_bench.obj", gl);
//            addDrawable(bench.getModel());
//            bench.getModel().translate(Float.parseFloat(values[0]),
//                    Float.parseFloat(values[1]), Float.parseFloat(values[2]));
//            bench.getModel().scale(0.014f, 0.014f, 0.014f);
//            bench.getModel().rotate(Float.parseFloat(values[3]), 0, 1, 0);
        }
    }


    /**
     * createCoins
     *
     * @param gl - GL2
     */
    private void createCoins(GL2 gl) {
        ArrayList<String> lines = Reader.readLines("resources/models/coin/coins.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader coin = new OBJLoader("models/coin/coin.obj", gl);
            CoinObject coinObject = new CoinObject(coin.getModel(), new Vertex(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]), Float.parseFloat(values[2])));
            coinObject.translate(Float.parseFloat(values[0]),
                    Float.parseFloat(values[1]), Float.parseFloat(values[2]));
            coinObject.scale(0.2f, 0.2f, 0.2f);
            coinObject.rotate(0.01f, 0, 1, 0);
            addDrawable(coinObject);
            addCollidable(coinObject);


//        ArrayList<String> lines = Reader.readLines("resources/models/coin/coins.txt");
//        for (String line : lines) {
//            String[] values = line.split(" ");
//
//            OBJLoader coin = new OBJLoader("models/coin/coin.obj", gl);
//            addDrawable(coin.getModel());
//            coin.getModel().translate(Float.parseFloat(values[0]),
//                    Float.parseFloat(values[1]), Float.parseFloat(values[2]));
//            coin.getModel().scale(0.2f, 0.2f, 0.2f);
//            coin.getModel().rotate(0.01f, 0, 1, 0);
//        }
        }

    }



    /**
     * createCops
     *
     * @param gl - GL2
     */
    private void createCops (GL2 gl){

        ArrayList<String> lines = Reader.readLines("resources/models/cops/cops.txt");
        for (String line : lines) {
            String[] values = line.split(" ");

            OBJLoader cop = new OBJLoader("models/cops/Dusty_2.obj", gl);
            CopObject copObject = new CopObject(cop.getModel(), new Vertex(Float.parseFloat(values[0]), -2f,
                    Float.parseFloat(values[1])));
            copObject.setMotion(0.01f, 0f, 0f);
            copObject.translate(Float.parseFloat(values[0]), -2f, Float.parseFloat(values[1]));
            copObject.scale(0.0007f, 0.0007f, 0.0007f);
            addDrawable(copObject);
            addCollidable(copObject);
        }
//        ArrayList<String> lines = Reader.readLines("resources/models/cops/cops.txt");
//        for (String line : lines) {
//            String[] values = line.split(" ");
//
//            OBJLoader cop = new OBJLoader("models/cops/Dusty_2.obj", gl);
//            addDrawable(cop.getModel());
//            cop.getModel().translate(Float.parseFloat(values[0]),
//                    -1.85f, Float.parseFloat(values[1]));
//            cop.getModel().scale(0.0007f, 0.0007f, 0.0007f);
//            cop.getModel().setMotion(0.01f, 0f, 0f);
//        }
    }


    private void addDrawable (Drawable d){
        drawables.add(d);
    }

    private void addCollidable (Collidable c){
        collidables.add(c);
    }

    private void addCollidables (ArrayList < Collidable > c) {
        collidables.addAll(c);
    }

//
//        /**
//         * addCollidable
//         * Add collidable to list of collision objects
//         *
//         * @param marketPlace - collidables
//         */
//        private static void addCollidable (MarketPlace marketPlace){
//            World.collidables.addAll(marketPlace.getCollidables());
//        }
//
//        /**
//         * addCollidable
//         * Add collidable to list of collision objects
//         *
//         * @param cave - collidables
//         */
//        private static void addCollidable (Cave cave){
//            World.collidables.addAll(cave.getCollidables());
////        World.collidables.add(cave.genie);
////        List<Wall> walls = cave.getMaze();
////        World.collidables.addAll(walls);
//        }
//
//
//        /**
//         * addDrawable
//         * Add drawable to list of drawable objects
//         *
//         * @param d - drawable
//         */
//        private static void addDrawable (Drawable d){
//            World.drawables.add(d);
//        }

}
