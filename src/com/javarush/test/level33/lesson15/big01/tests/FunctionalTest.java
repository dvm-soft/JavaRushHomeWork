package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 1 on 22.01.2017.
 */
public class FunctionalTest
{
    public void testStorage(Shortener shortener)
    {
        String str1 = Helper.generateRandomString();
        String str2 = Helper.generateRandomString();
        String str3 = new String(str1);

        Long key1 = shortener.getId(str1);
        Long key2 = shortener.getId(str2);
        Long key3 = shortener.getId(str3);

        Assert.assertNotEquals(key2, key1);
        Assert.assertNotEquals(key2, key3);

        Assert.assertEquals(key1, key3);

        String strTest1 = shortener.getString(key1);
        String strTest2 = shortener.getString(key2);
        String strTest3 = shortener.getString(key3);

        Assert.assertEquals(str1, strTest1);
        Assert.assertEquals(str2, strTest2);
        Assert.assertEquals(str3, strTest3);
    }

    @Test
    public void testHashMapStorageStrategy()
    {
        StorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        StorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy()
    {
        StorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy()
    {
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        StorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}


