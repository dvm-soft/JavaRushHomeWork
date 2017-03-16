package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static byte key = (byte)0xAA;
    public static void main(String[] args) throws Exception{
        if (args.length == 3)
        {
            String fileName = args[1];
            String fileOutputName = args[2];

            BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));

            byte[] buff = new byte[file.available()];
            file.read(buff);
            file.close();

            if ("-e".equals(args[0]))
                encrypt(buff);

            if ("-d".equals(args[0]))
                dencrypt(buff);

            BufferedOutputStream fileOutput = new BufferedOutputStream(new FileOutputStream(fileOutputName));
            fileOutput.write(buff);
            fileOutput.close();
        }
    }

    public static void encrypt (byte[] buff)
    {
        for (int i = 0; i < buff.length; i++)
            buff[i] = (byte)(buff[i]^key);
        return;
    }

    public static void dencrypt (byte[] buff)
    {
        for (int i = 0; i < buff.length; i++)
            buff[i] = (byte)(buff[i]^key);
        return;
    }
}
