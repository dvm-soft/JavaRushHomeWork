package com.javarush.test.level25.lesson05.home01;

/**
 * Created by Дмитрий on 30.11.2016.
 */
public class LoggingStateThread extends Thread
{
    Thread target;

    public LoggingStateThread(Thread target)
    {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        Thread.State state = target.getState();
        System.out.println(state);
       while(state != Thread.State.TERMINATED)
       {
           if (state != target.getState())
           {
               state = target.getState();
               System.out.println(state);
           }
       }
    }
}
