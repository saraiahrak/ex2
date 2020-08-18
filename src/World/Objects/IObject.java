package World.Objects;

import Math.*;
import World.*;
import World.CollisionDetection.Collidable;
import World.Models.*;

public interface IObject extends Collidable {

    Model getModel();

    Vertex getPosition();

    float getDepth();

    float getHeight();

    float getWidth();

    void rotate(float angle, float x, float y, float z);

    void translate(float x, float y, float z);

    void scale(float x, float y, float z);

    void setMotion(float dx, float dy, float dz);

    Box getBox();

}
