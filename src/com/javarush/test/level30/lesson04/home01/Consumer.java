package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by 1 on 28.12.2016.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        Thread currentThread = Thread.currentThread();
        try
        {
            currentThread.sleep(500);
            while (!currentThread.isInterrupted())
            {
                ShareItem item = queue.take();
                System.out.println("Processing " + item);
            }
        }
        catch (InterruptedException e)
        {
        }


    }
}
