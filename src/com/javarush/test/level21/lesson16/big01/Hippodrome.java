package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by 1 on 19.11.2016.
 */
public class Hippodrome
{
    public static ArrayList<Horse> horses = new ArrayList<Horse>();
    public static Hippodrome game;

    public static void main(String[] args)
    {
        game = new Hippodrome();
        Horse horse1 = new Horse("Зорька", 3, 0);
        Horse horse2 = new Horse("Ласточка", 3, 0);
        Horse horse3 = new Horse("Огонек", 3, 0);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        System.out.println(game.getHorses());

        game.run();
        game.printWinner();


    }

    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
    public Horse getWinner()
    {
        Horse winner = horses.get(0);
        for (Horse horse : horses)
            if(horse.distance > winner.distance)
                winner = horse;
        return winner;
    }

    public void move()
    {
        for (Horse horse : horses)
            horse.move();
    }

    public void run()
    {
        for (int i = 1; i <= 100 ; i++)
        {
            move();
            print();
            try
            {
                Thread.sleep(200);
            } catch (Exception e) {}
        }
    }

    public void print()
    {
        for (Horse horse : horses)
            horse.print();
        System.out.println("");
        System.out.println("");

    }

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public Hippodrome()
    {
    }
}

