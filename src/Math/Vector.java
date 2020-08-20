/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package Math;

import World.CollisionDetection.Collidable;
import java.util.Arrays;

/*************
 * Class Vector
 * ***********/
public class Vector implements Collidable {

    private float[] vector;
    private int dim;

    /*************
     * Constructors
     * ***********/

    public Vector(float[] res) {
        vector = res;
        dim = res.length;
    }

    public Vector(int num) {
        vector = new float[]{1, 1, 1, 1};
        dim = num;
    }

    public Vector(float x, float y, float z) {
        vector = new float[]{x, y, z, 1};
        dim = 4;
    }

    public Vector(Vertex ver, Vector vec) {
        float x = ver.getX() - vec.getX();
        float y = ver.getY() - vec.getY();
        float z = ver.getZ() - vec.getZ();
        vector = new float[]{x, y, z, 1};
        dim = 4;
    }

    /*************
     * Getters
     * ***********/
    public float[] getVector() {
        return vector;
    }

    public float getX() {
        return vector[0];
    }

    public float getY() {
        return vector[1];
    }

    public float getZ() {
        return vector[2];
    }

    public float getDim() {
        return dim;
    }

    public float getSize() {
        return (float) Math.sqrt(this.dot(this));
    }

    public void setX(float x) {
         vector[0] = x;
    }

    public void setY(float y) {
        vector[1] = y;
    }

    public void setZ(float z) {
        vector[2] = z;
    }

    public void setVector(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }


    /*************
     * Methods
     * ***********/

    /**
     * cross
     * Calculate cross product with v vector
     *
     * @param v other vector
     * @return cross product vector
     */
    public Vector cross(Vector v) {
        float[] res = new float[dim];

        res[0] = vector[1] * v.vector[2] - vector[2] * v.vector[1];
        res[1] = vector[2] * v.vector[0] - vector[0] * v.vector[2];
        res[2] = vector[0] * v.vector[1] - vector[1] * v.vector[0];
        res[3] = 1;

        return new Vector(res);
    }

    /**
     * dot
     * Calculate dot product with v vector
     *
     * @param v other vector
     * @return dot product
     */
    public float dot(Vector v) {
        float res = 0;
        for (int i = 0; i < dim - 1; i++) {
            res += vector[i] * v.vector[i];
        }
        return res;
    }

    /**
     * dot
     * Calculate dot product with v vector
     *
     * @param v     other vector
     * @param theta angle
     * @return dot product
     */
    public float dot(Vector v, float theta) {
        return getSize() * v.getSize() * (float) Math.cos(theta);
    }

    /**
     * add
     * Calculate addition of two vectors
     *
     * @param v other vector
     * @return addition vector
     */
    public Vector add(Vector v) {
        Vector res = new Vector(dim);
        for (int i = 0; i < dim - 1; i++) {
            res.vector[i] = vector[i] + v.vector[i];
        }
        return res;
    }

    /**
     * sub
     * Calculate subtraction of two vectors
     *
     * @param v other vector
     * @return subtraction vector
     */
    public Vector sub(Vector v) {
        Vector res = new Vector(dim);
        for (int i = 0; i < dim - 1; i++) {
            res.vector[i] = vector[i] - v.vector[i];
        }
        return res;
    }

    /**
     * sub
     * multiply vector by scalar
     *
     * @param scalar scalar
     * @return vector
     */
    public Vector multByScalar(float scalar) {
        Vector res = new Vector(dim);
        for (int i = 0; i < dim; i++) {
            res.vector[i] = this.at(i) * scalar;
        }
        return res;
    }

    /**
     * proj
     * find the projection of this vector on vector v
     *
     * @param v vector
     * @return projection vector
     */
    public Vector proj(Vector v) {
        float scalar = v.dot(this) / (float) Math.pow(v.getSize(), 2);
        return v.multByScalar(scalar);
    }

    /**
     * isEqual
     * Compare two vectors
     *
     * @param v to compare
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Vector v) {
        if (v.getDim() != getDim()) {
            return false;
        }

        for (int i = 0; i < dim; i++) {
            if (this.at(i) != v.vector[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * at
     * Return value of vector at given position
     *
     * @param pos position
     * @return value
     */
    public float at(int pos) {
        return vector[pos];
    }

    /**
     * normalize
     * normalize vector
     *
     * @return normalized vector
     */
    public Vector normalize() {
        return multByScalar(1 / getSize());
    }

    /**
     * getTheta
     * Calculate angle between two vectors
     *
     * @return angle
     */
    public float getTheta(Vector v) {
        return (float) Math.toDegrees(Math.acos(this.dot(v) / (this.getSize() * v.getSize())));
    }

    /**
     * clone
     * Deep copy this vector
     *
     * @return the clone
     */
    public Vector clone() {
        float[] copy = Arrays.copyOf(vector, vector.length);
        return new Vector(copy);
    }
}