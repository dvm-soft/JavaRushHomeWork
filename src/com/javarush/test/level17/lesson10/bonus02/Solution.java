package com.javarush.test.level17.lesson10.bonus02;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
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
        if ("-i".equals(args[0]) && args.length > 1)
            for (int i = 1; i < args.length; i++)
                getInfo(args[i]);

        if ("-d".equals(args[0]) && args.length > 1)
            for (int i = 1; i < args.length; i++)
                deletePerson(args[i]);

        if ("-u".equals(args[0]) && args.length > 4 && ((args.length - 1)%4 == 0))
            for (int i = 1; i < args.length; i+=4)
                updatePerson(args[i], args[i+1], args[i+2], args[i+3]);

        if ("-c".equals(args[0]) && args.length > 3 && ((args.length - 1)%3 == 0))
            for (int i = 1; i < args.length; i+=3)
                createPerson(args[i], args[i+1], args[i+2]);

        //System.out.println(allPeople);
    }
    public synchronized static void getInfo(String id)
    {
        System.out.println(allPeople.get(Integer.parseInt(id)));
    }

    public synchronized static void deletePerson(String id)
    {
        Person person = allPeople.get(Integer.parseInt(id));
        person.setName(null);
        person.setSex(null);
        person.setBirthDay(null);
    }

    public synchronized static void updatePerson(String id, String inputName, String inputSex, String inputBD)
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

    public synchronized static void createPerson(String inputName, String inputSex, String inputBD)
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
