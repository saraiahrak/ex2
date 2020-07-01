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
    private float tr;
    private float[] tf;
    private float illum;
    private float[] ka;
    private float[] kd;
    private float[] ks;
    private float[] ke;
    String map_ka;
    String map_kd;

    /*************
     * Constructor
     * ***********/
    public Material() {}


    /*************
     * Getters
     * ***********/

    public float getNs() { return ns; }
    public float getNi() { return ni; }
    public float getD() { return d; }
    public float getTr() { return tr; }
    public float[] getTf() { return tf; }
    public float getIllum() { return illum; }
    public float[] getKa() { return ka; }
    public float[] getKd() { return kd; }
    public float[] getKs() { return ks; }
    public float[] getKe() { return ke; }
    public String getMapKa() { return map_ka; }
    public String getMapKd() { return map_kd; }

    /*************
     * Setters
     * ***********/

    public void setNs(float val) { ns = val; }
    public void setNi(float val) { ni = val; }
    public void setD(float val) { d = val; }
    public void setTr(float val) { tr = val; }
    public void setTf(float[] val) { tf  = val; }
    public void setIllum(float val) { illum = val; }
    public void setKa(float[] val) { ka = val; }
    public void setKd(float[] val) { kd = val; }
    public void setKs(float[] val) { ks = val; }
    public void setKe(float[] val) { ke = val; }
    public void setMapKa(String val) { map_ka = val; }
    public void setMapKd(String val) { map_kd = val; }

}