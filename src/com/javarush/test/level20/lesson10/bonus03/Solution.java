package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        int wordCount = words.length;
        List<Word> list = new ArrayList<Word>();

        for (int w = 0; w < wordCount; w++)
        {
            String word = words[w];
            int wordLenght = word.length();
            char firstLitera = (char) word.charAt(0);
            for (int i = 0; i < crossword.length ; i++)
                for (int j = 0; j < crossword[0].length; j++)
                    if ((char)crossword[i][j] == firstLitera)
                        checkAllDirection(list, crossword, i, j, word);
        }

        for (Word w : list)
            System.out.println(w);
        return list;
    }

    public static void checkAllDirection(List<Word> list, int[][] crossword, int rowStart, int colStart, String word)
    {
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
            {
                int rowEnd = rowStart + i * (word.length()-1);
                int colEnd = colStart + j * (word.length()-1);
                if ((rowEnd > -1) && (rowEnd < crossword.length ))
                    if ((colEnd > -1) && (colEnd < crossword[0].length ))
                        if (checkOneDirection(crossword, rowStart, colStart, word, i, j))
                        {
                            Word w = new Word(word);
                            w.setStartPoint(colStart, rowStart);
                            w.setEndPoint(colEnd, rowEnd);
                            list.add(w);
                        }

            }


    }

    public static boolean checkOneDirection(int[][] crossword, int row, int col, String word, int rowDir, int colDir)
    {
        String tmp = "";
        for (int i = 0; i < word.length(); i++)
            tmp += (char) crossword[row + rowDir * i][col + colDir * i];
        if (tmp.equals(word))
            return true;
        else
            return false;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
