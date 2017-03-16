package com.javarush.test.level26.lesson10.home02;


import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

/**
 * Created by 1 on 04.12.2016.
 */
public class Producer implements Runnable
{
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map)
    {
        this.map = map;
    }

    @Override
    public void run()
    {
        Thread currentThread = Thread.currentThread();
        int i = 1;
        while(!currentThread.isInterrupted())
        {
            try
            {
                map.putIfAbsent(String.valueOf(i), "Some text for " + i);
                sleep(500);
                i++;
            }
            catch (InterruptedException e)
            {
                System.out.println(String.format("[%s] thread was terminated", currentThread.getName()));
                currentThread.interrupt();
            }
        }
    }
}
