package com.javarush.test.level25.lesson09.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {



    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<String> list = new ArrayList<String>();
        list.add(0, e.toString());
        Throwable ee = e.getCause();
        while(ee != null)
        {
            list.add(0, ee.toString());
            ee = ee.getCause();
        }
        for (String s : list)
            System.out.println(s);

    }
}
