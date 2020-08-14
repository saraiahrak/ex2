package View;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

public class Life {

    int counter;
    TextRenderer textRenderer;

    public Life() {
        counter = 3;
        textRenderer = new TextRenderer(new Font("david", Font.PLAIN, 80));
    }

    public void display() {
        textRenderer.beginRendering(1000, 600);
        textRenderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        textRenderer.draw("Life:" + counter, 10, 10);
        textRenderer.endRendering();
    }

    public void reduceLife() {
        counter -= 1;
    }


    public void addLife(){
        this.counter += 1;
    }

}