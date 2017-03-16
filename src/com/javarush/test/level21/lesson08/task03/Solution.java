package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Разрешите клонировать класс А
Запретите клонировать класс B
Разрешите клонировать класс C
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws CloneNotSupportedException
    {
        A a = new A(1,2);
        A a2 = a.clone();
        System.out.println(a);
        System.out.println(a2);
        B b = new B(1,2,"B");
        System.out.println(b);
        try
        {
            B b2 = b.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException");
        }
        C c = new C(1,2,"C");
        C c2 = c.clone();
        System.out.println(c);
        System.out.println(c2);
    }

    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        protected A clone() throws CloneNotSupportedException
        {
            return (A) super.clone();
        }

        @Override
        public String toString()
        {
            return "A{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        @Override
        protected B clone() throws CloneNotSupportedException
        {
            throw new CloneNotSupportedException();
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName()+"{" +
                    "i=" + getI() +
                    ", j=" + getJ() +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class C extends B implements Cloneable{
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected C clone() throws CloneNotSupportedException
        {
            return new C(this.getI(), this.getJ(), this.getName());
        }
    }
}
