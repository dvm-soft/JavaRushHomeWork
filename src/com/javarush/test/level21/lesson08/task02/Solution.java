package com.javarush.test.level21.lesson08.task02;

import java.util.Arrays;

/* Клонирование
Класс Plant не должен реализовывать интерфейс Cloneable
Реализуйте механизм глубокого клонирования для Tree.
Метод main изменять нельзя.
*/
public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        try {
            clone = tree.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.branches);
        System.out.println(clone.branches);
    }

    public static class Plant{
        private String name;

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString()
        {
            return "Plant{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class Tree extends Plant {
        private String[] branches;

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }

        public String[] getBranches() {
            return branches;
        }

        @Override
        public String toString()
        {
            return "Tree{" +
                    "name='" + getName() + '\'' +
                    "branches=" + Arrays.toString(branches) +
                    '}';
        }

        @Override
        protected Tree clone() throws CloneNotSupportedException
        {
            String[] branches = getBranches() == null ? null : getBranches().clone();
            String name = this.getName();
            Tree tree = new Tree(name, branches);
            return tree;
        }
    }
}
