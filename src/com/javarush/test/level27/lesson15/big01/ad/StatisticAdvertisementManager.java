package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Дмитрий on 19.12.2016.
 */
public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }

    public List<Advertisement> getAdvertisment(boolean active)
    {
        List<Advertisement> list = new ArrayList<Advertisement>();
        for (Advertisement ad : AdvertisementStorage.getInstance().list())
        {
            if (active)
            {
                if (ad.getHits() > 0)
                    list.add(ad);
            } else
                if (ad.getHits() == 0)
                    list.add(ad);
        }
        Collections.sort(list, new Comparator<Advertisement>()
                {
                    @Override
                    public int compare(Advertisement o1, Advertisement o2)
                    {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                }
        );

        return list;
    }
}
