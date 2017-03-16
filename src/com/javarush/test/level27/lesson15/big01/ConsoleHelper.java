package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 12.12.2016.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> list = new ArrayList<Dish>();
        if (Dish.values().length > 0)
        {
            writeMessage("Please choose a dish: " + Dish.allDishesToString());
            while (true)
            {
                String str = readString();
                if ("exit".equalsIgnoreCase(str))
                    break;
                boolean isDish = false;
                for (Dish dish : Dish.values())
                    if (str.equals(dish.name()))
                    {
                        list.add(dish);
                        isDish = true;
                        break;
                    }
                if (!isDish)
                    writeMessage(str +" is not detected");
            }
        }
        return list;
    }
}
