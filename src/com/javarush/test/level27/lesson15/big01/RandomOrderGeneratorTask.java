package com.javarush.test.level27.lesson15.big01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 19.12.2016.
 */
public class RandomOrderGeneratorTask extends Thread
{
    private List<Tablet> tablets = new ArrayList<Tablet>();
    private int interval;

    @Override
    public void run()
    {
        while (true)
        {
            int tabletNumber = (int) (Math.random() * tablets.size());
            tablets.get(tabletNumber).createTestOrder();
            try
            {
                sleep(interval);
            } catch (InterruptedException e) {break;}
        }
    }

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tablets = tablets;
        this.interval = interval;
    }
}