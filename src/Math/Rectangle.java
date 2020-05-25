/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Math;

/*************
 * Class Rectangle
 * ***********/
public class Rectangle {

    private Vertex v;
    private float width;
    private float height;

    public Rectangle(Vertex vertex, float w, float h) {
        v = vertex;
        width = w;
        height = h;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Vertex getV() {
        return v;
    }
}
