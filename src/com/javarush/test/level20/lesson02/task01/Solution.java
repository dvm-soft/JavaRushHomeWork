package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File file = File.createTempFile("human", null);
            OutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = new FileInputStream(file);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if (somePerson.equals(ivanov))
                System.out.println("Все верно");
            else
            {
                System.out.println(ivanov);
                System.out.println(somePerson);
            }
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
        public String toString(){
            StringBuilder str = new StringBuilder();
            str.append(this.name+" - ");
            str.append(this.assets.size());
            str.append("\r\n");
            for (Asset asset : this.assets)
            {
                str.append(asset.getName());
                str.append(" - ");
                str.append(asset.getPrice());
                str.append(". ");
            }
            return str.toString();
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод

            StringBuilder str = new StringBuilder();
            str.append(this.name+"\r\n");
            str.append(this.assets.size());
            str.append("\r\n");
            for (Asset asset : this.assets)
            {
                str.append(asset.getName());
                str.append("\r\n");
                str.append(asset.getPrice());
                str.append("\r\n");
            }
            outputStream.write(str.toString().getBytes());
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            byte[] buff = new byte[inputStream.available()];
            inputStream.read(buff);
            String[] str = new String(buff).split("\r\n");
            this.name = str[0];
            int assetsCount = Integer.parseInt(str[1]);
            for (int i = 2; i < 2 + assetsCount * 2 ; i += 2)
            {
                Asset asset = new Asset(str[i]);
                asset.setPrice(Double.parseDouble(str[i+1]));
                assets.add(asset);
            }
        }
    }
}
