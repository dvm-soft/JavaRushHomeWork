package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("");
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
//            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"));
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
            String line = file.readLine();
            while(line != null)
            {
                sb.append((sb.length() == 0 ? "" : " ") + line.trim());
                line = file.readLine();
            }
            file.close();
            reader.close();
        } catch (Exception e) {}
//        System.out.println(sb.toString());
        StringBuilder result = getLine(sb.toString().trim().split("\\s+"));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {

        StringBuilder result = new StringBuilder("");
        if (words == null || words.length == 0)
            return result;
        if (words.length == 1)
            return result.append(words[0]);
        ArrayList<String> list = new ArrayList<String>();
            int maxChain = 0;
            String maxString = "";
            for (int i = 0; i < words.length * 1000; i++)
            {
                list.clear();
                result = new StringBuilder("");
                for (String s : words)
                {
                    s = s.trim();
                    if (!s.isEmpty() && !list.contains(s))
                        list.add(s);
                }
                Collections.shuffle(list);
                int chainLengh = 0;
                int pos = 0;
                result.append(list.get(pos));
                char lastLitera = list.get(pos).toUpperCase().charAt(list.get(pos).length() - 1);
                list.set(pos, null);
                boolean found = true;
                while (found)
                {
                    found = false;
                    for (pos = 0; pos < list.size(); pos++)
                    {
                        if (list.get(pos) != null && list.get(pos).toUpperCase().charAt(0) == lastLitera)
                        {
                            lastLitera = list.get(pos).toUpperCase().charAt(list.get(pos).length() - 1);
                            found = true;
                            result.append(" " + list.get(pos));
                            list.set(pos, null);
                            chainLengh ++;
                            break;
                        }
                    }
                }
                if (maxChain < chainLengh)
                {
                    maxChain = chainLengh;
                    maxString = result.toString();
                }
            }
            result = new StringBuilder(maxString);
        return result;
    }
}
