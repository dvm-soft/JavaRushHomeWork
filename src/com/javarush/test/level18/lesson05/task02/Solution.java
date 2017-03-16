package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        int Counter = 0;
        try
        {
            fileName = reader.readLine();
            reader.close();
            FileInputStream file = new FileInputStream(fileName);
            while (file.available() > 0)
            {
                int a = file.read();
                if (a == ((int)','))
                    Counter++;
            }
            file.close();

        } catch (IOException e) {}
        System.out.println(Counter);
    }
}
