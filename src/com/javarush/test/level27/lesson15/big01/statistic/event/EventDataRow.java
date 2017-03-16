package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by 1 on 17.12.2016.
 */
public interface EventDataRow
{
    public EventType getType();
    public Date getDate();
    public int getTime();

}
