package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
*/

import java.util.ArrayList;
import java.util.List;

class Point
{
    public int x;
    public int y;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

public class Solution
{
    public static void main(String[] args)
    {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon)
    {
        //напишите тут ваш код
        int i, j, npol;
        boolean inPoly;

        npol = polygon.size() - 1;
        j = npol;
        inPoly = false;
        for (i = 0; i <= npol; i++)
        {
            if ((((polygon.get(i).y < point.y) && (point.y < polygon.get(j).y)) || ((polygon.get(j).y <= point.y) && (point.y < polygon.get(i).y))) &&
                    (point.x > (polygon.get(j).x - polygon.get(i).x) * (point.y - polygon.get(i).y) / (polygon.get(j).y - polygon.get(i).y) + polygon.get(i).x))
                inPoly = !inPoly;
            j = i;
        }
        return inPoly;
    }

}

