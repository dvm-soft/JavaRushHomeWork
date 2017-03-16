package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws  Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedReader fileInput = new BufferedReader(new FileReader(fileName1));
        BufferedWriter fileOutput = new BufferedWriter(new FileWriter(fileName2));
        String str = fileInput.readLine();
        int Count = 0;
        while (str != null)
        {
            String[] words = str.split(" ");
            for (String word : words)
            {
                try {
                    int i = Integer.parseInt(word);
                    if(Count != 0)
                        fileOutput.write(" " + i);
                    else
                    {
                        fileOutput.write(""+i);
                        Count++;
                    }
                } catch (Exception e) {}
            }
            str = fileInput.readLine();
        }
        fileInput.close();
        fileOutput.close();

    }
}
