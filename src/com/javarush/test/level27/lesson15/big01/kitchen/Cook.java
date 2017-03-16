package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Дмитрий on 13.12.2016.
 */
public class Cook extends Observable implements Runnable
{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name)
    {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try {
                Thread.sleep(10);
                startCookingOrder(queue.take());
            } catch (InterruptedException e)
            { break;}
        }

    }

    public void startCookingOrder(Order order)
    {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60 , order.getDishes()));
        try
        {
            Thread.sleep(order.getTotalCookingTime() *  10);
        } catch (InterruptedException e) {}
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy()
    {
        return busy;
    }

    public String getName()
    {
        return name;
    }
}
