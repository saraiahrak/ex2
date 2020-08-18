/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View.Text;

import com.jogamp.opengl.util.awt.TextRenderer;
import World.Space.World;
import java.awt.*;

/*************
 * Class LevelText
 * ***********/
public class LevelText {

    TextRenderer textRenderer;
    String level;

    /*************
     * Constructor
     * ***********/
    public LevelText() {
        textRenderer = new TextRenderer(new Font("david", Font.PLAIN, 30));
    }


    /**
     * display
     * Show the text on the screen
     */
    public void display() {
        if (World.firstLevel) {
            level = "level1";
        } else {
            level = "level2";
        }
        textRenderer.beginRendering(1000, 600);
        textRenderer.setColor(1.0f, 1f, 1f, 1f);
        textRenderer.draw(level, 800, 560);
        textRenderer.endRendering();
    }
}