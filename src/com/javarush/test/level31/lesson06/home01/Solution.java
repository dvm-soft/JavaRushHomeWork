package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        if (args == null || args.length != 2 || args[0] == null || args[0].isEmpty() || args[1] == null || args[1].isEmpty())
            return;
        String fileName = args[0];
        String zipFileName = args[1];
        Map<String, ByteArrayOutputStream> map = new HashMap<>();

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName)))
        {
            ZipEntry entry;
            while((entry = zin.getNextEntry()) != null)
            {
                int count = 0;

                byte[] byffer = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while((count = zin.read(byffer)) != -1)
                {
                    baos.write(byffer, 0, count);
                }
                map.put(entry.getName(), baos);
                baos.close();
                zin.closeEntry();
            }
        }

        try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFileName)))
        {
            File file = new File(fileName);

            for (Map.Entry<String, ByteArrayOutputStream> pair : map.entrySet())
            {
                if (pair.getKey().endsWith(fileName))
                {
                    zip.putNextEntry( new ZipEntry("new\\" + file.toPath().getFileName().toString()));
                    Files.copy(file.toPath(), zip);
                    zip.closeEntry();
                    continue;
                }
                else
                {
                    zip.putNextEntry(new ZipEntry(pair.getKey()));
                    zip.write(pair.getValue().toByteArray());
                    zip.closeEntry();
                }
            }
            zip.close();
        }

    }
}
