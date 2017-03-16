package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedReader fileReader1 = new BufferedReader(new FileReader(fileName1));
        String str = fileReader1.readLine();
        fileReader1.close();
        String[] strOut = str.split(" ");
        String Result = "";

        for (String a : strOut)
            Result += " " + (int)Math.round(Double.parseDouble(a));

        BufferedWriter fileWriter2 = new BufferedWriter(new FileWriter(fileName2));
        fileWriter2.write(Result.trim());
        fileWriter2.close();


    }
}
