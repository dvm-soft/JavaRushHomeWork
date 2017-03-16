package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        String paramId = args[0];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
//        fileName = "c:/!/goods.txt";
        reader.close();

        BufferedReader file = new BufferedReader(new FileReader(fileName));
        int  id, quantity;


        String strProduct = file.readLine();
        while (strProduct != null)
        {
            String[] product = strProduct.split(" ");
            if (product[0].equals(paramId))
            {
                System.out.println(strProduct);
                break;
            }
            strProduct = file.readLine();
        }
        file.close();

    }
}
