package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
    public static void main(String[] args)
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = "";
        try
        {
            url = reader.readLine();
            //url = "http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo";
            //url = "http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo";
            reader.close();
        }
        catch (IOException e)
        {
        }
        url = url.substring(url.indexOf("?")+1);
        String objValue = "", keyString = "";
        for ( String pair : url.split("&"))
        {
            String key = null, value = null;
            if(pair.contains("="))
            {
                key = pair.split("=")[0];
                value = pair.split("=")[1];
            }
            else
                key = pair;
            if (keyString.isEmpty())
                keyString = key;
            else
                keyString += " " + key;
            if ("obj".equals(key))
                objValue = value;

        }
        System.out.println(keyString);
        if (!objValue.isEmpty())
            try
            {
                alert( Double.parseDouble(objValue));
            } catch (Exception e)
            {
                alert(objValue);
            }



    }
    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
