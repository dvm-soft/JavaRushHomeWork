package com.javarush.test.level20.lesson10.home01;

import java.io.Serializable;

/* Минимум изменений
Используя минимум изменений кода сделайте так, чтобы сериализация класса C стала возможной.
*/
public class Solution {

    public class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
        public A()
        {
            super();
        }
    }

    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }

        public B()
        {
            super();
        }
    }

    public class C extends B{
        String name = "C";
        //private static final long serialVersionUID = 1L;
        public C(String name) {
            super(name);
            this.name = name;
        }
    }
}