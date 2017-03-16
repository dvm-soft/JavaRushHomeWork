package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by 1 on 28.12.2016.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        Thread currentThread = Thread.currentThread();
        for (int i = 1; i < 10; i++)
        {
            try
            {
                System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
                queue.offer(new ShareItem("ShareItem-" + i, i));
                currentThread.sleep(100);
                if (queue.hasWaitingConsumer())
                    System.out.println("Consumer в ожидании!");
            }
            catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
