package com.javarush.test.level12.lesson04.task02;

/* print(int) и print(Integer)
Написать два метода: print(int) и print(Integer).
Написать такой код в методе main, чтобы вызвались они оба.
*/

public class Solution
{
    public static void main(String[] args)
    {
        int a  = 5;
        print(a);
        print((Integer) a);
    }

    //Напишите тут ваши методы
    public static void print(int a)
    {
        System.out.println(a + " int");
    }
    public static void print(Integer a)
    {
        System.out.println(a +" Integer");
    }
}
