package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
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

        if((a1>=a2)&(a1>=a3)) {
            System.out.print(a1);
            if (a2>=a3)
                System.out.println(" "+a2+" "+a3);
            else
                System.out.println(" "+a3+" "+a2);

        }
        if((a2>=a1)&(a2>=a3)) {
            System.out.print(a2);
            if (a1>=a3)
                System.out.println(" "+a1+" "+a3);
            else
                System.out.println(" "+a3+" "+a1);

        }
        if((a3>=a1)&(a3>=a2)) {
            System.out.print(a3);
            if (a1>=a2)
                System.out.println(" "+a1+" "+a2);
            else
                System.out.println(" "+a2+" "+a1);

        }


    }
}
