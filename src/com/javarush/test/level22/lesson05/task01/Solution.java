package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException
    {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("JavaRush - лучший сервис"));
        System.out.println(getPartOfString(""));

    }
    public static String getPartOfString(String string) throws TooShortStringException{
        if (string == null)
            throw new TooShortStringException();

        StringBuilder result = new StringBuilder();
        int countSpace = 0;

        for (int i = 0; i < string.length(); i++)
        {
            char ch = string.charAt(i);
            if (countSpace > 0 & countSpace < 5)
                if (countSpace < 4)
                    result.append(ch);
                else if (ch != '.' & ch != ',' & ch != '!' & ch != ':' & ch != ';' & ch != '?' & ch != ' ')
                    result.append(ch);
            if (ch == ' ')
                countSpace ++;
        }
        if (countSpace <= 4)
            throw new TooShortStringException();

        return result.toString();
    }

    public static class TooShortStringException extends Throwable{
    }
}
