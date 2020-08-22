/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View.Text;

import World.Space.World;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.*;

/*************
 * Class Life
 * ***********/
public class Life {

    int counter;
    public static boolean reduced = false;
    TextRenderer textRenderer;

    /*************
     * Constructor
     * ***********/
    public Life() {
        counter = 3;
        textRenderer = new TextRenderer(new Font("david", Font.PLAIN, 30));
    }


    /**
     * display
     * Show the text on the screen
     */
    public void display() {
        textRenderer.beginRendering(1000, 600);
        textRenderer.setColor(1.0f, 1f, 1f, 1f);
        textRenderer.draw("Life:" + counter, 150, 560);
        textRenderer.endRendering();
    }


    /**
     * reduceLife
     */
    public void reduceLife() {
        counter -= 1;
        if (counter == 0) {
            // end game
            World.showMenu = true;
            World.showGameOver = true;
        }
    }

}