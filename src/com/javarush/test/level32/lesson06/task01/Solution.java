package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = "";

        for (int i = 0; i < 8; i++)
        {
            char litera;
            switch (i)
            {
                case 0: litera = alphabet.charAt((int)(Math.random() * 26)); break;
                case 1: litera = alphabet.charAt((int)(Math.random() * 26) + 26); break;
                case 2: litera = alphabet.charAt((int)(Math.random() * 10) + 52); break;
                default: litera = alphabet.charAt((int)(Math.random() * 62));
            }
            password += litera;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            baos.write(password.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return baos;
    }
}
