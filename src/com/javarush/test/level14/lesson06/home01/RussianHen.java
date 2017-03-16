package com.javarush.test.level14.lesson06.home01;

/**
 * Created by 1 on 19.10.2016.
 */
public class RussianHen extends Hen
{
    public int getCountOfEggsPerMonth()
    {
        return 31;
    }

    public String getDescription()
    {
        return super.getDescription() +
                " Моя страна - " +
                RUSSIA +
                ". Я несу " +
                this.getCountOfEggsPerMonth() +
                " яиц в месяц.";
    }

}
