package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
    C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) {

        List<String> fileNames = new ArrayList<>();
        String outFullFileName = "";
        if (args == null || args.length < 2)
            return;
        if (args[0] != null && !args[0].isEmpty())
            outFullFileName = args[0];
        else
            return;
        for (int i = 1; i < args.length; i++)
        {
            if (args[i] != null && !args[i].isEmpty())
                fileNames.add(args[i]);
            else
                return;
        }
        Collections.sort(fileNames);

        try
        {
            File outFile = new File(outFullFileName);
            FileOutputStream fos = new FileOutputStream(outFile);
            String outFileName = outFile.toPath().getFileName().toString();

            List<FileInputStream> listFileInputStream = new ArrayList<>();
            for (String fileName : fileNames)
            {
                listFileInputStream.add(new FileInputStream(fileName));
            }
            ZipInputStream zip = new ZipInputStream(new SequenceInputStream(Collections.enumeration(listFileInputStream)));
            ZipEntry entry = zip.getNextEntry();
            //while((entry = zip.getNextEntry()) != null)
           // {
                //if (!entry.getName().equals(outFileName))
                //    continue;
            int count = 0;

            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((count = zip.read(buffer)) != -1)
            {
                baos.write(buffer, 0, count);

            }
            fos.write(baos.toByteArray());
            baos.close();
            zip.closeEntry();
            //}
            zip.close();
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
