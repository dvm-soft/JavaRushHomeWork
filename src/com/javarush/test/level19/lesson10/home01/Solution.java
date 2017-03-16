package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
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
        for (Map.Entry<String, Double> pair : map.entrySet())
            System.out.println(pair.getKey() + " " + pair.getValue());
    }
}
