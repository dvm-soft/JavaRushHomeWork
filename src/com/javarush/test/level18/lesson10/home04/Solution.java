package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedInputStream fileReader1 = new BufferedInputStream(new FileInputStream(fileName1));
        byte[] buff1 = new byte[fileReader1.available()];
        fileReader1.read(buff1);
        BufferedInputStream fileReader2 = new BufferedInputStream(new FileInputStream(fileName2));
        byte[] buff2 = new byte[fileReader2.available()];
        fileReader2.read(buff2);
        fileReader1.close();
        fileReader2.close();

        BufferedOutputStream fileWriter1 = new BufferedOutputStream(new FileOutputStream(fileName1));
        fileWriter1.write(buff2);
        fileWriter1.write(buff1);

        fileWriter1.close();


    }
}
