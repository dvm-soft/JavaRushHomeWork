package com.javarush.test.level05.lesson07.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя инициализаторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    //напишите тут ваш код
    public String name, color;
    public int growth;

    public void initialize(String name) {
        this.name = name;
        this.growth = 0;
        this.color = null;
    }

    public void initialize(String name, int growth) {
        this.name = name;
        this.growth = growth;
        this.color = null;
    }

    public void initialize(String name, int growth, String color) {
        this.name = name;
        this.growth = growth;
        this.color = color;
    }
}
