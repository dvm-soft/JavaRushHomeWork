package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static String fileName = "";
    public static List<String> list = new ArrayList<String>();

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
//        fileName = "c:/!/goods2.txt";
        reader.close();


        if ("-d".equals(args[0]) && args.length == 2)
            deleteProduct(Integer.parseInt(args[1]));

        if ("-u".equals(args[0]) && args.length == 5)
            updateProduct(Integer.parseInt(args[1]), args[2], Double.parseDouble(args[3]), Integer.parseInt(args[4]));

    }

    public static void deleteProduct(int id) throws Exception
    {
        loadList();
        for (int i = 0; i < list.size(); i++)
        {
            int tmpId = Integer.parseInt((list.get(i).substring(0,8)).trim());
            if (id == tmpId)
                list.remove(i);
        }

        saveList();
    }

    public static void updateProduct(int id, String productName, double price, int quantity) throws Exception
    {
        loadList();
        for (int i = 0; i < list.size(); i++)
        {
            int tmpId = Integer.parseInt((list.get(i).substring(0,8)).trim());
            if (id == tmpId)
                list.set(i, String.format("%-8d%-30s%-8.2f%-4d", id, productName, price, quantity));
        }

        saveList();
    }

    public static void loadList() throws Exception
    {
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while (line != null)
        {
            list.add(line);
            line = file.readLine();
        }
        file.close();
    }
    public static void saveList() throws Exception
    {
        BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
        for (String line : list)
            file.write(line+"\n");
        file.close();
    }


}
