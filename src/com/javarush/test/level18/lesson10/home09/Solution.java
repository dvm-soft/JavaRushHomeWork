package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String fileName = "";
        BufferedInputStream file = null;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                while (true)
                {
                    fileName = reader.readLine();
                    file = new BufferedInputStream(new FileInputStream(fileName));
                    file.close();
                }
            } catch (FileNotFoundException e)
            {
                System.out.println(fileName);
            }
            reader.close();
            file.close();
        }catch (IOException e) {}
    }
}
