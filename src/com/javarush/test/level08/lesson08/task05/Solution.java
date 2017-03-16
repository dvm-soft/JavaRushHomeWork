package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{


    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("lastname", "name");
        map.put("lastname1", "name1");
        map.put("lastname2", "name");
        map.put("lastname3", "name1");
        map.put("lastname4", "name");
        map.put("lastname5", "name3");
        map.put("lastname6", "name");
        map.put("lastname7", "name4");
        map.put("lastname8", "name5");
        map.put("lastname9", "name5");

        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //напишите тут ваш код
        HashSet<String> set = new HashSet<String>();

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();

            if (set.contains(pair.getValue()))
            {
                removeItemFromMapByValue(map, pair.getValue());
                iterator = map.entrySet().iterator();
            }
            else
                set.add(pair.getValue());
        }

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
