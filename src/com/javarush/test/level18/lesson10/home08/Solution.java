package com.javarush.test.level18.lesson10.home08;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public volatile static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        while (!"exit".equals(fileName))
        {
            new ReadThread(fileName).start();
            fileName = reader.readLine();
        }
        reader.close();
        for (Map.Entry<String, Integer> pair : resultMap.entrySet())
        {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }


    }

    public static class ReadThread extends Thread {
        public String fileName = "";
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run()
        {
            try
            {
                BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                while (file.available() > 0)
                {
                    Integer a = file.read();
                    if (map.containsKey(a))
                        map.put(a, map.get(a)+1);
                    else
                        map.put(a, 1);
                }
                file.close();

                if (map.size() > 0)
                {
                    Integer maxCount = 0, maxSymbol = 0;
                    for (Map.Entry<Integer, Integer> pair : map.entrySet())
                    {
                        if (maxCount < pair.getValue())
                        {
                            maxCount = pair.getValue();
                            maxSymbol = pair.getKey();
                        }
                    }
                    resultMap.put(fileName, maxSymbol);
                }
            } catch (Exception e) {}

        }
    }
}
