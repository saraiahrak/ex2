/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Objects;

import Math.Vertex;
import World.CollisionDetection.Collidable;
import World.Drawable;
import com.jogamp.opengl.util.texture.Texture;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/*************
 * Class Box
 * ***********/
public class Box implements Drawable, Collidable {

    private String textureKey;
    private Texture texture;
    private Vertex bottomLeft;

    private float depth;
    private float height;
    private float width;

    private Vertex center;
    private ArrayList<Polygon> polygons;

    /*************
     * Constructor
     * ***********/
    public Box(String key, Vertex left, float d, float h, float w) {
        textureKey = key;
        bottomLeft = left.clone();
        depth = d;
        height = h;
        width = w;
        polygons = new ArrayList<>();
        center = new Vertex(bottomLeft.getX() + w / 2, bottomLeft.getY() + h / 2, bottomLeft.getZ() + d / 2);
        createPolygons();
    }

    /**************
     * Getters
     * ***********/

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Vertex getCenter() {
        return center;
    }

    public float getDepth() {
        return depth;
    }

    public Vertex getBottomLeft() {
        return bottomLeft;
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    /**
     * createPolygons
     */
    private void createPolygons() {
        float x = bottomLeft.getX();
        float y = bottomLeft.getY();
        float z = bottomLeft.getZ();

        Wall front = new Wall(textureKey, new Vertex(x, y, z + depth), height, width, 0f);
        Wall back = new Wall(textureKey, new Vertex(x, y, z), height, width, 0f);
        Floor bottom = new Floor(textureKey, new Vertex(x, y, z), 0f, width, depth);
        Wall left = new Wall(textureKey, new Vertex(x, y, z), height, 0f, depth);
        Wall right = new Wall(textureKey, new Vertex(x + width, y, z), height, 0f, depth);
        Ceiling top = new Ceiling(textureKey, new Vertex(x, y + height, z), 0f, width, depth);

        polygons.add(front);
        polygons.add(back);
        polygons.add(left);
        polygons.add(right);
        if (textureKey.contains("bush")) {
            polygons.add(bottom);
            polygons.add(top);
        }

    }

    @Override
    public void draw(GL2 gl) {

        for (Polygon p : polygons) {
            p.draw(gl);
        }
    }


    /**
     * createBoxes
     * Create boxes from string of coordinates
     *
     * @param lines   string of coordinates
     * @param texture name
     * @return boxes array
     */
    public static ArrayList<Box> createBoxes(List<String> lines, String texture) {
        ArrayList<Box> boxesList = new ArrayList<>();

        for (String line : lines) {
            String[] coordinate = line.split(" ");
            float x = Float.parseFloat(coordinate[0]);
            float y = Float.parseFloat(coordinate[1]);
            float z = Float.parseFloat(coordinate[2]);
            float h = Float.parseFloat(coordinate[3]);
            float w = Float.parseFloat(coordinate[4]);
            float d = Float.parseFloat(coordinate[5]);

            boxesList.add(new Box(texture, new Vertex(x, y, z), h, w, d));
        }
        return boxesList;
    }

}