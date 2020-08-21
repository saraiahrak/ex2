/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/

package World.Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

/*************
 * Class Object Loader
 * ***********/
public class OBJLoader {

    // model data
    private Model model;
    private String objDataName;
    private String mtlName;
    private Material material;
    private Texture texture;
    private String OBJModelPath; //the path to the model file
    private String MTLModelPath = null; //the path to the material file
    private HashMap<String,Texture> texturesMap = new HashMap<>();
    private HashMap<String,ArrayList<ObjData>> objDataMap = new HashMap<>();
    private ArrayList<ObjData> objDataList = new ArrayList<>();

    private ArrayList<float[]> vData = new ArrayList<>(); //list of vertex coordinates
    private ArrayList<float[]> vtData = new ArrayList<>(); //list of texture coordinates
    private ArrayList<float[]> vnData = new ArrayList<>(); //list of normal coordinates
    private ArrayList<int[]> fv = new ArrayList<>(); //face vertex indices
    private ArrayList<int[]> ft = new ArrayList<>(); //face texture indices
    private ArrayList<int[]> fn = new ArrayList<>(); //face normal indices
    private int FaceFormat; //format of the faces triangles or quads
    private int FaceMultiplier; //number of possible coordinates per face
    private int PolyCount = 0; //the model polygon count


    /**
     * Constructor
     */
    public OBJLoader(String inModelPath, GL2 gl) {
        OBJModelPath = inModelPath;
        LoadOBJModel(OBJModelPath, gl);
        if (MTLModelPath != null) {
            LoadMTLModel();
        }
        model = new Model(objDataList);
        cleanup();
    }


    /**
     * getModel
     * @return model
     */
    public Model getModel() {
        return this.model;
    }


    /**
     * LoadOBJModel
     */
    private void LoadOBJModel(String ModelPath, GL2 gl) {
        try {
            BufferedReader br;
            InputStream myis = ClassLoader.getSystemClassLoader().getResourceAsStream(ModelPath);

            InputStreamReader isr = new InputStreamReader(myis);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) { //read any descriptor data in the file
                    // Zzzz ...
                } else if (line.equals("")) {
                    // Ignore whitespace data
                } else if (line.startsWith("v ")) { //read in vertex data
                    vData.add(ProcessData(line));
                } else if (line.startsWith("vt ")) { //read texture coordinates
                    vtData.add(ProcessData(line));
                } else if (line.startsWith("vn ")) { //read normal coordinates
                    vnData.add(ProcessData(line));
                } else if (line.startsWith("f ")) { //read face data
                    ProcessfData(line);
                } else if (line.startsWith("usemtl ")) {
                    if (objDataName != null) {
                        SetOBJ(gl);
                    }
                    objDataName = ProcessPathData(line);
                } else if (line.startsWith("mtllib ")) {
                    MTLModelPath = ProcessPathData(line);
                }
            }
            SetOBJ(gl);
            br.close();
        } catch (IOException e) {
        }
    }


    /**
     * SetOBJ
     * Creates new object data
     *
     * @param gl - Gl2
     */
    private void SetOBJ(GL2 gl) {
        SetFaceRenderType();
        ObjData data = new ObjData(OBJModelPath);
        // get the tDisplayListID
        data.setList(ConstructInterleavedArray(gl));
        addToList(objDataName, data);
        clean();
    }


    /**
     * addToList
     *
     * @param key - object data name
     * @param objData
     */
    public void addToList(String key, ObjData objData) {
        ArrayList<ObjData> objDataArrayList = objDataMap.get(key);
        // if list does not exist create it
        if (objDataArrayList == null) {
            objDataArrayList = new ArrayList<>();
            objDataArrayList.add(objData);
            objDataMap.put(key, objDataArrayList);
        } else {
            // add if item is not already in list
            if(!objDataArrayList.contains(objData)) {
                objDataArrayList.add(objData);
            }
        }
    }


    /**
     * LoadMTLModel
     */
    private void LoadMTLModel() {
        try {
            BufferedReader br;
            InputStream myis = ClassLoader.getSystemClassLoader().getResourceAsStream(MTLModelPath);
            InputStreamReader isr = new InputStreamReader(myis);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) { //read any descriptor data in the file
                    // Zzzz ...
                } else if (line.equals("")) {
                    // Ignore whitespace data
                } else if (line.startsWith("newmtl ")) { //read in vertex data
                    if (material != null) {
                        setMaterial();
                    }
                    material = new Material();
                    mtlName = ProcessPathData(line);
                } else if (line.startsWith("Ns ")) {
                    material.setNs(ProcessData(line)[0]);
                } else if (line.startsWith("Ka ")) {
                    material.setKa(ProcessData(line));
                } else if (line.startsWith("Kd ")) {
                    material.setKd(ProcessData(line));
                } else if (line.startsWith("Ks ")) {
                    material.setKs(ProcessData(line));
                } else if (line.startsWith("Ni ")){
                    material.setNi(ProcessData(line)[0]);
                } else if (line.startsWith("d ")){
                    material.setD(ProcessData(line)[0]);
                } else if (line.startsWith("illum ")){
                    material.setIllum(ProcessData(line)[0]);
                } else if (line.startsWith("map_Ka ")){
                    texture = getTexturePath(line);
                    if (texture != null) {
                        texturesMap.put(mtlName, texture);
                    }
                }
            }
            // set the last material
            setMaterial();
            br.close();
        } catch (IOException e) {
        }
    }


    /**
     * setMaterial
     */
    private void setMaterial() {
        ArrayList<ObjData> dataList = objDataMap.get(mtlName);
        for (ObjData data: dataList) {
            data.setTexture(texturesMap.get(mtlName));
            data.setMaterial(material);
            objDataList.add(data);
        }
    }


    private float[] ProcessData(String read) {
        final String s[] = read.split("\\s+");
        return (ProcessFloatData(s)); //returns an array of processed float data
    }

    private String ProcessPathData(String read) {
        final String obj[] = read.split("\\s+");
        return obj[1];
    }

    private float[] ProcessFloatData(String sdata[]) {
        float data[] = new float[sdata.length - 1];
        for (int loop = 0; loop < data.length; loop++) {
            data[loop] = Float.parseFloat(sdata[loop + 1]);
        }
        return data; //return an array of floats
    }

    private void ProcessfData(String fread) {
        PolyCount++;
        String s[] = fread.split("\\s+");
        if (fread.contains("//")) { //pattern is present if obj has only v and vn in face data
            for (int loop = 1; loop < s.length; loop++) {
                s[loop] = s[loop].replaceAll("//", "/0/"); //insert a zero for missing vt data
            }
        }
        ProcessfIntData(s); //pass in face data
    }

    private void ProcessfIntData(String sdata[]) {
        int vdata[] = new int[sdata.length - 1];
        int vtdata[] = new int[sdata.length - 1];
        int vndata[] = new int[sdata.length - 1];
        for (int loop = 1; loop < sdata.length; loop++) {
            String s = sdata[loop];
            String[] temp = s.split("/");
            vdata[loop - 1] = Integer.valueOf(temp[0]); //always add vertex indices
            if (temp.length > 1) { //we have v and vt data
                vtdata[loop - 1] = Integer.valueOf(temp[1]); //add in vt indices
            } else {
                vtdata[loop - 1] = 0; //if no vt data is present fill in zeros
            }
            if (temp.length > 2) { //we have v, vt, and vn data
                vndata[loop - 1] = Integer.valueOf(temp[2]); //add in vn indices
            } else {
                vndata[loop - 1] = 0; //if no vn data is present fill in zeros
            }
        }
        fv.add(vdata);
        ft.add(vtdata);
        fn.add(vndata);
    }


    /**
     * SetFaceRenderType
     */
    private void SetFaceRenderType() {
        final int temp[] = (int[]) fv.get(0);
        if (temp.length == 3) {
            FaceFormat = GL2.GL_TRIANGLES; //the faces come in sets of 3 so we have triangular faces
            FaceMultiplier = 3;
        } else if (temp.length == 4) {
            FaceFormat = GL2.GL_QUADS; //the faces come in sets of 4 so we have quadrilateral faces
            FaceMultiplier = 4;
        } else {
            FaceFormat = GL2.GL_POLYGON; //fall back to render as free form polygons
        }
    }


    private Texture getTexturePath(String line) {
        try {
            String currentPath = ProcessPathData(line);
            InputStream myis = ClassLoader.getSystemClassLoader().getResourceAsStream(currentPath);
            String data[] = currentPath.split("\\.");
            return TextureIO.newTexture(myis, true, data[1]);
        } catch(Exception e){
        }
        return null;
    }


    /**
     * ConstructInterleavedArray
     */
    private int ConstructInterleavedArray(GL2 inGL) {
        final int tv[] = (int[]) fv.get(0);
        final int tt[] = (int[]) ft.get(0);
        final int tn[] = (int[]) fn.get(0);
        int tDisplayListID = 0;
        //if a value of zero is found that it tells us we don't have that type of data
        if ((tv[0] != 0) && (tt[0] != 0) && (tn[0] != 0)) {
            tDisplayListID = ConstructTNV(inGL); //we have vertex, 2D texture, and normal Data
        } else if ((tv[0] != 0) && (tt[0] != 0) && (tn[0] == 0)) {
            tDisplayListID = ConstructTV(inGL); //we have just vertex and 2D texture Data
        } else if ((tv[0] != 0) && (tt[0] == 0) && (tn[0] != 0)) {
            tDisplayListID = ConstructNV(inGL); //we have just vertex and normal Data
        } else if ((tv[0] != 0) && (tt[0] == 0) && (tn[0] == 0)) {
            tDisplayListID = ConstructV(inGL);
        }
        return tDisplayListID;
    }


    private int ConstructTNV(GL2 inGL) {
        int[] v, t, n;
        float tcoords[] = new float[2]; //only T2F is supported in interLeavedArrays!!
        float coords[] = new float[3];
        int tDisplayListID = inGL.glGenLists(1);
        inGL.glNewList(tDisplayListID,GL2.GL_COMPILE);
        inGL.glBegin(FaceFormat);

        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            t = (int[]) (ft.get(oloop));
            n = (int[]) (fn.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the texture coordinate data
                for (int tloop = 0; tloop < tcoords.length; tloop++)
                    //only T2F is supported in interleavedarrays!!
                    tcoords[tloop] = ((float[]) vtData.get(t[iloop] - 1))[tloop];
                inGL.glTexCoord2f(tcoords[0],tcoords[1]);
                //fill in the normal coordinate data
                for (int vnloop = 0; vnloop < coords.length; vnloop++)
                    coords[vnloop] = ((float[]) vnData.get(n[iloop] - 1))[vnloop];
                inGL.glNormal3f(coords[0],coords[1],coords[2]);
                //fill in the vertex coordinate data
                for (int vloop = 0; vloop < coords.length; vloop++)
                    coords[vloop] = ((float[]) vData.get(v[iloop] - 1))[vloop];
                inGL.glVertex3f(coords[0],coords[1],coords[2]);
            }
        }
        inGL.glEnd();
        inGL.glEndList();
        return tDisplayListID;
    }

    private int ConstructTV(GL2 inGL) {
        int[] v, t;
        float tcoords[] = new float[2]; //only T2F is supported in interLeavedArrays!!
        float coords[] = new float[3];
        int tDisplayListID = inGL.glGenLists(1);
        inGL.glNewList(tDisplayListID,GL2.GL_COMPILE);
        inGL.glBegin(FaceFormat);
        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            t = (int[]) (ft.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the texture coordinate data
                for (int tloop = 0; tloop < tcoords.length; tloop++)
                    //only T2F is supported in interleavedarrays!!
                    tcoords[tloop] = ((float[]) vtData.get(t[iloop] - 1))[tloop];
                inGL.glTexCoord2f(tcoords[0],tcoords[1]);
                //fill in the vertex coordinate data
                for (int vloop = 0; vloop < coords.length; vloop++)
                    coords[vloop] = ((float[]) vData.get(v[iloop] - 1))[vloop];
                inGL.glVertex3f(coords[0],coords[1],coords[2]);
            }
        }
        inGL.glEnd();
        inGL.glEndList();
        return tDisplayListID;
    }

    private int ConstructNV(GL2 inGL) {
        int[] v, n;
        float coords[] = new float[3];
        int tDisplayListID = inGL.glGenLists(1);
        inGL.glNewList(tDisplayListID,GL2.GL_COMPILE);
        inGL.glBegin(FaceFormat);
        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            n = (int[]) (fn.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the normal coordinate data
                for (int vnloop = 0; vnloop < coords.length; vnloop++)
                    coords[vnloop] = ((float[]) vnData.get(n[iloop] - 1))[vnloop];
                inGL.glNormal3f(coords[0],coords[1],coords[2]);
                //fill in the vertex coordinate data
                for (int vloop = 0; vloop < coords.length; vloop++)
                    coords[vloop] = ((float[]) vData.get(v[iloop] - 1))[vloop];
                inGL.glVertex3f(coords[0],coords[1],coords[2]);
            }
        }
        inGL.glEnd();
        inGL.glEndList();
        return tDisplayListID;
    }

    private int ConstructV(GL2 inGL) {
        int[] v;
        float coords[] = new float[3];
        int tDisplayListID = inGL.glGenLists(1);
        inGL.glNewList(tDisplayListID,GL2.GL_COMPILE);
        inGL.glBegin(FaceFormat);
        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the vertex coordinate data
                for (int vloop = 0; vloop < coords.length; vloop++)
                    coords[vloop] = ((float[]) vData.get(v[iloop] - 1))[vloop];
                inGL.glVertex3f(coords[0],coords[1],coords[2]);
            }
        }
        inGL.glEnd();
        inGL.glEndList();
        return tDisplayListID;
    }


    /**
     * cleanup
     *
     * Clear all lists
     */
    private void cleanup() {
        vData.clear();
        vtData.clear();
        vnData.clear();
        fv.clear();
        ft.clear();
        fn.clear();
        texturesMap.clear();
    }


    /**
     * clean
     *
     * Clear data
     */
    private void clean() {
        fv.clear();
        ft.clear();
        fn.clear();
    }

}