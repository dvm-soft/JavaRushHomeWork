package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {

        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");

        if (args.length != 2)
            return;

        try
        {
            BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(args[0]));
            BufferedOutputStream fileOutput = new BufferedOutputStream(new FileOutputStream(args[1]));
            byte[] buffer = new byte[fileInput.available()];
            fileInput.read(buffer);
            String s = new String(buffer, utf8);
            buffer = s.getBytes(windows1251);
            fileOutput.write(buffer);
            fileInput.close();
            fileOutput.close();
        } catch (IOException e) {}



    }
}
