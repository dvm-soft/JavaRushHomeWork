package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
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

        int minCount = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (minCount == 0)
                minCount = pair.getValue();
            if (pair.getValue() < minCount)
                minCount = pair.getValue();
        }
        String Result = "";
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (pair.getValue().equals(minCount))
                Result += ((Result.length() == 0) ? "" + pair.getKey(): " " + pair.getKey());
        }
        System.out.println(Result);
    }
}
