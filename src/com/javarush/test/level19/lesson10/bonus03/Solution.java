package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static String
            html = "",
            tagOpen = "",
            tagClose = "";

    public static void main(String[] args) throws Exception {
        String  tag = args[0];
        tagOpen = "<"+tag;
        tagClose = "</" + tag + ">";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while (file.ready())
            html += file.readLine();
        file.close();
        html.replaceAll("[\\x0D|\\x0A]", " ");


        List<String> list = new ArrayList<String>();
        int
                tagStart = -1,
                tagEnd = -1;

//        System.out.println(html+"\n");

        tagStart = getTagStart(0);
        while (tagStart >= 0)
        {
            tagEnd = getTagEnd(tagStart + 1);
            list.add(html.substring(tagStart, tagEnd));
            tagStart = getTagStart(tagStart + 1);
        }

        for (String tags : list)
            System.out.println(tags);
    }
    public static int getTagEnd (int pos)
    {
        int innerTagCount = 0;

        while (pos < (html.length() - tagClose.length()))
        {
            if (tagClose.equals(html.substring(pos, pos + tagClose.length())))
                if (innerTagCount == 0)
                    return pos + tagClose.length();
                else
                    innerTagCount--;
            if (tagOpen.equals(html.substring(pos, pos + tagOpen.length())))
                innerTagCount++;
            pos++;
        }
        return pos + tagClose.length();
    }
    public static int getTagStart (int pos)
    {
        pos = html.indexOf(tagOpen, pos);
        if ((html.charAt(pos+tagOpen.length()) != ' ') & (html.charAt(pos+tagOpen.length()) != '>') & (pos >= 0))
            pos = -1;
        return pos;
    }

}
