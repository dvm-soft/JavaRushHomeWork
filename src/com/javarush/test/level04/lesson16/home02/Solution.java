package com.javarush.test.level04.lesson16.home02;

import java.io.*;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a1 = Integer.parseInt(reader.readLine());
        int a2 = Integer.parseInt(reader.readLine());
        int a3 = Integer.parseInt(reader.readLine());

        float midle = (a1+a2+a3)/3;
        double b1 = Math.pow((a1-midle),2);
        double b2 = Math.pow((a2-midle),2);
        double b3 = Math.pow((a3-midle),2);
        //System.out.println(a1+" "+a2+" "+a3+" "+midle+" "+b1+" "+b2+" "+b3);

        if ((b1<=b2)&(b1<=b3))
            System.out.println(a1);
        else
            if ((b2<=b1)&(b2<=b3))
                System.out.println(a2);
            else
                System.out.println(a3);

    }
}
