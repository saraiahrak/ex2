package View;
import Math.*;

public class View {
    public static Vector position;
    public static Vector lookAt;
    public static Vector up;

    public View() {
        position = new Vector(0.5, 0.5, 10);
        lookAt = position.sub(new Vector(0, 0, 1));
        lookAt.normalize();
        up = new Vector(0, 1, 0);
    }


}
