package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedReader fileInput = new BufferedReader(new FileReader(fileName1));
        BufferedWriter fileOutput = new BufferedWriter(new FileWriter(fileName2));
        String str = fileInput.readLine();
        while (str != null)
        {
            fileOutput.write(str.replace('.', '!')+"\n");
            str = fileInput.readLine();
        }
        fileInput.close();
        fileOutput.close();
    }
}
