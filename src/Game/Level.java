package Game;

import World.CollisionDetection.Collidable;
import World.Drawable;

import java.util.ArrayList;

public interface Level {
    ArrayList<Collidable> getCollidables();
    ArrayList<Drawable> getDrawables();
}
