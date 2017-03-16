package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        List<String> list = new ArrayList<>();
        Queue<String> queue = new PriorityQueue<>();

        queue.add(root);
        while (!queue.isEmpty())
        {
            String folder = queue.remove();
            File[] files = (new File(folder)).listFiles();
            for (File file : files)
            {
                if (file.isFile())
                    list.add(file.getCanonicalPath());
                else
                    queue.add(file.getCanonicalPath());
            }
        }
        return list;
    }
}
