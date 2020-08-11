package World.CollisionDetection;

public class CollisionFactory {

    public static CollisionHandler create() {
        return new PolyPointCollision();
    }

}
