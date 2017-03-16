package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable{

    public static void main(String[] args) throws Exception
    {
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("c:\\!\\!.txt"));
        Solution s = new Solution();
        B b = s.new B("B");
        ous.writeObject(b);
        ous.close();

        ObjectInputStream ins = new ObjectInputStream(new FileInputStream("c:\\!\\!.txt"));
        B newB = (B) ins.readObject();
        ins.close();

        System.out.println(b.name);
        System.out.println(newB.name);

    }


    public static class A {
        protected String name = "A";

        public A() {}
        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public B(String name) {
            super(name);
            this.name += name;
        }
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeUTF(name);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            name = in.readUTF();
        }
    }


}
