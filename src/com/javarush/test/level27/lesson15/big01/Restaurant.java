package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Дмитрий on 12.12.2016.
 */
public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<Order>();

    public static void main(String[] args)
    {
        Cook cook = new Cook("Amigo");
        cook.setQueue(QUEUE);
        Cook cook2 = new Cook("Dima");
        cook2.setQueue(QUEUE);

        Thread threadCook1 = new Thread(cook);
        Thread threadCook2 = new Thread(cook2);
        threadCook1.setDaemon(true);
        threadCook2.setDaemon(true);
        threadCook1.start();
        threadCook2.start();

        List<Tablet> tablets = new ArrayList<Tablet>();
        for (int i = 0; i < 5 ; i++)
        {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(QUEUE);
            tablets.add(tablet);
        }

        Waitor waitor = new Waitor();
        cook.addObserver(waitor);
        cook2.addObserver(waitor);

        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        generatorTask.start();
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        generatorTask.interrupt();

        DirectorTablet director = new DirectorTablet();

        director.printAdvertisementProfit();
        director.printCookWorkloading();
        director.printActiveVideoSet();
        director.printArchivedVideoSet();


    }


}
