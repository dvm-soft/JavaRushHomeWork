package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 1 on 17.12.2016.
 */
public class DirectorTablet
{
    public void printAdvertisementProfit()
    {
        Map<Date, Long> map = StatisticEventManager.getInstance().reportAdvertisementProfit();
        long totalAmount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, Long> pair : map.entrySet())
        {
            totalAmount += pair.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f", dateFormat.format(pair.getKey()),pair.getValue()/100.0));
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalAmount/100.0));

    }
    public void printCookWorkloading()
    {
        Map<Date, Map<String, Integer>> map = StatisticEventManager.getInstance().reportCookWorkloading();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, Map<String, Integer>> pair : map.entrySet())
        {
            ConsoleHelper.writeMessage(dateFormat.format(pair.getKey()));
            for (Map.Entry<String, Integer> innerPair : pair.getValue().entrySet())
            {
                ConsoleHelper.writeMessage(innerPair.getKey() + " - " + (int) Math.ceil(innerPair.getValue()/60.0) + " min");
            }
            ConsoleHelper.writeMessage("");
        }


    }
    public void printActiveVideoSet()
    {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getAdvertisment(true);
        for (Advertisement ad : list)
        {
            ConsoleHelper.writeMessage(String.format("%s - %d", ad.getName(), ad.getHits()));
        }

    }
    public void printArchivedVideoSet()
    {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getAdvertisment(false);
        for (Advertisement ad : list)
        {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
