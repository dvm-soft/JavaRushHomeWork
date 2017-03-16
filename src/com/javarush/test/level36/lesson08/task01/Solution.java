package com.javarush.test.level36.lesson08.task01;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        byte[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
        Arrays.sort(alphabet);
        TreeSet<String> set = new TreeSet<>();

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(args[0])))
        {
            byte[] bytes = new byte[bis.available()];
            bis.read(bytes);
            for (int i = 0; i < bytes.length; i++)
                if (Arrays.binarySearch(alphabet, bytes[i]) >= 0)
                    set.add(Character.toString((char) bytes[i]).toLowerCase());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        int count = 0;
        for (String litera : set)
        {
            System.out.print(litera);
            if (++count == 5) break;
        }

    }
}
