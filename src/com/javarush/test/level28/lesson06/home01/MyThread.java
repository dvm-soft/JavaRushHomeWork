package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Дмитрий on 20.12.2016.
 */
public class MyThread extends Thread
{
    private volatile static AtomicInteger threadCounter = new AtomicInteger(0);

    public MyThread()
    {
        super();
        setPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        setPriority();
    }

    public MyThread(String name)
    {
        super(name);
        setPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        setPriority();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        setPriority();
    }

    private synchronized void setPriority()
    {
        int priority = threadCounter.addAndGet(1);
        if (priority > this.getThreadGroup().getMaxPriority())
            this.setPriority(this.getThreadGroup().getMaxPriority());
        else
            this.setPriority(priority);
        if (priority == 10)
            threadCounter = new AtomicInteger(0);
    }
}
