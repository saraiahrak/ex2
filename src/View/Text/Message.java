/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View.Text;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.*;

/*************
 * Class Message
 * ***********/
public class Message {

    TextRenderer textRenderer;

    /**
     * Constructor
     */
    public Message() {
        textRenderer = new TextRenderer(new Font("david", Font.PLAIN, 40));
    }


    /**
     * display
     * Show the text on the screen
     */
    public void display() {
        textRenderer.beginRendering(1000, 600);
        textRenderer.setColor(1f, 0.6f, 0f, 1f);
        textRenderer.draw("Press 'B' to buy the carpet for 200$", 200, 500);
        textRenderer.endRendering();
    }

}