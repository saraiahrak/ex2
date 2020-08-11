package World.Objects;

import World.CollisionDetection.Collidable;
import World.Drawable;
import Math.*;

import java.util.ArrayList;

public interface Polygon extends Collidable {

    ArrayList<Vertex> getVertices();
}
