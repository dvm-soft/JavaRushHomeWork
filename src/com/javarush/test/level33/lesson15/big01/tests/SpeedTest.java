package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 22.01.2017.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        Date dateStart = new Date();

        for (String str : strings)
            ids.add(shortener.getId(str));

        return new Date().getTime() - dateStart.getTime();

    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        Date dateStart = new Date();

        for (Long key : ids)
            strings.add(shortener.getString(key));

        return new Date().getTime() - dateStart.getTime();
    }

    @Test
    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        Set<Long> keys = new HashSet<>();

        long time1 = getTimeForGettingIds(shortener1, origStrings, keys);
        long time2 = getTimeForGettingIds(shortener2, origStrings, keys);

        Assert.assertTrue(time1 > time2);

        time1 = getTimeForGettingStrings(shortener1, keys, origStrings);
        time2 = getTimeForGettingStrings(shortener2, keys, origStrings);

        Assert.assertEquals(time1, time2, 5);

    }
}

