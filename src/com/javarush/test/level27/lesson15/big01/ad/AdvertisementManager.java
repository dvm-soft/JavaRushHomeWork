package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 1 on 15.12.2016.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<List<Advertisement>> globalList = new ArrayList<List<Advertisement>>();

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException
    {
        List<Advertisement> playList = optimizeList(storage.list());

        if (playList.isEmpty())
            throw new NoVideoAvailableException();

        Collections.sort(playList, new Comparator<Advertisement>() {
                    @Override
                    public int compare(Advertisement o1, Advertisement o2)
                    {
                        int result = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                        return result != 0 ? result : Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration(), o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                    }
                }
        );

        long amount = 0;
        int totalDuration = 0;
        for (Advertisement ad : playList)
        {
            amount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(playList, amount, totalDuration));

        for (Advertisement ad : playList)
        {
            ConsoleHelper.writeMessage(ad.getName() + " is displaying... " + ad.getAmountPerOneDisplaying() + ", " + ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration());
            try
            {
                ad.revalidate();
            }
            catch (UnsupportedOperationException e)
            {
                throw new NoVideoAvailableException();
            }
        }
    }

    private List<Advertisement> optimizeList (List<Advertisement> list)
    {
        List<Advertisement> playList = new ArrayList<Advertisement>();
        for (Advertisement ad : list)
            if (ad.getHits() > 0)
                playList.add(ad);

        for (int n = 1; n <= playList.size(); n++)
        {
            recursion(playList, new ArrayList<Advertisement>(), n, 1);
        }

        Collections.sort(globalList, new Comparator<List<Advertisement>>() {
                    @Override
                    public int compare(List<Advertisement> o1, List<Advertisement> o2)
                    {
                        long    totalAmount1 = 0,
                                totalAmount2 = 0;
                        int     totalCount1 = o1.size(),
                                totalCount2 = o2.size(),
                                totalDuration1 = 0,
                                totalDuration2 = 0;
                        for (Advertisement ad : o1)
                        {
                            totalAmount1 += ad.getAmountPerOneDisplaying();
                            totalDuration1 += ad.getDuration();
                        }
                        for (Advertisement ad : o2)
                        {
                            totalAmount2 += ad.getAmountPerOneDisplaying();
                            totalDuration2 += ad.getDuration();
                        }

                        int result = Long.compare(totalAmount2, totalAmount1);
                        if (result != 0)
                            return result;
                        else
                        {
                            result = Long.compare(totalDuration2, totalDuration1);
                            return result != 0 ? result : Long.compare(totalCount1, totalCount2);
                        }
                    }
                }
        );

        if (!globalList.isEmpty())
            playList = globalList.get(0);
        else
            playList.clear();

        return playList;
    }


    private void recursion(List<Advertisement> list, List<Advertisement> preList, int count, int deep)
    {
            for (Integer i = 0; i < list.size(); i++)
            {
                ArrayList<Advertisement> resultList = new ArrayList<Advertisement>(preList);
                resultList.add(list.get(i));
                if (deep == count)
                {
                    int totalTime = 0;
                    for (Advertisement ad : resultList)
                        totalTime += ad.getDuration();
                    if (totalTime <= timeSeconds)
                        globalList.add(resultList);
                }
                else
                {
                    List<Advertisement> cutList = new ArrayList<Advertisement>();
                    for (int j = 0; j < list.size(); j++)
                    {
                        if (j > i)
                            cutList.add(list.get(j));
                    }
                    recursion(cutList, resultList, count, deep + 1);
                }
            }
    }
}
