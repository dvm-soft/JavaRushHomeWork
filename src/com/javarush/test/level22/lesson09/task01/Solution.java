package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.*;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        Map<String, Integer> words = new HashMap<String, Integer>();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
//            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"));
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
            String line = file.readLine();
            while(line != null)
            {
                String[] wordLine = line.split("\\s+");
                for (String str : wordLine)
                {
                    if(str.length() > 0)
                    {
                        if (words.containsKey(str))
                            words.put(str, words.get(str) + 1);
                        else
                            words.put(str, 1);
                    }
                }
                line = file.readLine();
            }
        } catch (Exception e) {}

//        for (Map.Entry<String, Integer> pair : words.entrySet())
//            System.out.println(pair.getKey() + " " + pair.getValue());
        for (Map.Entry<String, Integer> pair : words.entrySet())
        {
//            System.out.println(pair.getKey() + " " + pair.getValue());
            String drow = (new StringBuilder(pair.getKey())).reverse().toString();
            if (drow.equals(pair.getKey()) & pair.getValue() > 1)
                result.add(new Pair(drow, pair.getKey()));
            else if ((words.get(drow) == null ? false : words.get(drow) > 0) & (pair.getValue() > 0) & !drow.equals(pair.getKey()))
            {
                result.add(new Pair(drow, pair.getKey()));
                words.put(drow, 0);
            }

        }

        for (Pair pair : result)
            System.out.println(pair);

    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second)
        {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
