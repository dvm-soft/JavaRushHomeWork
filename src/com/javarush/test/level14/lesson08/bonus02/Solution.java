package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a, b, min, max, nod = 0;
        a = Integer.parseInt(reader.readLine());
        b = Integer.parseInt(reader.readLine());
        min = (a > b) ? b : a;
        max = (a > b) ? a : b;
        for (int i = min; i > 0; i--)
        {
            if ((max % i) == 0)
            {
                nod = i;
                break;
            }
        }
        System.out.println(nod);
    }
}
