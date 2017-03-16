package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    public Solution(int i) {}
    public Solution(String i) {}
    public Solution(Double i) {}
    protected Solution(int i, int j) {}
    protected Solution(String i, int j) {}
    protected Solution(Double i, int j) {}
    private Solution(int i, int j, int f) {}
    private Solution(String i, int j, int f) {}
    private Solution(Double i, int j, int f) {}
    Solution(int i, int j, int f, int k) {}
    Solution(String i, int j, int f, int k) {}
    Solution(Double i, int j, int f, int k) {}
}

