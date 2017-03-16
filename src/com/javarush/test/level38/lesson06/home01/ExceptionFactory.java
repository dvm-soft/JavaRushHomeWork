package com.javarush.test.level38.lesson06.home01;

/**
 * Created by 1 on 02.02.2017.
 */
public class ExceptionFactory
{
    public static Throwable getException(Enum en) throws Exception
    {
        if(en == null)
            return new IllegalArgumentException();

        String msg = en.name().substring(0,1) + en.name().substring(1).toLowerCase().replace('_', ' ');

        if (en instanceof ExceptionApplicationMessage)
            return new Exception(msg);
        if (en instanceof ExceptionDBMessage)
            return new RuntimeException(msg);
        if (en instanceof ExceptionUserMessage)
            return new Error(msg);

        return new IllegalArgumentException();
    }

}

