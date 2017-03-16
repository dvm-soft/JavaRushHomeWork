package com.javarush.test.level34.lesson15.big01.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.javarush.test.level34.lesson15.big01.model.Model.FIELD_SELL_SIZE;

/**
 * Created by 1 on 25.01.2017.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        level = level > 60 ? level % 60 : level;

        List<String> list = new ArrayList<>();
        int currentLevel = 0;
        try
        {
            FileReader file = new FileReader(levels.toFile());
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith("Maze: "))
                    currentLevel = Integer.parseInt(line.split(" ")[1]);
                if (currentLevel != level || line.length() == 0)
                    continue;
                if (!line.startsWith(" ") && !line.startsWith("X"))
                    continue;
                list.add(line);
            }
            file.close();
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes =  new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        for (int row = 0; row < list.size(); row++)
        {
            char[] chars = list.get(row).toCharArray();
            for (int col = 0; col < chars.length; col ++)
            {
                switch (chars[col])
                {
                    case ' ':
                        break;
                    case '*':
                        boxes.add(new Box(FIELD_SELL_SIZE / 2 + col * FIELD_SELL_SIZE,FIELD_SELL_SIZE / 2 + row * FIELD_SELL_SIZE));
                        break;
                    case 'X':
                        walls.add(new Wall(FIELD_SELL_SIZE / 2 + col * FIELD_SELL_SIZE,FIELD_SELL_SIZE / 2 + row * FIELD_SELL_SIZE));
                        break;
                    case '.':
                        homes.add(new Home(FIELD_SELL_SIZE / 2 + col * FIELD_SELL_SIZE,FIELD_SELL_SIZE / 2 + row * FIELD_SELL_SIZE));
                        break;
                    case '&':
                        boxes.add(new Box(FIELD_SELL_SIZE / 2 + col * FIELD_SELL_SIZE,FIELD_SELL_SIZE / 2 + row * FIELD_SELL_SIZE));
                        homes.add(new Home(FIELD_SELL_SIZE / 2 + col * FIELD_SELL_SIZE,FIELD_SELL_SIZE / 2 + row * FIELD_SELL_SIZE));
                        break;
                    case '@':
                        player = new Player(FIELD_SELL_SIZE / 2 + col * FIELD_SELL_SIZE,FIELD_SELL_SIZE / 2 + row * FIELD_SELL_SIZE);
                        break;

                }
            }
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}

