package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileOutput = reader.readLine();

        List<String> list = new ArrayList<String>();

        while (true)
        {
            String str = reader.readLine();
            list.add(str);
            if ("exit".equals(str)) break;
        }

        FileOutputStream outputStream = new FileOutputStream(fileOutput);

        for (String str : list)
        {
            outputStream.write(str.getBytes());
            outputStream.write('\n');
        }

        outputStream.close();
        reader.close();

    }
}
