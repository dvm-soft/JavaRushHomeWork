package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 0; i < array.length-1; i++)
        {
            int c;
            if (array[i] > array[i+1]) {
                c = array[i];
                array[i] = array[i+1];
                array[i+1] = c;
                i = -1;
            }
        }

        for (int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }

    }
}
