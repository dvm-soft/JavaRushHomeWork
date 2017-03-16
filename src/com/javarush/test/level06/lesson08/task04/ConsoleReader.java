package com.javarush.test.level06.lesson08.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Класс ConsoleReader
Сделать класс ConsoleReader, у которого будут 4 статических метода:
String readString() – читает с клавиатуры строку
int readInt() – читает с клавиатуры число
double readDouble() – читает с клавиатуры дробное число
boolean readBoolean() – читает с клавиатуры строку "true" или "false" и возвращает соответствующую логическую переменную true или false
Внимание: создавайте переменную для чтения данных с консоли (BufferedReader или Scanner) внутри каждого метода
*/

public class ConsoleReader
{
    public static String readString() throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static int readInt() throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean validInput = false;
        String strValue;
        int intValue = 0;
        while (!validInput) {
            strValue = reader.readLine();
            try {
                intValue = Integer.parseInt(strValue);
                validInput = true;
            } catch (Exception e) {
                validInput = false;
            }
    }
        return intValue;
    }

    public static double readDouble() throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean validInput = false;
        String strValue;
        double doubleValue = 0d;
        while (!validInput) {
            strValue = reader.readLine();
            try {
                doubleValue = Double.parseDouble(strValue);
                validInput = true;
            } catch (Exception e) {
                validInput = false;
            }
        }
        return doubleValue;

    }

    public static boolean readBoolean() throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strValue;
        boolean booleanValue = false;
        while (true) {
            strValue = reader.readLine();
            if ("true".equals(strValue)) {
                booleanValue = true;
                break;
            }
            else
                if ("false".equals(strValue)) {
                    booleanValue = false;
                    break;
                }
            }
        return booleanValue;
    }

}
