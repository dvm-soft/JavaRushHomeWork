package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static List<File> listForSave = new ArrayList<>();
    public static List<File> listForDelete = new ArrayList<>();

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[0].isEmpty() || args[1] == null || args[1].isEmpty())
            return;
        String path = args[0];
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/" + "allFilesContent.txt");
        File rootFolder = new File(path);
        if (!rootFolder.isDirectory())
        {
            System.out.println("Неверная корневая директория!");
            return;
        }
        Solution solution = new Solution();

        try
        {
            solution.processingFolder(rootFolder);

            Collections.sort(listForSave, new Comparator<File>()
            {
                @Override
                public int compare(File o1, File o2)
                {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file : listForDelete)
            {
                if (file.getCanonicalFile().equals(resultFileAbsolutePath.getCanonicalFile()))
                    continue;
                file.delete();
            }

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(resultFileAbsolutePath));
            for (File file : listForSave)
            {
                if (file.getCanonicalFile().equals(resultFileAbsolutePath.getCanonicalFile()))
                    continue;
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                out.write(buffer);
                out.write(System.lineSeparator().getBytes());
                in.close();

            }
            out.close();

            resultFileAbsolutePath.renameTo(allFilesContent);

            solution.deleteEmptyFolder(rootFolder);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public void processingFolder(File folder)
    {
        File[] files = folder.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
                processingFolder(file);
            else
            {
                if (file.getName().endsWith(".txt"))
                {
                    if (file.length() > 50)
                        listForDelete.add(file);
                    else
                        listForSave.add(file);
                }
            }
        }

    }

    public void deleteEmptyFolder(File folder)
    {
        File[] files = folder.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                File[] subFoldersFiles = file.listFiles();
                if (subFoldersFiles.length == 0)
                    file.delete();
                else
                    deleteEmptyFolder(file);
            }
        }
    }
}
