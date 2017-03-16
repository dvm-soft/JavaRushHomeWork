package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.Helper;

/**
 * Created by Дмитрий on 19.01.2017.
 */
public class ExceptionHandler
{
    public static void log(Exception e)
    {
        Helper.printMessage(e.toString());
    }
}
