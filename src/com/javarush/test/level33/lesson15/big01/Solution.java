package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Дмитрий on 20.01.2017.
 */
public class Solution
{
    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> keys = new HashSet<>();
        for (String string : strings)
            keys.add(shortener.getId(string));
        return keys;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> Strings = new HashSet<>();
        for (Long key : keys)
            Strings.add(shortener.getString(key));
        return Strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++)
            testStrings.add(Helper.generateRandomString());

        Shortener shortener = new Shortener(strategy);

        Date date;

        date = new Date();
        Set<Long> keys = getIds(shortener, testStrings);
        Helper.printMessage(new Date().getTime() - date.getTime() + " ms");

        date = new Date();
        Set<String> outputStrings = getStrings(shortener, keys);
        Helper.printMessage(new Date().getTime() - date.getTime() + " ms");

        if (testStrings.equals(outputStrings))
            Helper.printMessage("Тест пройден.");

        else
            Helper.printMessage("Тест не пройден.");
    }
}

