package com.javarush.test.level08.lesson11.home09;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
    }

    public static boolean isDateOdd(String date) throws Exception
    {
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
        Date currDate = format.parse(date);
        Date startDate = (Date)currDate.clone();
        startDate.setDate(1);
        startDate.setMonth(0);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        long intervalMs = currDate.getTime() - startDate.getTime();
        long daysCount = intervalMs / (1000 * 60 * 60 * 24) + 1;

        System.out.println(daysCount);

        if (daysCount % 2 == 0)
            return false;
        else
            return true;
    }
}
