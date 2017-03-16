package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null)
            throw new TooShortStringException();

        StringBuilder result = new StringBuilder();
        int countTab = 0;

        for (int i = 0; i < string.length(); i++)
        {
            char ch = string.charAt(i);
            if (countTab > 0 & countTab < 2 & ch != '\t')
                result.append(ch);
            if (ch == '\t')
                countTab ++;
        }
        if (countTab < 2)
            throw new TooShortStringException();

        return result.toString();
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("tab0\ttab\ttab1\t"));       //tab
        System.out.println(getPartOfString("\t\t"));                    //
        System.out.println(getPartOfString("123\t123"));                //Exception
        System.out.println(getPartOfString(null));                      //Exception
    }
}
