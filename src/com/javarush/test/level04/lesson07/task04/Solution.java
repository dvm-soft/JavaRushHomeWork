package com.javarush.test.level04.lesson07.task04;

/* Положительные и отрицательные числа
Ввести с клавиатуры три целых числа. Вывести на экран количество положительных и количество отрицательных чисел в исходном наборе,
в следующем виде:
"количество отрицательных чисел: а", "количество положительных чисел: б", где а, б - искомые значения.
Пример для чисел 2 5 6:
количество отрицательных чисел: 0
количество положительных чисел: 3
Пример для чисел -2 -5 6:
количество отрицательных чисел: 2
количество положительных чисел: 1
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a1 = Integer.parseInt(reader.readLine());
        int a2 = Integer.parseInt(reader.readLine());
        int a3 = Integer.parseInt(reader.readLine());

        int countPozitive = 0;
        int countNegative = 0;
        if (a1>0) countPozitive++;
        if (a2>0) countPozitive++;
        if (a3>0) countPozitive++;
        if (a1<0) countNegative++;
        if (a2<0) countNegative++;
        if (a3<0) countNegative++;
        System.out.println("количество отрицательных чисел: "+countNegative);
        System.out.println("количество положительных чисел: "+countPozitive);

    }
}
