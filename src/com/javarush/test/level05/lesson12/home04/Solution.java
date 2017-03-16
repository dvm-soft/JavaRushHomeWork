package com.javarush.test.level05.lesson12.home04;

/* Вывести на экран сегодняшнюю дату
Вывести на экран текущую дату в аналогичном виде "21 02 2014".
*/

import java.util.Date;
import java.text.SimpleDateFormat;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        System.out.println(format.format(d));

    }
}
