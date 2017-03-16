package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static String fileName = "";
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
//        fileName = "c:/!/goods2.txt";
        reader.close();

        if ("-c".equals(args[0]) && args.length == 4)
            createProduct(args[1], Double.parseDouble(args[2]), Integer.parseInt(args[3]));


    }

    public static void createProduct(String productName, double price, int quantity) throws Exception
    {
        FileWriter file = new FileWriter(fileName, true);
        int id = getId();
        file.write(String.format("%-8d%-30s%-8.2f%-4d\n", id, productName, price, quantity));
        file.close();

    }

    public static int getId() throws Exception
    {
        int id = 0;
        BufferedReader fr = new BufferedReader(new FileReader(fileName));
        String line = fr.readLine();
        while (line != null)
        {
            int tmpId = Integer.parseInt( (line.substring(0,8)).trim());
            if (id < tmpId)
                id = tmpId;
            line = fr.readLine();
        }
        fr.close();
        return id+1;
    }


}
