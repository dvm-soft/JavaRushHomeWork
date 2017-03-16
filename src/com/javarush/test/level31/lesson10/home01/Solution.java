package com.javarush.test.level31.lesson10.home01;

import java.io.*;
import java.util.Properties;

/* Читаем конфиги
Реализовать метод getProperties, который должен считывать свойства из переданного файла fileName.
fileName может иметь любое расширение - как xml, так и любое другое, или вообще не иметь.
Нужно обеспечить корректное чтение свойств.
При возникновении ошибок должен возвращаться пустой объект.
Метод main не участвует в тестировании.
Подсказка: возможно, Вам понадобится File.separator.
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String firstLine = reader.readLine();
            reader.close();
            if (fileName.endsWith(".xml") || firstLine.contains("<?xml version=\"1.0\""))
            {
                Properties properties = new Properties();
                properties.loadFromXML(new FileInputStream(fileName));
                return properties;
            }
            else
            {
                Properties properties = new Properties();
                properties.load(new FileInputStream(fileName));
                return properties;
            }
        }
        catch (IOException e)
        {
            return new Properties();
        }

    }
}
