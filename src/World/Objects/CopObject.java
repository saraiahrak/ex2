package World.Objects;

import World.CollisionDetection.Collidable;
import World.CollisionDetection.CollisionFactory;
import World.CollisionDetection.CollisionHandler;
import World.Drawable;
import World.Models.Model;
import Math.*;
import World.Space.World;


import javax.media.opengl.GL2;

public class CopObject implements IObject, Drawable {


    private Model model = null;
    private Vertex position = null;
    private final float depth = 2;
    private final float width = 0.75f;
    private final float height = 4f;
    private float[] motion;
    private Box wrap = null;

    public CopObject(Model m, Vertex p) {
        model = m;
        position = p;
        createWrap();
    }
//
//    public void setMotion(float[] m) {
//        motion = m;
//    }

    @Override
    public void setMotion(float dx, float dy, float dz) {
        motion = new float[]{dx, dy, dz};
        model.setMotion(dx, dy, dz);
    }

    private void createWrap() {
        Vertex v = new Vertex(position.getX() - (width / 2), position.getY(), position.getZ() - (depth / 2));
        wrap = new Box("wood", v, depth, height, width);
    }

    @Override
    public Box getBox() {
        return wrap;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public Vertex getPosition() {
        return position;
    }

    @Override
    public float getDepth() {
        return depth;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void draw(GL2 gl) {
        model.draw(gl);
//        wrap.draw(gl);
//        drawLeft(gl);
//        drawWrap(gl);


        for (Collidable c : World.collidables) {
            if (this == c) {
                continue;
            }
            CollisionHandler handler = CollisionFactory.create(this, c);
            if (handler != null) {
                boolean intersection = handler.handle(this, c);
                if (intersection) {
                    setMotion(-motion[0], motion[1], motion[2]);
                    break;
                }

            }
        }

        addMotion();

    }


    private void addMotion() {
        position = new Vertex(position.getX() + motion[0], position.getY() + motion[1], position.getZ() + motion[2]);
        wrap = new Box("wood", new Vertex(position.getX() - (width / 2), position.getY(), position.getZ() - (depth / 2)), depth, height, width);
    }

    private void drawLeft(GL2 gl) {
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        Vertex v = new Vertex(position.getX() - (width / 2), position.getY(), position.getZ() - (depth / 2));


        float x = v.getX();
        float y = v.getY();
        float z = v.getZ();


        gl.glBegin(GL2.GL_LINES);

        // Bottom Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);


        // Left Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);

        // Back Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z);


        gl.glEnd();


    }

    private void drawWrap(GL2 gl) {
        //
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        Vertex v = new Vertex(position.getX() - (width / 2) + 0.01f, position.getY(), position.getZ() - (depth / 2));

        gl.glBegin(GL2.GL_LINES);

        float x = v.getX();
        float y = v.getY();
        float z = v.getZ();


        // top Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);


        // Right Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);

        // Front Face
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x, y + height, z + depth);
        gl.glColor3f(174f / 255, 107f / 255, 107f / 255);
        gl.glVertex3f(x + width, y + height, z + depth);


        gl.glEnd();


    }

    @Override
    public void translate(float x, float y, float z) {
        model.translate(x, y, z);
    }

    @Override
    public void rotate(float angle, float x, float y, float z) {
        model.rotate(angle, x, y, z);
    }

    @Override
    public void scale(float x, float y, float z) {
        model.scale(x, y, z);
    }

}
