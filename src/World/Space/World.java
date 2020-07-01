/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import Utils.Reader;
import World.CollisionDetection.CollisionObject;
import Sensor.KeySensor;
import World.Models.ObjData;
import World.Models.OBJLoader;
import World.Space.Level1.Cave;
import World.Space.Level1.Level1;
import World.Space.Level2.Level2;
import World.Space.Level1.MarketPlace;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;
import World.Drawable;
import World.Player;

/*************
 * Class World
 * ***********/
public class World extends KeyAdapter implements GLEventListener, Drawable {

    public Player player = new Player(0f, 0.5f, 0f);
    public static ArrayList<Drawable> drawables;
    public static ArrayList<CollisionObject> collisionObjects;
    public static boolean wasCollision = false;
    public static boolean firstLevel = true;

    private static GLU glu = new GLU();
    private static GLCanvas canvas = new GLCanvas();
    private static Frame frame = new Frame("Aladdin");
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

        //if (!wasCollision) {
        player.update();
        //}

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

        initLevel1(gl);
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


    public static void initLevel1(GL2 gl) {
        // set the gl to the second level
        new Level2(gl);

        initCollidables();
        initDrawables();

        Level1.createSpace();
        Level1.createObjects();
        Level1.createModels(gl);
    }

    public static void initLevel2() {

        initCollidables();
        initDrawables();

        Level2.createSpace();
        Level2.createObjects();
        Level2.createModels();
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
     * initDrawables
     * Initialize the list
     */
    public static void initDrawables() {
        drawables = new ArrayList<>();
    }


    /**
     * initCollidables
     * Initialize the list
     */
    public static void initCollidables() {
        collisionObjects = new ArrayList<>();
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
        gl.glPushMatrix();
        for (Drawable d : drawables) {
            d.draw(gl);
        }
        gl.glPopMatrix();
    }


    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }


    public void displayChanged(GLAutoDrawable gLDrawable,
                               boolean modeChanged, boolean deviceChanged) {
    }
}
