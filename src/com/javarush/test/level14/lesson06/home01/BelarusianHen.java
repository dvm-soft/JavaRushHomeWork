package com.javarush.test.level14.lesson06.home01;

/**
 * Created by 1 on 19.10.2016.
 */
public class BelarusianHen extends Hen
{
    public int getCountOfEggsPerMonth()
    {
        return 34;
    }
    public String getDescription()
    {
        return super.getDescription() +
                " Моя страна - " +
                BELARUS +
                ". Я несу " +
                this.getCountOfEggsPerMonth() +
                " яиц в месяц.";
    }

}
