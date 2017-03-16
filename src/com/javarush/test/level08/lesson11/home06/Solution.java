package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human child1 = new Human("Child_1", true, 1, new ArrayList<Human>());
        Human child2 = new Human("Child_2", false, 2, new ArrayList<Human>());
        Human child3 = new Human("Child_3", true, 5, new ArrayList<Human>());

        ArrayList<Human> ch = new ArrayList<Human>();
        ch.add(child1);
        ch.add(child2);
        ch.add(child3);


        Human mother = new Human("Mother", false, 35, ch);
        Human father = new Human("Father", true, 45, ch);

        ArrayList<Human> chm = new ArrayList<Human>();
        chm.add(mother);
        Human grandmother1 = new Human("GrMother1", false, 65, chm);
        Human grandfather1 = new Human("GrFather1", true, 75, chm);

        ArrayList<Human> chf = new ArrayList<Human>();
        chf.add(father);
        Human grandmother2 = new Human("GrMother2", false, 65, chf);
        Human grandfather2 = new Human("GrFather2", true, 75, chf);

        System.out.println(grandfather1);
        System.out.println(grandmother1);
        System.out.println(grandfather2);
        System.out.println(grandmother2);

        System.out.println(father);
        System.out.println(mother);

        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);

    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        Boolean sex;
        int age;
        ArrayList<Human> children;

        Human () {}

        Human (String name, Boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
