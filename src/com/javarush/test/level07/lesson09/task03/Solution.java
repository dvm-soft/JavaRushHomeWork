package com.javarush.test.level07.lesson09.task03;

import java.util.ArrayList;

/* Слово «именно»
1. Создай список из слов «мама», «мыла», «раму».
2. После каждого слова вставь в список строку, содержащую слово «именно».
3. Используя цикл for вывести результат на экран, каждый элемент списка с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        list.add("мама");
        list.add("мыла");
        list.add("раму");
        int i = 0;
        while (i < list.size()-1) {
            list.add(i+1, "именно");
            i++;
            i++;
        }
        list.add("именно");
        for (int j = 0; j < list.size(); j++)
        {
            System.out.println(list.get(j));
        }

    }
}
