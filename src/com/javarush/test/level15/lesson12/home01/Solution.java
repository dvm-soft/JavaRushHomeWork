package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения, кроме "exit", вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для String.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString;
        while (!"exit".equals(inputString = reader.readLine()))
        {
            if (inputString.contains("."))
            {
                print(Double.parseDouble(inputString));
                continue;
            }
            else
            {
                int inputNum;
                try {
                    inputNum = Integer.parseInt(inputString);
                    if ((Integer.parseInt(inputString) > 0) & (Integer.parseInt(inputString) < 128))
                    {
                        print((short) Integer.parseInt(inputString));
                        continue;
                    }
                    else if (Integer.parseInt(inputString) >= 128)
                    {
                        print(Integer.parseInt(inputString));
                        continue;
                    }
                } catch (Exception e) {}
                print(inputString);
            }
        }

        reader.close();
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
