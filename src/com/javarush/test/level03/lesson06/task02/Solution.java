package com.javarush.test.level03.lesson06.task02;

/* Таблица умножения
Выведи на экран таблицу умножения 10 на 10 в следующем виде:
1 2 3 …
2 4 6 …
3 6 9 …
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        String row;
        for(int x = 1; x <=10; x = x+1)
        {
            row = "";
            for (int y = 1; y <= 10; y = y + 1)
            {
                row  = row + x*y + " ";
            }
            System.out.println(row);
        }
    }
}