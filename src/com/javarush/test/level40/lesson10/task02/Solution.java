package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
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

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

            if (dateStr != null)
            {
                DateTime datetime = new DateTime(format.parse(dateStr + " " + "00:00:00"));
                System.out.println("День: " + datetime.getDayOfMonth());
                int weekday = datetime.getDayOfWeek() == 7 ? 1 : datetime.getDayOfWeek() + 1;
                System.out.println("День недели: " + weekday);
                //System.out.println("День недели: " + (datetime.getDayOfWeek() + 1));
                System.out.println("День месяца: " + datetime.getDayOfMonth());
                System.out.println("День года: " + datetime.getDayOfYear());
                //System.out.println("Неделя месяца: " + (datetime.getWeekOfWeekyear() - datetime.withDayOfMonth(1).getWeekOfWeekyear() + 1));
                //System.out.println("Неделя года: " + datetime.getWeekOfWeekyear());


                DateTime minYearDate = datetime.dayOfYear().withMinimumValue();
                DateTime minMonthDate = datetime.dayOfMonth().withMinimumValue();
                int weekDis = (minYearDate.getWeekyear() == datetime.getYear()) ? 0 : 1;
                int weekOfYear = datetime.getWeekOfWeekyear() + weekDis;
                if (weekOfYear >= 53)
                    weekOfYear = 1;
                int weekOfMonth = datetime.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
                if (minMonthDate.getWeekOfWeekyear() >= datetime.getWeekOfWeekyear())
                    weekOfMonth = datetime.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
                if (datetime.getMonthOfYear() == 1)
                    weekOfMonth = weekOfYear;
                System.out.println("Неделя месяца: " + weekOfMonth);
                System.out.println("Неделя года: " + weekOfYear);

                System.out.println("Месяц: " + (datetime.getMonthOfYear() - 1));
                System.out.println("Год: " + datetime.getYear());
                System.out.println("Эра: " + datetime.getEra());
            }
            if (timeStr != null)
            {
                DateTime datetime = new DateTime(format.parse("1.10.2000" + " " + timeStr));
                System.out.println("AM или PM: " + datetime.get(DateTimeFieldType.halfdayOfDay()));
                System.out.println("Часы: " + datetime.get(DateTimeFieldType.hourOfHalfday()));
                System.out.println("Часы дня: " + datetime.getHourOfDay());
                System.out.println("Минуты: " + datetime.getMinuteOfHour());
                System.out.println("Секунды: " + datetime.getSecondOfMinute());
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
