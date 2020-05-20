package View;
import Math.*;

import javax.media.opengl.glu.GLU;

public class View {
    public static Vector position;
    public static Vector lookAt;
    public static Vector up;

    public View(GLU glu) {
        position = new Vector(0.5, 0.5, 10);
        lookAt = position.sub(CoordinateSystem.zAxis);
        lookAt.normalize();
        up = new Vector(0, 1, 0);

        glu.gluLookAt(position.at(0), position.at(1), position.at(2),
                lookAt.at(0), lookAt.at(1), lookAt.at(2),
                up.at(0), up.at(1), up.at(2));

    }


}
