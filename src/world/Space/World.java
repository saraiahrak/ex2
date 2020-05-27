/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package world.Space;

import java.awt.Frame;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import Sensor.KeySensor;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;
import world.Drawable;
import world.Player;
import world.objects.*;
import Math.*;

/*************
 * Class World
 * ***********/
public class World extends KeyAdapter implements GLEventListener, Drawable {

    private Player player = new Player();
    private ArrayList<Drawable> drawables;

    private static GLU glu = new GLU();
    private static GLCanvas canvas = new GLCanvas();
    private static Frame frame = new Frame("Room");
    private static Animator animator = new Animator(canvas);

    /**
     * display
     *
     * @param gLDrawable - GL Auto Drawable
     */
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View

        glu.gluLookAt(player.getLookAt().getX(), player.getLookAt().getY(), player.getLookAt().getZ(),
                player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
                player.getUp().getX(), player.getUp().getY(), player.getUp().getZ());

        player.update();

        // set lighting positions
        float	roomLightPos[] = {0f,0f,0f,1.0f};
        float redLightPos[] = {4.5f, 0f, -11.5f, 1.0f};

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, roomLightPos , 0);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, redLightPos , 0);
        gl.glEnable(GL2.GL_LIGHT1);

//        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        this.draw(gl);

    }

    /**
     * init
     *
     * @param drawable - GL Auto Drawable
     */
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        // enable light
        gl.glEnable(GL2.GL_LIGHTING);

        initDrawables();
        initGL(gl);

        // set colors
        float	ambient0[] = {1f,1f,1f,1.0f};
        float	diffuse0[] = {0.5f,0f,0f,1.0f};

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient0 , 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse0 , 0);

        // set colors
        float	ambient1[] = {1f,1f,1f,1.0f};
        float	diffuse1[] = {1f,1f,0f,1.0f};

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient1 , 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse1 , 0);

        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }

    /**
     * initGL
     *
     * @param gl - GL2 object
     */
    private void initGL(GL2 gl) {
        gl.glShadeModel(GL2.GL_SMOOTH);    // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);    // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);    // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);    // The Type Of Depth Testing To Do
        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
    }

    /**
     * reshape
     *
     * @param drawable - GL Auto Drawable
     * @param x - coordinate
     * @param y - coordinate
     * @param width
     * @param height
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
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

    /**
     * exit
     * Stops the animator and frame and closes the window
     */
    public static void exit() {
        animator.stop();
        frame.dispose();
        System.exit(0);
    }

    /**
     * addDrawable
     * Add drawable to list of drawable objects
     *
     * @param d - drawable
     */
    private void addDrawable(Drawable d) {
        drawables.add(d);
    }

    /**
     * initDrawables
     * Initialize the list
     */
    public void initDrawables() {
        drawables = new ArrayList<>();
        createObjects();
    }

    /**
     * createObjects
     */
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

    /**
     * show
     */
    public void show() {
        canvas.addGLEventListener(new World());
        canvas.addKeyListener(new KeySensor(player.getCoordinates()));
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

    public void displayChanged(GLAutoDrawable gLDrawable,
                               boolean modeChanged, boolean deviceChanged) {
    }

}

