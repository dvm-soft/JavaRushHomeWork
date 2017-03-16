package com.javarush.test.level05.lesson09.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя конструкторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    //напишите тут ваш код
    public String name, color;
    public int growth;

    public Dog(String name) {
        this.name = name;
        this.growth = 0;
        this.color = null;
    }

    public Dog(String name, int growth) {
        this.name = name;
        this.growth = growth;
        this.color = null;
    }

    public Dog(String name, int growth, String color) {
        this.name = name;
        this.growth = growth;
        this.color = color;
    }

}
