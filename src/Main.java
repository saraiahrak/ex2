import World.World;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*****************
 * Class Main
 * ***************/


public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final World world = new World();

        world.canvas.addGLEventListener(world);
        world.frame.add(world.canvas, java.awt.BorderLayout.CENTER);
        world.frame.validate();

        world.show();
    }
}