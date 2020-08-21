/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package View.Text;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.*;

/*************
 * Class Coins
 * ***********/
public class Coins {

    public int counter;
    TextRenderer textRenderer;

    /*************
     * Constructor
     * ***********/
    public Coins() {
        counter = 0;
        textRenderer = new TextRenderer(new Font("david", Font.PLAIN, 30));
    }


    /**
     * display
     * Show the text on the screen
     */
    public void display() {
        textRenderer.beginRendering(1000, 600);
        textRenderer.setColor(1.0f, 1f, 1f, 1f);
        textRenderer.draw("Coins:" + counter, 460, 560);
        textRenderer.endRendering();
    }


    /**
     * reduceCoin
     * Pay for the carpet
     */
    public void reduceCoin() {
        counter -= 200;
    }


    /**
     * addCoin
     */
    public void addCoin(){
        this.counter += 10;
    }


    /**
     * setCoins
     * Set 200 coins when Moving to the next step without completing the task
     */
    public void setCoins(){
        this.counter = 200;
    }
}