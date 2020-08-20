/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space;

import Game.Level;
import Sensor.KeySensor;
import View.Text.LevelText;
import World.CollisionDetection.Collidable;
import World.Drawable;
import World.Player;
import World.Space.Level1.Level1;
import World.Space.Level2.Level2;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.util.ArrayList;
import Game.Menu;

/*************
 * Class World
 * ***********/
public class World extends KeyAdapter implements GLEventListener, Drawable {

    public static Player player = new Player(5f, 0.2f, 180f);

    public static ArrayList<Drawable> drawables = new ArrayList<>();
    public static ArrayList<Collidable> collidables = new ArrayList<>();

    public static boolean firstLevel = true;
    public static boolean secondLevel = false;
    public static boolean playerDisqualified = false;

    private Level level;

    public static boolean showMenu = true;
    public static boolean showInstructions = false;
    private Menu menu = null;



    private static GLU glu = new GLU();
    private static GLCanvas canvas = new GLCanvas();
    private static Frame frame = new Frame("Aladdin");
    private static Animator animator = new Animator(canvas);
    private static LevelText levelText = new LevelText();


    /**
     * display
     *
     * @param gLDrawable - GL Auto Drawable
     */
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View


        if (menu == null)
            menu = new Menu(gl);


        if (!firstLevel && !secondLevel) {
            secondLevel = true;
            initLevel2(gl);
        }

        // if the player disqualified
        setLight(gl);
//        if (playerDisqualified) {
//            playerDisqualified = false;
//            disqualificationLight(gl);
//        } else {
//            setLight(gl);
//        }

        player.update();

        glu.gluLookAt(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
                player.getLookAt().getX(), player.getLookAt().getY(), player.getLookAt().getZ(),
                player.getUp().getX(), player.getUp().getY(), player.getUp().getZ());


//        menu.draw(gl);
        if (showMenu) {
            menu.draw(gl);
        }
        else {
            draw(gl);
            player.displayLife();
            player.displayCoins();
            levelText.display();
//            System.out.println("Hi");
        }
//        if (!showMenu) {
//            draw(gl);
//            player.displayLife();
//            player.displayCoins();
//            levelText.display();
//        } else {
//            menu.draw(gl);
//        }
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

        setLight(gl);

        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }



    public static void addScore(int score) {
        player.addScore();
    }

    public static void reduceLife() {
        player.reduceLife();
    }




    /**
     * setLight
     *
     * @param gl - GL2 object
     */
    private void setLight(GL2 gl) {
        float[] ambient, diffuse0, diffuse1;

        // set colors
        if (playerDisqualified) {
            ambient = new float[]{1f, 0f, 0f, 0f};
            diffuse0 = new float[]{0.5f, 0f, 0f, 0f};
            diffuse1 = new float[]{0.5f, 0f, 0f, 0f};
            playerDisqualified = false;
        } else {
            ambient = new float[]{1f, 1f, 1f, 1f};
            diffuse0 = new float[]{0f, 0f, 0f, 1f};
            diffuse1 = new float[]{1f, 1f, 0f, 0f};
        }

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse0, 0);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse1, 0);
        gl.glEnable(GL2.GL_LIGHT1);

        // set lighting positions
        float LightPos0[] = {0f, 0f, -13f, 1.0f};
        float LightPos1[] = {0f, 0f, 0f, 1.0f};

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, LightPos0, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, LightPos1, 0);

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, LightPos0, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, LightPos1, 0);
    }


    /**
     * initLevel1
     *
     * @param gl - GL2 object
     */
    public void initLevel1(GL2 gl) {
        level = new Level1(gl);

        initCollidables();
        initDrawables();

//        new Level1(gl);
    }


    /**
     * initLevel2
     *
     * @param gl - GL2 object
     */
    public  void initLevel2(GL2 gl) {
        level = new Level2(gl);
        initCollidables();
        initDrawables();

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
     * @param x        - coordinate
     * @param y        - coordinate
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
    public void initDrawables() {
        drawables = new ArrayList<>();
        drawables = level.getDrawables();
    }

    /**
     * initCollidables
     * Initialize the list
     */
    public void initCollidables() {
        collidables = new ArrayList<>();
        collidables = level.getCollidables();
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


    public static void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    public static void removeDrawable(Drawable d) {
        drawables.remove(d);
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

