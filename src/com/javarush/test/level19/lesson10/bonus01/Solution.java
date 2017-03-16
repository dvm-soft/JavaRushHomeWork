package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws Exception{
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        String
                fileName1 = reader.readLine(),
                fileName2 = reader.readLine();
        reader.close();

        BufferedReader file1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader file2 = new BufferedReader(new FileReader(fileName2));
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        while (file1.ready())
            list1.add(file1.readLine());
        while (file2.ready())
            list2.add(file2.readLine());
        int
                i = 0,
                j = 0;
        while (i < list1.size() | (j < list2.size()))
        {
            if (j >= list2.size())
            {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                i ++;
            }
            else
            {
                if (i >= list1.size())
                {
                    lines.add(new LineItem(Type.ADDED, list2.get(j)));
                    j ++;
                }
                else
                {
                    if (list1.get(i).equals(list2.get(j)))
                    {
                        lines.add(new LineItem(Type.SAME, list1.get(i)));
                        i ++;
                        j ++;
                    } else
                    {
                        if ( (j+1) < list2.size() && list1.get(i).equals(list2.get(j+1)))
                        {
                            lines.add(new LineItem(Type.ADDED, list2.get(j)));
                            j ++;
                        }
                        else if ( (i+1) < list1.size() && list1.get(i+1).equals(list2.get(j)))
                        {
                            lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                            i ++;
                        } else {
                            lines.add(new LineItem(Type.ADDED, list2.get(j)));
                            lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                            i ++;
                            j ++;

                        }
                    }
                }
            }
        }


        file1.close();
        file2.close();

 /*       for (LineItem item : lines)
            System.out.println(item);
*/

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
        public String toString(){
            return ""+type + " " + line;
        }
    }
}
