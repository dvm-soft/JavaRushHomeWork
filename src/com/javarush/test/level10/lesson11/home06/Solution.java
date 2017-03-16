package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        String name, lastname;
        Boolean sex;
        int age, weight, hight;

        Human()
        {
            this.name = "Аноним";
        }
        Human(String name)
        {
            this.name = name;
        }

        Human(String name, String lastname)
        {
            this.name = name;
            this.lastname = lastname;
        }
        Human(String name, String lastname, Boolean sex)
        {
            this.name = name;
            this.lastname = lastname;
            this.sex = sex;
        }
        Human(String name, String lastname, Boolean sex, int weight)
        {
            this.name = name;
            this.lastname = lastname;
            this.sex = sex;
            this.weight = weight;
        }
        Human(String name, String lastname, Boolean sex, int weight, int hight)
        {
            this.name = name;
            this.lastname = lastname;
            this.sex = sex;
            this.weight = weight;
            this.hight = hight;
        }
        Human(String name, String lastname, Boolean sex, int weight, int hight, int age)
        {
            this.name = name;
            this.lastname = lastname;
            this.sex = sex;
            this.weight = weight;
            this.hight = hight;
            this.age = age;
        }
        Human(Boolean sex, int weight, int hight, int age)
        {
            this.name = "Anonim";
            this.lastname = "";
            this.sex = sex;
            this.weight = weight;
            this.hight = hight;
            this.age = age;
        }
        Human(Boolean sex, int weight, int hight)
        {
            this.name = "Anonim";
            this.lastname = "";
            this.sex = sex;
            this.weight = weight;
            this.hight = hight;
            this.age = 0;
        }
        Human(Boolean sex, int weight)
        {
            this.name = "Anonim";
            this.lastname = "";
            this.sex = sex;
            this.weight = weight;
            this.hight = 180;
            this.age = 0;
        }

    }
}
