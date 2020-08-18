package World.Objects;

import Math.Vertex;
import World.CollisionDetection.Collidable;
import World.Drawable;

import java.util.ArrayList;

public interface Polygon extends Collidable, Drawable {

    ArrayList<Vertex> getVertices();

    float getDistFactor();


}
