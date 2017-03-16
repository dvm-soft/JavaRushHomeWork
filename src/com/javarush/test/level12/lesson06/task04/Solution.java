package com.javarush.test.level12.lesson06.task04;

/* Класс Cow от Animal
Унаследуй класс Cow от Animal.
Реализуй все недостающие методы в классе Cow.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Animal animal = new Cow();
        System.out.println(animal.getName());

    }

    public static abstract class Animal
    {
        public abstract String getName();
    }

    public static class Cow extends Animal
    {
        String name = "Корова";
        public String getName() {
            return name;
        }
    }

}
