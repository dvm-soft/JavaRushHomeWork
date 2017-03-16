package com.javarush.test.level17.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут

        //System.out.println(allPeople);
        if ("-i".equals(args[0]))
            getInfo(args[1]);

        if ("-d".equals(args[0]))
            deletePerson(args[1]);

        if ("-u".equals(args[0]))
            updatePerson(args[1], args[2], args[3], args[4]);

        if ("-c".equals(args[0]))
            createPerson(args[1], args[2], args[3]);

        //System.out.println(allPeople);
    }

    public static void getInfo(String id)
    {
        System.out.println(allPeople.get(Integer.parseInt(id)));
    }

    public static void deletePerson(String id)
    {
        Person person = allPeople.get(Integer.parseInt(id));
        person.setName(null);
        person.setSex(null);
        person.setBirthDay(null);
    }

    public static void updatePerson(String id, String inputName, String inputSex, String inputBD)
    {
        Person person = allPeople.get(Integer.parseInt(id));
        Date bd = null;
        Sex sex;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd/MM/yyyy");
        try {
            bd = format.parse(inputBD);
        } catch (Exception e) {}

        if ("м".equals(inputSex))
            sex = Sex.MALE;
        else
            sex = Sex.FEMALE;

        person.setName(inputName);
        person.setSex(sex);
        person.setBirthDay(bd);

    }

    public static void createPerson(String inputName, String inputSex, String inputBD)
    {
        Person person = null;
        Date bd = null;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd/MM/yyyy");
        try {
        bd = format.parse(inputBD);
        } catch (Exception e) {}

        if ("м".equals(inputSex))
           person = Person.createMale(inputName, bd);
        else
           person = Person.createFemale(inputName, bd);
        allPeople.add(person);
        System.out.println(allPeople.size()-1);
    }
}
