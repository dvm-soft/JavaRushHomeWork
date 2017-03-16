package com.javarush.test.level03.lesson04.task03;

/* StarCraft
Создать 10 зергов, 5 протосов и 12 терран.
Дать им всем уникальные имена.
*/

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Zerg Zerg1= new Zerg();
        Zerg Zerg2= new Zerg();
        Zerg Zerg3= new Zerg();
        Zerg Zerg4= new Zerg();
        Zerg Zerg5= new Zerg();
        Zerg Zerg6= new Zerg();
        Zerg Zerg7= new Zerg();
        Zerg Zerg8= new Zerg();
        Zerg Zerg9= new Zerg();
        Zerg Zerg10= new Zerg();
                
                
        Zerg1.name = "Zerg1";
        Zerg2.name = "Zerg2";
        Zerg3.name = "Zerg3";
        Zerg4.name = "Zerg4";
        Zerg5.name = "Zerg5";
        Zerg6.name = "Zerg6";
        Zerg7.name = "Zerg7";
        Zerg8.name = "Zerg8";
        Zerg9.name = "Zerg9";
        Zerg10.name = "Zerg10";

        Protos Protos1= new Protos();
        Protos Protos2= new Protos();
        Protos Protos3= new Protos();
        Protos Protos4= new Protos();
        Protos Protos5= new Protos();        
        
        Protos1.name = "Protos1";
        Protos2.name = "Protos2";
        Protos3.name = "Protos3";
        Protos4.name = "Protos4";
        Protos5.name = "Protos5";

        Terran Terran1= new Terran();
        Terran Terran2= new Terran();
        Terran Terran3= new Terran();
        Terran Terran4= new Terran();
        Terran Terran5= new Terran();
        Terran Terran6= new Terran();
        Terran Terran7= new Terran();
        Terran Terran8= new Terran();
        Terran Terran9= new Terran();
        Terran Terran10= new Terran();
        Terran Terran11= new Terran();
        Terran Terran12= new Terran();
        
        Terran1.name = "Terran1";
        Terran2.name = "Terran2";
        Terran3.name = "Terran3";
        Terran4.name = "Terran4";
        Terran5.name = "Terran5";
        Terran6.name = "Terran6";
        Terran7.name = "Terran7";
        Terran8.name = "Terran8";
        Terran9.name = "Terran9";
        Terran10.name = "Terran10";
        Terran11.name = "Terran11";
        Terran12.name = "Terran12";
    }

    public static class Zerg
    {
        public String name;
    }

    public static class Protos
    {
        public String name;
    }

    public static class Terran
    {
        public String name;
    }
}