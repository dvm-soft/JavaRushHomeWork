package com.javarush.test.level04.lesson13.task03;

/* Рисуем треугольник
Используя цикл for вывести на экран прямоугольный треугольник из восьмёрок со сторонами 10 и 10.
Пример:
8
88
888
...
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код

        for (int i=1; i<=10; i++){
            for(int j=1; j<=i; j++){
                if (j==i)
                    System.out.println("8");
                else
                    System.out.print("8");
            }

        }

    }
}
