package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * Created by Дмитрий on 25.01.2017.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public void paint(Graphics g)
    {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());

        GameObjects gameObjects = view.getGameObjects();
        for (GameObject gameObject : gameObjects.getAll())
            gameObject.draw(g);
    }

    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);
            switch (e.getKeyCode())
            {
                case VK_LEFT: eventListener.move(Direction.LEFT); break;
                case VK_UP: eventListener.move(Direction.UP); break;
                case VK_DOWN: eventListener.move(Direction.DOWN); break;
                case VK_RIGHT: eventListener.move(Direction.RIGHT); break;
                case VK_R: eventListener.restart(); break;
            }

        }
    }
}

