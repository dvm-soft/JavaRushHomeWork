package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        //BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "cp1251"));
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        Map<String,Double> map = new TreeMap<String, Double>();
        while (file.ready())
        {
            String[] value = file.readLine().split(" ");
            if ( value.length == 2)
            {
                if (map.containsKey(value[0]))
                    map.put(value[0], map.get(value[0]) + Double.parseDouble(value[1]));
                else
                    map.put(value[0], Double.parseDouble(value[1]));
            }
        }
        file.close();
        Double max = 0.0;
        for (Map.Entry<String, Double> pair : map.entrySet())
            if (max < pair.getValue())
                max = pair.getValue();

        for (Map.Entry<String, Double> pair : map.entrySet())
            if (pair.getValue() == max)
                System.out.println(pair.getKey());
    }
}
