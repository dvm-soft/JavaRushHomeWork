package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        //создай по два объекта каждого класса тут
        Man man1 = new Man("Dima", 44, "Home");
        Man man2 = new Man("Sergey", 42, "Home2");

        Woman woman1 = new Woman("Marina", 44, "Work");
        Woman woman2 = new Woman("Olga", 19, "Work2");

        //выведи их на экран тут
        man1.toPrint();
        man2.toPrint();
        woman1.toPrint();
        woman2.toPrint();

    }

    //добавьте тут ваши классы
    public static class Man {

        String name,address;
        int age;

        public Man (String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public void toPrint () {
            System.out.println(name + " " + age + " " + address);
        }
    }

    public static class Woman
    {

        String name, address;
        int age;

        public Woman(String name, int age, String address)
        {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public void toPrint () {
            System.out.println(name + " " + age + " " + address);
        }
    }
}
