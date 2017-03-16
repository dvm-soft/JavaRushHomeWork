package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;

    public TaskManipulator()
    {
    }

    @Override
    public void run() {
        try
        {
            while (!thread.isInterrupted())
            {
                System.out.println(thread.getName());
                thread.sleep(100);
            }
        }
        catch (Exception e){}
    }

    @Override
    public synchronized void start(String threadName)
    {
        thread = new Thread(this);
        thread.setName(threadName);
        thread.start();
    }

    @Override
    public synchronized void stop()
    {
        thread.interrupt();
    }
}
