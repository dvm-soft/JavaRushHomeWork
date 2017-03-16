package com.javarush.test.level03.lesson04.task01;

/* Дата рождения
Вывести на экран дату своего рождения в виде: MAY 1 2012
*/


import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Locale;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Date bithDay = new Date(72,03,10);
        DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy",Locale.ENGLISH);
        String strDate = dateFormat.format(bithDay).toUpperCase();
        System.out.print(strDate);
    }
}