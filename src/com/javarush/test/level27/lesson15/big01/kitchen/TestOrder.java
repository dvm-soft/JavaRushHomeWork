package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;

/**
 * Created by Дмитрий on 19.12.2016.
 */
public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes()
    {
        int count = (int)(Math.random() * Dish.values().length);
        for (int i = 0; i <= count; i++)
        {
            dishes.add(Dish.values()[(int) (Math.random() * Dish.values().length)]);
        }
    }
}
