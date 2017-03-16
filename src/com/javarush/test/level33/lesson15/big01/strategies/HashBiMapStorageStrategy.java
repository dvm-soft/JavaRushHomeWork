package com.javarush.test.level33.lesson15.big01.strategies;

import com.google.common.collect.HashBiMap;

import java.util.Map;

/**
 * Created by 1 on 22.01.2017.
 */
public class HashBiMapStorageStrategy implements StorageStrategy
{

    private HashBiMap<Long, String> data = HashBiMap.create();

    @Override
    public boolean containsKey(Long key)
    {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value)
    {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value)
    {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value)
    {
        return data.inverse().get(value);
/*        if (containsValue(value))
        {
            for (Map.Entry<Long, String> pair : data.entrySet())
            {
                if (value.equals(pair.getValue()))
                    return pair.getKey();
            }
        }
        return null;
        */
    }

    @Override
    public String getValue(Long key)
    {
        return data.get(key);
    }
}
