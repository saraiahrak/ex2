package Math;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Vertex
 * ***********/
public class Vertex {

    private float x;
    private float y;
    private float z;
    private float w;


    /*************
     * Constructors
     * ***********/
    public Vertex(float xv, float yv, float zv) {
        x = xv;
        y = yv;
        z = zv;
        w = 1;
    }


    /*************
     * Getters
     * ***********/

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }


    /*************
     * Methods
     * ***********/

    /**
     * isEqual
     * Compare two points
     *
     * @param p to compare
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Vertex p) {
        return p.getX() == x && p.getY() == y;
    }

    /**
     * at
     * value at position
     *
     * @param pos position
     * @return value
     */
    public float at(int pos) {
        if (pos == 0) {
            return x;
        }
        if (pos == 1) {
            return y;
        }
        if (pos == 2) {
            return z;
        } else {
            return w;
        }
    }

    /**
     * clone
     * Deep copy vertex
     *
     * @return clone
     */
    public Vertex clone() {
        return new Vertex(x, y, z);
    }
}
