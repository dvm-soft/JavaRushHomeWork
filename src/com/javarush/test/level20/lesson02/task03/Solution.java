package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
/*
    public static void main(String[] args) throws Exception
    {
        Solution s = new Solution();
        s.fillInPropertiesMap();
    }
*/
    public void fillInPropertiesMap() throws Exception{
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        InputStream inputStream = new FileInputStream(fileName);
        load(inputStream);
        inputStream.close();
/*
        OutputStream outputStream = new FileOutputStream(fileName+"1");
        save(outputStream);
        outputStream.close();
*/
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties pr = new Properties();
        for (Map.Entry<String, String> pair : properties.entrySet())
            pr.put(pair.getKey(), pair.getValue());
        pr.store(outputStream, "test");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties pr = new Properties();
        pr.load(inputStream);

        for (Map.Entry<Object, Object> pair : pr.entrySet())
            properties.put(pair.getKey().toString(), pair.getValue().toString());

    }
}
