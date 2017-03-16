package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Solution {
    public static void main(String[] args) throws Exception{
        String fileName = args[0];
        BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
        int countSpace = 0, count;
        count = file.available();
        while(file.available() > 0)
        {
            int ch = file.read();
            if (ch == 0x20)
                countSpace++;
        }
        System.out.println(String.format("%.2f", countSpace*100.00/count));
        file.close();
    }
}
