package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        //BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "cp1251"));
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        while (file.ready())
        {
            String[] value = file.readLine().split(" ");
            if ( value.length >= 4)
            {
                DateFormat format = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                Date birthday = format.parse(value[value.length-3]+ " " + value[value.length-2] + " " + value[value.length-1]);
                String name = "";
                for (int i = 0; i < value.length - 3; i++)
                    name += (name.isEmpty() ? "" : " ") + value[i];
                PEOPLE.add(new Person(name, birthday));
            }
        }
        file.close();
    }

}
