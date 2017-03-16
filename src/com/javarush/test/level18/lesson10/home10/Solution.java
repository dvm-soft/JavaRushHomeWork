package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args)  throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String realFileName = "";
        Map<Integer, byte[]> map = new TreeMap<Integer, byte[]>();

        while (!"end".equals(fileName))
        {
            Pattern p = Pattern.compile("\\.part");
            String[] m = p.split(fileName);
            if (m.length >= 2)
            {
                realFileName = "";
                int partNumber = 0;
                for (int i = 0; i < m.length-1; i++)
                    realFileName += m[i];
                partNumber = Integer.parseInt(m[m.length - 1]);

                BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
                byte[] buff = new byte[file.available()];
                file.read(buff);
                map.put(partNumber, buff);
                file.close();
            }
            fileName = reader.readLine();
        }
        reader.close();

        BufferedOutputStream outputFile = new BufferedOutputStream(new FileOutputStream(realFileName));
        for (Map.Entry<Integer, byte[]> pair : map.entrySet())
            outputFile.write(pair.getValue());
        outputFile.close();

    }
}
