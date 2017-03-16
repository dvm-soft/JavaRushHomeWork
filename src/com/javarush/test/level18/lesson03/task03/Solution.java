package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream file = new FileInputStream(fileName);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        while (file.available() > 0)
        {
            Integer a = file.read();
            if (map.containsKey(a))
                map.put(a, map.get(a)+1);
            else
                map.put(a, 1);
        }
        file.close();

        int maxCount = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (pair.getValue() > maxCount)
                maxCount = pair.getValue();
        }
        String Result = "";
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (pair.getValue().equals(maxCount))
                Result += ((Result.length() == 0) ? "" + pair.getKey(): " " + pair.getKey());
        }
        System.out.println(Result);
    }
}
