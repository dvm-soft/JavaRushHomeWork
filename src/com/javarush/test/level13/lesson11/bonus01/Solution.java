package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

//        fileName = "c:\\!\\a.txt";
        reader = new BufferedReader(new FileReader(fileName));
        List<Integer> array = new ArrayList<Integer>();
        String str = "";

        while (( str = reader.readLine())!= null)
        {
            try
            {
                array.add(Integer.parseInt(str));
            }
            catch (Exception e) {}
        }
        reader.close();


        for (int i = 0; i < array.size() - 1; i++)
        {
            if (array.get(i) > array.get(i+1))
            {
                Collections.swap(array, i, i+1);
                i = -1;
            }
        }
        for (int a : array)
        {
            if (a % 2 == 0)
                System.out.println(a);
        }
    }
}
