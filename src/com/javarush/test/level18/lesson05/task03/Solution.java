package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = "", fileName2 = "", fileName3 = "";
        int fileSize = 0, bufferSize1 = 0, bufferSize2 = 0;
        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
            fileName3 = reader.readLine();
            reader.close();

            FileInputStream fileInput = new FileInputStream(fileName1);
            fileSize = fileInput.available();
            bufferSize1 = fileSize / 2;
            bufferSize2 = fileSize / 2;
            if (fileSize % 2 == 1)
                bufferSize1 ++;

            byte[] buff1 = new byte[bufferSize1];
            byte[] buff2 = new byte[bufferSize2];

            fileInput.read(buff1);
            fileInput.read(buff2);
            fileInput.close();

            FileOutputStream fileOutput = new FileOutputStream(fileName2);
            fileOutput.write(buff1);
            fileOutput.close();

            fileOutput = new FileOutputStream(fileName3);
            fileOutput.write(buff2);
            fileOutput.close();


        } catch (IOException e) {}

    }
}
