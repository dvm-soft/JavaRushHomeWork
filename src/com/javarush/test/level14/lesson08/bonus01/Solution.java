package com.javarush.test.level14.lesson08.bonus01;



import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        try
        {
            int a[] = {1,2};
            a[0] = a[5];

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            int a[] = null;
            a[0] = 5;

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            int a = Integer.parseInt("dsfsdf");

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            new FileInputStream("c:'\\!\\d.txt");

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            (new ArrayList<String>()).get(5);

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            "sdfsd".charAt(777);

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            Class.forName("sdsd");

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            (new Stack<String>()).pop();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            int a[] = new int[-5];

        } catch (Exception e)
        {
            exceptions.add(e);
        }
    }
}
