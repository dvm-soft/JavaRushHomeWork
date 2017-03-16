package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            User user1 = new User();
            user1.setMale(true);
            user1.setLastName("Ivanov");
            user1.setFirstName("Ivan");
            user1.setBirthDate(df.parse("10.03.1972"));
            user1.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user1);

            User user2 = new User();
            user2.setMale(false);
            user2.setLastName("Ivanova");
            user2.setFirstName("Ivanna");
            user2.setBirthDate(df.parse("10.04.1982"));
            user2.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            StringBuilder str = new StringBuilder();

            for (User user : users)
            {
                str.append(user.getFirstName() + "\r\n");
                str.append(user.getLastName() + "\r\n");
                str.append((user.isMale() ? "true" : "false") + "\r\n");
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                str.append(df.format(user.getBirthDate()) + "\r\n");
                str.append(user.getCountry() + "\r\n");

            }
            outputStream.write(str.toString().getBytes());

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            byte[] buff = new byte[inputStream.available()];
            inputStream.read(buff);
            String[] str = new String(buff).split("\r\n");
            int userCount = str.length / 5;
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            for (int i = 0; i < userCount * 5 ; i += 5)
            {
                User user = new User();
                user.setFirstName(str[i]);
                user.setLastName(str[i+1]);
                user.setMale(("true".equals(str[i+2]) ? true : false));
                user.setBirthDate(df.parse(str[i+3]));
                user.setCountry(User.Country.valueOf(str[i+4]));
                this.users.add(user);

            }
        }
    }
}
