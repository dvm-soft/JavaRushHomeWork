package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        reader.close();

        BufferedOutputStream fileWriter1 = new BufferedOutputStream(new FileOutputStream(fileName1));
        BufferedInputStream fileReader2 = new BufferedInputStream(new FileInputStream(fileName2));
        BufferedInputStream fileReader3 = new BufferedInputStream(new FileInputStream(fileName3));

        while (fileReader2.available() > 0)
            fileWriter1.write(fileReader2.read());
        while (fileReader3.available() > 0)
            fileWriter1.write(fileReader3.read());

        fileWriter1.close();
        fileReader2.close();
        fileReader3.close();
    }
}
