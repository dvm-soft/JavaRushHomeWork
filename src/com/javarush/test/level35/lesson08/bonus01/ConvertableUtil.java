package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <Key, T extends Convertable<Key>> Map<Key, T> convert(List<T> list) {
        Map<Key, T> result = new HashMap<Key, T>();

        for (T item : list)
            result.put(item.getKey(), item);
        return result;
    }
}
