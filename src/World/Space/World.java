/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Space;

import Game.Level;
import Sensor.KeySensor;
import View.Text.LevelText;
import View.Text.Message;
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

    public static Player player = new Player(5f, 0.2f, 380f);

    public static ArrayList<Drawable> drawables = new ArrayList<>();
    public static ArrayList<Collidable> collidables = new ArrayList<>();
    public static ArrayList<Collidable> jasmineCollidables = new ArrayList<>();

    public static boolean firstLevel = true;
    public static boolean secondLevel = false;
    public static boolean playerDisqualified = false;
    public static boolean showMessage = false;

    private Level level;

    public static boolean showMenu = true;
    public static boolean showMainMenu = true;
    public static boolean showInstructions = false;
    public static boolean showGameOver = false;
    public static boolean showSuccess = false;
    public static boolean showLevel2Menu = false;
    private Menu mainMenu = null;
    private Menu instructionsMenu = null;
    private Menu gameOverMenu = null;
    private Menu successMenu = null;
    private Menu level2Menu = null;

    private static GLU glu = new GLU();
    private static GLCanvas canvas = new GLCanvas();
    private static Frame frame = new Frame("Aladdin");
    private static Animator animator = new Animator(canvas);
    private static LevelText levelText = new LevelText();
    public static Message message = new Message();


    /**
     * display
     *
     * @param gLDrawable - GL Auto Drawable
     */
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View

        // set world light
        setLight(gl);

        player.update();

        glu.gluLookAt(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
                player.getLookAt().getX(), player.getLookAt().getY(), player.getLookAt().getZ(),
                player.getUp().getX(), player.getUp().getY(), player.getUp().getZ());

        if (showMenu) {
            player.coordinates.init(5, 0.2f, 380);
            findMenuType(gl);
            draw(gl);
            return;
        }

        draw(gl);

        player.displayLife();
        player.displayCoins();
        levelText.display();

        if (showMessage) {
            message.display();
        }

        if (!firstLevel && !secondLevel) {
            level2Menu.data.draw(gl);
            initLevel2(gl);
            secondLevel = true;
            showLevel2Menu = false;
        }
    }


    /**
     * findMenuType
     *
     * @param gl - GL2 object
     */
    private void findMenuType(GL2 gl) {
        if (showInstructions) {
            instructionsMenu.data.draw(gl);
        } else if (showMainMenu) {
            mainMenu.data.draw(gl);
        } else if (showGameOver) {
            gameOverMenu.data.draw(gl);
        } else if (showSuccess) {
            successMenu.data.draw(gl);
        } else {
            // wait for level2 menu
            level2Menu.data.draw(gl);
            World.showMenu = false;
            firstLevel = false;
        }
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

        initLevels(gl);
        initGL(gl);

        setMenus(gl);

        setLight(gl);

        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }


    /**
     * setMenus
     *
     * @param gl - GL2 object
     */
    private void setMenus(GL2 gl) {
        mainMenu = new Menu(gl, "textures/mainMenu.jpg");
        instructionsMenu = new Menu(gl, "textures/instructionsMenu.jpg");
        gameOverMenu = new Menu(gl, "textures/tryAgain.jpg");
        successMenu = new Menu(gl, "textures/goodJob.jpg");
        level2Menu = new Menu(gl, "textures/level2.jpg");

    }


    /**
     * setLight
     *
     * @param gl - GL2 object
     */
    private void setLight(GL2 gl) {
        float[] ambient, diffuse0, diffuse1;

        float LightPos0[] = {0f, 0f, -13f, 1.0f};
        float LightPos1[] = {0f, 0f, 0f, 1.0f};

        // set colors
        if (playerDisqualified) {
            ambient = new float[]{1f, 0f, 0f, 0f};
            diffuse0 = new float[]{0.5f, 0f, 0f, 0f};
            diffuse1 = new float[]{0.5f, 0f, 0f, 0f};
            playerDisqualified = false;
        } else if (showMenu || showLevel2Menu) {
            ambient = new float[]{1f, 1f, 1f, 1f};
            diffuse0 = new float[]{1f, 1f, 1f, 1f};
            diffuse1 = new float[]{1f, 1f, 1f, 1f};
            // set lighting positions
            if (secondLevel) {
                LightPos0 = new float[]{-200, 200, 10000, 1.0f};
                LightPos1 = new float[]{200, 1, 10000, 1.0f};
            } else {
                LightPos0 = new float[]{-160f, 200f, -1000f, 1.0f};
                LightPos1 = new float[]{160f, 1f, -1000, 1.0f};
            }
        } else {
            ambient = new float[]{1f, 1f, 1f, 1f};
            diffuse0 = new float[]{0f, 0f, 0f, 1f};
            diffuse1 = new float[]{1f, 1f, 0f, 0f};
        }

        // set light source
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse0, 0);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse1, 0);
        gl.glEnable(GL2.GL_LIGHT1);

        // position
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
    public void initLevels(GL2 gl) {

        level = new Level1(gl);

        initCollidables();
        initDrawables();
    }


    /**
     * initLevel2
     *
     * @param gl - GL2 object
     */
    public  void initLevel2(GL2 gl) {

        level = new Level2(gl);
        player.coordinates.init(32f, 3.5f, 86f);
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
        glu.gluPerspective(48, h, 1.0, 300);
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