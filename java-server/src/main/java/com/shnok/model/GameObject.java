package com.shnok.model;

/**
 * This class represents all spawnable objects in the world.<BR>
 * <BR>
 * Such as : static object, player, npc, item... <BR>
 * <BR>
 */
public abstract class GameObject {
    protected int _id;
    protected int _model;
    protected Point3D _position = new Point3D(0, 0, 0);

    public GameObject() {
    }

    public GameObject(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public float getPosX() {
        return _position.getX();
    }

    public float getPosY() {
        return _position.getY();
    }

    public float getPosZ() {
        return _position.getZ();
    }

    public Point3D getPos() {
        return _position;
    }

    public void setPosition(Point3D pos) {
        _position = pos;
    }

}
