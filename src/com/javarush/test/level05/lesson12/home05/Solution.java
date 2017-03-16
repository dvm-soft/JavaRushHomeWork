package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strInput = reader.readLine();
        int summa = 0;

        while (!"сумма".equals(strInput)) {
            try
            {
                summa += Integer.parseInt(strInput);
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
            strInput = reader.readLine();

        }

        System.out.println(summa);


    }
}
