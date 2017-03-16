package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedInputStream fileRead = new BufferedInputStream(new FileInputStream(fileName1));
        BufferedOutputStream fileWrite = new BufferedOutputStream(new FileOutputStream(fileName2));
        int i = 1;
        while (fileRead.available() > 0)
        {
            int a = fileRead.read();
            if (i % 2 == 0)
                fileWrite.write(a);
            i++;
        }

        fileRead.close();
        fileWrite.close();

    }

}
