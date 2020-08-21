/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Models;

/*************
 * Class Material
 * ***********/
public class Material {

    private float ns;
    private float ni;
    private float d;
    private float illum;
    private float[] ka;
    private float[] kd;
    private float[] ks;

    /*************
     * Constructor
     * ***********/
    public Material() {}

    /*************
     * Getters
     * ***********/

    public float getD() { return d; }

    /*************
     * Setters
     * ***********/

    public void setNs(float val) { ns = val; }
    public void setNi(float val) { ni = val; }
    public void setD(float val) { d = val; }
    public void setIllum(float val) { illum = val; }
    public void setKa(float[] val) { ka = val; }
    public void setKd(float[] val) { kd = val; }
    public void setKs(float[] val) { ks = val; }

}