package com.javarush.test.level40.lesson10.task01;

/* Работа с датами
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        String dateStr = null;
        String timeStr = null;
        Pattern p = Pattern.compile("(\\d{1,2}\\.\\d{1,2}\\.\\d{4})?\\s?(\\d{2}:\\d{2}:\\d{2})?");
        Matcher m = p.matcher(date);
        if (m.find())
        {
            dateStr = m.group(1);
            timeStr = m.group(2);
        }

        try
        {
            DateFormat format = new SimpleDateFormat("d.M.yyyy HH:mm:ss", Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            if (dateStr != null)
            {
                Date datetime = format.parse(dateStr + " " + "00:00:00");
                calendar.setTime(datetime);
                System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println("День недели: " + calendar.get(Calendar.DAY_OF_WEEK));
                System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
                System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
                System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
                System.out.println("Месяц: " + calendar.get(Calendar.MONTH));
                System.out.println("Год: " + calendar.get(Calendar.YEAR));
                System.out.println("Эра: " + calendar.get(Calendar.ERA));
            }
            if (timeStr != null)
            {
                Date datetime = format.parse("1.10.2000" + " " + timeStr);
                calendar.setTime(datetime);
                System.out.println("AM или PM: " + calendar.get(Calendar.AM_PM));
                System.out.println("Часы: " + calendar.get(Calendar.HOUR));
                System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
                System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
                System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

    }
}
