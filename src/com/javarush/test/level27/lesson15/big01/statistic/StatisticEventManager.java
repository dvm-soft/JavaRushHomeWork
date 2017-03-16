package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by 1 on 17.12.2016.
 */
public class StatisticEventManager
{
    private static StatisticEventManager ourInstance = new StatisticEventManager();

    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticEventManager getInstance()
    {
        if (ourInstance == null)
            ourInstance = new StatisticEventManager();
        return ourInstance;
    }

    private StatisticEventManager()
    {
    }

    public void register(EventDataRow data)
    {
        if (data == null)
            return;
        statisticStorage.put(data);
    }

    public Map<Date, Long> reportAdvertisementProfit()
    {
        Map<Date, Long> map = new TreeMap<Date, Long>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.get(EventType.SELECTED_VIDEOS);

        for (EventDataRow event : list)
        {
            Date date  = event.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date = cal.getTime();

            if (map.containsKey(date))
                map.put(date, map.get(date) + ((VideoSelectedEventDataRow)event).getAmount());
            else
                map.put(date, ((VideoSelectedEventDataRow)event).getAmount());
        }
        return map;
    }

    public Map<Date, Map<String, Integer>> reportCookWorkloading()
    {
        Map<Date, Map<String, Integer>> map = new TreeMap<Date, Map<String, Integer>>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.get(EventType.COOKED_ORDER);
        for (EventDataRow event : list)
        {
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) event;
            Date date = event.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date = cal.getTime();

            String cookName = cookedOrderEventDataRow.getCookName();
            Integer cookTime = cookedOrderEventDataRow.getTime();

            if (map.isEmpty())
            {
                Map<String, Integer> innerMap = new TreeMap<String, Integer>();
                innerMap.put(cookName, cookTime);
                map.put(date, innerMap);
            }
            else
            {
               if (map.containsKey(date))
               {
                   Map<String, Integer> innerMap = map.get(date);
                   if (innerMap.containsKey(cookName))
                       innerMap.put(cookName, innerMap.get(cookName) + cookTime);
                   else
                       innerMap.put(cookName, cookTime);
               }
                else
               {
                   Map<String, Integer> innerMap = new TreeMap<String, Integer>();
                   innerMap.put(cookName, cookTime);
                   map.put(date, innerMap);
               }
            }
        }
        return map;
    }

    private class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> map = new HashMap<EventType, List<EventDataRow>>();

        public StatisticStorage()
        {
            for (EventType event : EventType.values())
                map.put(event, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data)
        {
            map.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType eventType)
        {
            return map.get(eventType);
        }
    }
}
