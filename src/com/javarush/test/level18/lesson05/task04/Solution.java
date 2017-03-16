package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = "", fileName2 = "";
        int fileSize = 0;
        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
            reader.close();

            FileInputStream fileInput = new FileInputStream(fileName1);
            fileSize = fileInput.available();
            byte[] buff = new byte[fileSize];

            fileInput.read(buff);
            fileInput.close();

            FileOutputStream fileOutput = new FileOutputStream(fileName2);
            for (int i = buff.length - 1; i >= 0; i--)
                fileOutput.write(buff[i]);
            fileOutput.close();

        } catch (IOException e) {}

    }
}
