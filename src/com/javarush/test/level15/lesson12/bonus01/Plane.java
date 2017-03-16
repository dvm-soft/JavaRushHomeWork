package com.javarush.test.level15.lesson12.bonus01;

/**
 * Created by 1 on 21.10.2016.
 */
public class Plane implements Flyable
{
    int countPassenger;

    public Plane(int countPassenger)
    {
        this.countPassenger = countPassenger;
    }

    public void fly() {};

}
