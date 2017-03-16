package com.javarush.test.level14.lesson06.home01;

/**
 * Created by 1 on 19.10.2016.
 */
public class UkrainianHen extends Hen
{
    public int getCountOfEggsPerMonth()
    {
        return 32;
    }
    public String getDescription()
    {
        return super.getDescription() +
                " Моя страна - " +
                UKRAINE +
                ". Я несу " +
                this.getCountOfEggsPerMonth() +
                " яиц в месяц.";
    }

}
