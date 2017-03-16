package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        //напишите тут ваш код
        char[] array = s.toCharArray();
        s = "";
        for (int i = 0; i < array.length; i++)
        {
            char prev = ' ';
            if (i == 0)
                prev = ' ';
            else
                prev = array[i-1];
            if ((prev == ' ') & (array[i] != ' '))
                s += Character.toUpperCase(array[i]);
            else
                s += array[i];

        }
        System.out.println(s);

    }


}
