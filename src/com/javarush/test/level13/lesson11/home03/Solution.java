package com.javarush.test.level13.lesson11.home03;

/* Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
*/

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        InputStream fileInput = new FileInputStream(fileName);

        while (fileInput.available()>0) {

            List<Integer> intString = new ArrayList<Integer>();
            while (fileInput.available()>0)
            {
                byte[] b = new byte[255];
            }
            char ch = (char) fileInput.read();
            System.out.print(ch);
        }
        fileInput.close();
        reader.close();
    }

}
