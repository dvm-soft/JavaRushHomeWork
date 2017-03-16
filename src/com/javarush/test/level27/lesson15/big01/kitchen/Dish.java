package com.javarush.test.level27.lesson15.big01.kitchen;


/**
 * Created by Дмитрий on 12.12.2016.
 */
public enum Dish
{
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    private int duration;

    private Dish(int duration)
    {
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }

    public static String allDishesToString()
    {
        String result = "";
        for (Dish d : Dish.values())
        {
            result += (result.length() == 0 ? "" : ", ") + d.name();
        }
        return result;

    }
}
