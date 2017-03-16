package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Дмитрий on 25.01.2017.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int newX = getX();
        int newY = getY();

        switch (direction)
        {
            case DOWN:  newY += Model.FIELD_SELL_SIZE; break;
            case UP:    newY -= Model.FIELD_SELL_SIZE; break;
            case LEFT:  newX -= Model.FIELD_SELL_SIZE; break;
            case RIGHT: newX += Model.FIELD_SELL_SIZE; break;
        }

        return (gameObject.getX() == newX && gameObject.getY() == newY);
    }
}
