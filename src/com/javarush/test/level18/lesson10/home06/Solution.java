package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        String fileName = args[0];

        BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        while(file.available() > 0)
        {
            Integer ch = file.read();
            if (map.containsKey(ch))
                map.put(ch, map.get(ch)+1);
            else
                map.put(ch, 1);
        }

        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            System.out.println( (char)(int)(pair.getKey()) + " " + pair.getValue());
        }
        file.close();

    }
}
