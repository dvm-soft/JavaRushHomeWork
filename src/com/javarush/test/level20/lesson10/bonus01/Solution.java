package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static void main(String[] args)
    {
        for (int i : getNumbers(400000000))
            System.out.println(i);
    }

    public static int[] getNumbers(int N) {

        int x = 10, y = ((Integer) N).toString().length() + 1;
        int [][] a = new int [x] [y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                a[i][j] = (int) Math.pow(i, j);
            }
        }
        System.out.println("таблица готова");

        List<Integer> array = new ArrayList<Integer>();
        for (Integer i = 1; i < N; i++)
        {
            String iString= i.toString();
            int digitCount = iString.length();
            int summa = 0;
            for (int j = 0; j < digitCount ; j++)
            {
                summa += a [Character.getNumericValue(iString.charAt(j))][digitCount];
                if (summa > i)
                    break;
            }
            if (summa == i)
                array.add(i);
        }

        Collections.sort(array);
        int[] result = new int[array.size()];

        for (int i = 0; i < result.length ; i++)
        {
            result[i] = array.get(i);
        }

        return result;
    }
}
