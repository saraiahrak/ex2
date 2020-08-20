package Game;

import Design.TextureFactory;
import World.Drawable;
import World.Models.ObjData;
import World.Space.World;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.awt.*;

public class Menu implements Drawable {

    private TextRenderer renderer;
    private TextRenderer iRenderer;
    private GL2 gl;

    public Menu(GL2 gl2) {
        gl = gl2;
        renderer = new TextRenderer(new Font("Helvetica", Font.BOLD, 30));
        iRenderer = new TextRenderer(new Font("Helvetica", Font.PLAIN, 22));
    }

//    @Override
//    public void draw(GL2 gl) {
////        Render();
//        gl.glClearColor(1f, 1f, 1f, 0.5f);    // Black Background
//
//        gl.glEnable(GL2.GL_TEXTURE_2D);
//        Texture t = TextureFactory.create("smallManu", gl);
//        t.bind(gl);
//        gl.glBegin(GL2.GL_QUADS);
////
////        gl.glTexCoord2f(0,0);
////        gl.glVertex2f(-500f,-300f);
////        gl.glTexCoord2f(1,0);
////        gl.glVertex2f(500f,-300f);
////        gl.glTexCoord2f(1,1);
////        gl.glVertex2f(500f,300f);
////        gl.glTexCoord2f(0,1);
////        gl.glVertex2f(-500f,300f);
//
////        gl.glTexCoord2f(-1,-1);
//        gl.glVertex2f(-500f,-300f);
////        gl.glTexCoord2f(0,28);
//        gl.glVertex2f(500f,-300f);
////        gl.glTexCoord2f(2,28);
//        gl.glVertex2f(500f,300f);
////        gl.glTexCoord2f(2,0);
//        gl.glVertex2f(-500f,300f);
//
//        gl.glEnd();
//
//
////        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
////        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
////
////        gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
////        gl.glMatrixMode(GL2.GL_PROJECTION);
////        gl.glOrtho(0, 1000, 0, 600, -1, 1);
////        gl.glMatrixMode(GL2.GL_MODELVIEW);
//
////        Texture texture = TextureFactory.create("floor", gl);
////        texture.bind(gl);
////
//////
////        gl.glDisable(GL.GL_DEPTH_TEST);
////        gl.glEnable();
////        gl.glBegin(GL2.GL_QUADS);
////
////        gl.glTexCoord2f(0.0f, 0.0f);
////        gl.glVertex2f(0, 0);
////        gl.glTexCoord2f(0.0f, 1.0f);
////        gl.glVertex2f(0, 600f);
////        gl.glTexCoord2f(1.0f, 1.0f);
////        gl.glVertex2f(1000, 600);
////        gl.glTexCoord2f(1.0f, 0.0f);
////        gl.glVertex2f(1000, 0);
////
////
////        gl.glEnd();
////
////
////        int map;
////        gl.glGenTextures();
////        gl.glEnable(GL.GL_TEXTURE_2D);
////        Texture texture = TextureFactory.create("menuBackground", gl);
////        texture.bind(gl);
////
////        gl.glBegin(GL2.GL_QUADS);
////
////
////
////        gl.glTexCoord2f(0, 0);
////        gl.glVertex2f(0, 0);
////        gl.glTexCoord2f(0, 1);
////        gl.glVertex2f(0, 600);
////        gl.glTexCoord2f(1, 1);
////        gl.glVertex2f(1000, 0);
////        gl.glTexCoord2f(1, 0);
////        gl.glVertex2f(1000, 600);
////
////        gl.glDisable(GL.GL_TEXTURE_2D);
////        gl.glEnd();
////
//
//        renderer.beginRendering(1000, 600);
//        renderer.setColor(1f, 1f, 1f, 0.5f);
//        renderer.draw("WELCOME TO ALADDIN!", 320, 550);
//        renderer.draw("(ENTER) Start New Game", 20, 450);
//        renderer.draw("(F1) Show Instructions", 20, 350);
//        renderer.draw("(ESC) Quit", 20, 250);
//        renderer.endRendering();
//
//
//    }


    @Override
    public void draw(GL2 gl) {


        if (World.showMenu && World.showInstructions) {
            renderInstructions();
        }
        if (World.showMenu && !World.showInstructions) {
            renderMenu();
        }


//        gl.glClearColor(1f, 1f, 1f, 0.5f);    // Black Background
//
//        gl.glEnable(GL2.GL_TEXTURE_2D);
//
//        gl.glBegin(GL2.GL_QUADS);
//        gl.glVertex2f(-500f,-300f);
//        gl.glVertex2f(500f,-300f);
//        gl.glVertex2f(500f,300f);
//        gl.glVertex2f(-500f,300f);
//
//        gl.glEnd();
//
//        renderer.beginRendering(1000, 600);
//        renderer.setColor(1f, 1f, 1f, 0.5f);
//        renderer.draw("WELCOME TO ALADDIN!", 320, 550);
//        renderer.draw("(ENTER) Start New Game", 20, 450);
//        renderer.draw("(F1) Show Instructions", 20, 350);
//        renderer.draw("(ESC) Quit", 20, 250);
//        renderer.endRendering();
//
    }

    private void renderMenu() {
//        gl.glDisable(GL2.GL_COLOR_MATERIAL);
        gl.glDisable(GL.GL_TEXTURE);
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(1f, 1f, 1f, 0f);    // Black Background
//        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-500f, -300f);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2f(500f, -300f);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2f(500f, 300f);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2f(-500f, 300f);
        gl.glColor3f(1f, 1f, 1f);

        gl.glEnd();

        renderer.beginRendering(1000, 600);
        renderer.setColor(1f, 1f, 1f, 0.5f);
        renderer.draw("WELCOME TO ALADDIN!", 320, 550);
        renderer.draw("(ENTER)", 20, 450);
        renderer.draw("Start New Game", 250, 450);
        renderer.draw("(F1)", 20, 350);
        renderer.draw("Main Menu", 250, 350);
        renderer.draw("(H)", 20, 250);
        renderer.draw("Instructions", 250, 250);
        renderer.draw("(ESC)", 20, 150);
        renderer.draw("Quit", 250, 150);
        renderer.endRendering();
    }

    private void renderInstructions() {
        gl.glDisable(GL.GL_TEXTURE);
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(1f, 1f, 1f, 0f);    // Black Background
//        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-500f, -300f);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2f(500f, -300f);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2f(500f, 300f);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2f(-500f, 300f);
        gl.glColor3f(1f, 1f, 1f);

        gl.glEnd();

        renderer.beginRendering(1000, 600);
        renderer.setColor(1f, 1f, 1f, 0.5f);
        renderer.draw("KEYS", 730, 560);
        renderer.draw("INSTRUCTIONS", 170, 560);
        renderer.endRendering();

        iRenderer.beginRendering(1000, 600);
        iRenderer.setColor(1f, 1f, 1f, 0.5f);
        iRenderer.draw("Jasmine was taken by Jaffar and needs you to rescue her!", 20, 500);
        iRenderer.draw("Level 1 - collect as many coins as you can and avoid the cops.", 20, 470);
        iRenderer.draw("Go through the cave wall and go through the maze to find ", 20, 440);
        iRenderer.draw("the palace Jasmine is held in.", 20, 410);
        iRenderer.draw("Level 2 - find the carpet and fly through the palace window.", 20, 380);
        iRenderer.draw("Find Jasmine and rescue her!", 20, 350);

        iRenderer.draw("(W)", 670, 500);
        iRenderer.draw("walk forward", 730, 500);

        iRenderer.draw("(S)", 670, 470);
        iRenderer.draw("walk backwards", 730, 470);

        iRenderer.draw("(D)", 670, 440);
        iRenderer.draw("walk right", 730, 440);

        iRenderer.draw("(A)", 670, 410);
        iRenderer.draw("walk left", 730, 410);

        iRenderer.draw("(Q)", 670, 380);
        iRenderer.draw("go up (on carpet)", 730, 380);

        iRenderer.draw("(E)", 670, 350);
        iRenderer.draw("go down (on carpet)", 730, 350);

        iRenderer.draw("(W)", 670, 320);
        iRenderer.draw("walk forward", 730, 320);

        iRenderer.draw("(J)", 670, 290);
        iRenderer.draw("look left", 730, 290);

        iRenderer.draw("(L)", 670, 260);
        iRenderer.draw("look right", 730, 260);

        iRenderer.endRendering();
    }


    public void Render() {
        Texture t = TextureFactory.create("menuBackground", gl);
        gl.glEnable(gl.GL_TEXTURE_2D);
//        gl.glBindTexture(gl.GL_TEXTURE_2D, texture.Id);

        t.bind(gl);
        double height = 600;
        double width = 1000;

        double x = 0;
        double y = 0;
        double z = 0;

        float topUV = 0;
        float bottomUV = 1;
        float leftUV = 0;
        float rightUV = 1;

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glBegin(gl.GL_TRIANGLES);

        gl.glTexCoord2d(leftUV, topUV);
        gl.glVertex3d(x - width, y + height, z);
        gl.glTexCoord2d(rightUV, topUV);
        gl.glVertex3d(x + width, y + height, z);
        gl.glTexCoord2d(leftUV, bottomUV);
        gl.glVertex3d(x - width, y - height, z);

        gl.glTexCoord2d(rightUV, topUV);
        gl.glVertex3d(x + width, y + height, z);
        gl.glTexCoord2d(rightUV, bottomUV);
        gl.glVertex3d(x + width, y - height, z);
        gl.glTexCoord2d(leftUV, bottomUV);
        gl.glVertex3d(x - width, y - height, z);

        gl.glEnd();

    }

}
