package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String str = file.readLine();
        int count = 0;
        while (str != null)
        {
            String[] words = str.split("[,|.|!|?| |;|:]");
            for (String word : words)
                if("world".equals(word))
                    count++;
            str = file.readLine();
        }
        System.out.println(count);
        file.close();
    }
}
