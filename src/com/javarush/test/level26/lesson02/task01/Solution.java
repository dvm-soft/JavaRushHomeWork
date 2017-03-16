package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
/*    public static void main(String[] args)
    {
        Solution sol = new Solution();
        Integer[] array = new Integer[] {1,2,3,4,-5,6,7,8,9};
        sort(array);
        for (Integer i : array)
            System.out.print(i + " ");
    }
*/
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        if (array == null)
            return null;
        if (array.length < 2)
            return array;

        final double mediana;
        Arrays.sort(array);

//        for (Integer i : array)
//            System.out.print(i + " ");

        if (array.length % 2 == 1)
            mediana = array[array.length/2];
        else
            mediana = (array[array.length / 2 - 1] + array[array.length / 2]) * 1.0 / 2;
//        System.out.println(mediana);

        Arrays.sort(array, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2)
                    {
                        double result = Math.abs(mediana - o1) - Math.abs(mediana - o2);
                        if (result > 0)
                            return 1;
                        else if (result < 0)
                            return -1;
                        else
                            return o1 - o2;
                    }
                }
                );

        return array;
    }
}
