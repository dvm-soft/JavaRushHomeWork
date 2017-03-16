package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        String fileName = args[0];
        BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
        int count = 0;
        while(file.available() > 0)
        {
            int ch = file.read();
            if (ch >= 'A' & ch <= 'z')
                count++;
        }
        System.out.println(count);
        file.close();

    }
}
