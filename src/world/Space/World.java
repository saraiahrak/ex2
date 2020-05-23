package world.Space;

import java.awt.Frame;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;
import world.Drawable;
import world.Player;
import world.objects.*;
import Math.*;

public class World extends KeyAdapter implements GLEventListener, Drawable {

    private Player player = new Player();
    private ArrayList<Drawable> drawables;

    private static GLU glu = new GLU();
    private static GLCanvas canvas = new GLCanvas();
    private static Frame frame = new Frame("Room");
    private static Animator animator = new Animator(canvas);

    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View

        glu.gluLookAt(player.getLookAt().getX(), player.getLookAt().getY(), player.getLookAt().getZ(),
                player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
                player.getUp().getX(), player.getUp().getY(), player.getUp().getZ());

//        gl.glTranslatef(0.0f, 0.0f, 0.0f);

        this.draw(gl);
    }


    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        initDrawables();
        initGL(gl);

        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }

    private void initGL(GL2 gl) {

        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);              // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);               // The Type Of Depth Testing To Do
        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);


        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
    }

    public void reshape(GLAutoDrawable drawable, int x,
                        int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        float h = (float) width / (float) height;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            exit();
        }
    }

    public static void exit() {
        animator.stop();
        frame.dispose();
        System.exit(0);
    }

    private void addDrawable(Drawable d) {
        drawables.add(d);
    }


    public void initDrawables() {
        drawables = new ArrayList<>();
        createObjects();
    }

    public void createObjects() {

        Box box = new Box("wood", new Vertex(-5.5f, -2f, -13f), 1, 1, 1);
        Box box1 = new Box("LightWood", new Vertex(1f, -2f, -6f), 1, 0.5f, 1);
        Box box2 = new Box("washedWood", new Vertex(4.5f, -2f, -11f), 1, 1, 1);
        Room room = new Room();

        addDrawable(room);
        addDrawable(box);
        addDrawable(box1);
        addDrawable(box2);
    }

    public void show() {
        canvas.addGLEventListener(new World());
        frame.add(canvas);
        frame.setSize(1000, 600);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        frame.setVisible(true);
        animator.start();
        canvas.requestFocus();
    }

    @Override
    public void draw(GL2 gl) {
        for (Drawable d : drawables) {
            d.draw(gl);
        }
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }


    public void keyReleased(KeyEvent e) {

    }


    public void keyTyped(KeyEvent e) {
    }

    public void displayChanged(GLAutoDrawable gLDrawable,
                               boolean modeChanged, boolean deviceChanged) {
    }

}