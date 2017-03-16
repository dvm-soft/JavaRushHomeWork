package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.io.File;

/**
 * Created by Дмитрий on 25.01.2017.
 */
public class Model
{
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(new File("src/com/javarush/test/level34/lesson15/big01/res/levels.txt").toPath());

    public static final int FIELD_SELL_SIZE = 20;

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
      return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        restartLevel(++currentLevel);
    }


    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for (GameObject go : gameObjects.getAll())
            if (go instanceof Wall && gameObject.isCollision(go, direction))
                return true;
        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        Player player = gameObjects.getPlayer();

        for (Box box : gameObjects.getBoxes())
            if (player.isCollision(box, direction))
            {
                if (checkWallCollision(box, direction))
                    return true;
                for (GameObject otherBox : gameObjects.getBoxes())
                    if (!otherBox.equals(box) && (box.isCollision(otherBox, direction)))
                        return true;
                switch (direction)
                {
                    case DOWN:  box.move(0, FIELD_SELL_SIZE); break;
                    case UP:    box.move(0, -FIELD_SELL_SIZE); break;
                    case LEFT:  box.move(-FIELD_SELL_SIZE, 0); break;
                    case RIGHT: box.move(FIELD_SELL_SIZE, 0); break;
                }
            }

        return false;

    }

    public void checkCompletion()
    {
        int closeHome = 0;
        for (Home home : gameObjects.getHomes())
            for (Box box : gameObjects.getBoxes())
                if (home.getY() == box.getY() && home.getX() == box.getX())
                    closeHome ++;
        if (gameObjects.getHomes().size() == closeHome)
            eventListener.levelCompleted(currentLevel);
    }

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollision(direction))
            return;

        switch (direction)
        {
            case DOWN:  player.move(0, FIELD_SELL_SIZE); break;
            case UP:    player.move(0, -FIELD_SELL_SIZE); break;
            case LEFT:  player.move(-FIELD_SELL_SIZE, 0); break;
            case RIGHT: player.move(FIELD_SELL_SIZE, 0); break;
        }
        checkCompletion();
    }

}

