package World;

import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*****************
 * Class World
 * ***************/
public class World extends KeyAdapter implements GLEventListener {
    public GLCanvas canvas;
    public Frame frame;
    public final Animator animator;
    private float rotateT = 0.0f;



    public World() {

        frame = new Frame("Graphics");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        animator = new Animator();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });


        canvas = new GLCanvas();
        animator.add(canvas);



    }


    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClearDepth(1.0f); // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST); // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL); // The Type Of Depth Testing To Do

        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        // keyboard
        if (drawable instanceof com.jogamp.newt.Window) {
            com.jogamp.newt.Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof Component) {
            Component comp = (Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void display(GLAutoDrawable drawable) {

        // Get the GL corresponding to the drawable we are animating
        GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);

        gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
        gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);

        gl.glBegin(GL2.GL_TRIANGLES);

        // Front
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);

        // Right Side Facing Front
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, -1.0f, -1.0f);

        // Left Side Facing Front
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, -1.0f, -1.0f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);

        // Bottom
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.1f, 0.1f, 0.1f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        gl.glVertex3f(0.0f, -1.0f, -1.0f);

        gl.glEnd();

        rotateT += 0.05f;

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {

        GL2 gl = drawable.getGL().getGL2();

        float h = (float) height / (float) width;

        gl.glMatrixMode(GL2.GL_PROJECTION);

        gl.glLoadIdentity();

        if (h < 1)
            gl.glFrustum(-1.0f, 1.0f, -h, h, 1.0f, 60.0f);
        else {
            h = 1.0f / h;
            gl.glFrustum(-h, h, -1.0f, 1.0f, 1.0f, 60.0f);
        }

        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();
        if (KeyEvent.VK_ESCAPE == kc) {
            System.exit(0);
        }
    }


    public void show() {

        frame.setVisible(true);
        animator.start();
//
//        frame.pack();
//        frame.setVisible(true);
    }

}
