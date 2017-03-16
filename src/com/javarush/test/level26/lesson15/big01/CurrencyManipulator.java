package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Дмитрий on 05.12.2016.
 */
public class CurrencyManipulator
{
    String currencyCode;
    Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }
    public int getTotalAmount()
    {
        int amount = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
        {
            amount += pair.getKey() * pair.getValue();
        }

        return amount;
    }

    public boolean hasMoney()
    {
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {

        return (getTotalAmount() >= expectedAmount);
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException, ConcurrentModificationException
    {
        TreeMap<Integer, Integer> answ = new TreeMap<>(Collections.reverseOrder());
        int ostatok = withdraw(denominations, answ, expectedAmount);
        if(ostatok == 0)
        {
            for (Map.Entry<Integer, Integer> e : answ.entrySet())
            {
                int count = denominations.get(e.getKey()) - e.getValue();
                if (count == 0)
                {
                    denominations.remove(e.getKey());
                } else
                {
                    denominations.put(e.getKey(), count);
                }
            }
        } else
                throw new NotEnoughMoneyException("Выдача невозможна.");
        return answ;
    }

    //Снимает sum максимально крупными купюрами, результат помещается в result
    //Если сумму удалось снять - возвращает ноль
    private int withdraw(Map<Integer, Integer> map, Map<Integer, Integer> result, int sum){
        Map<Integer, Integer> copymap = new TreeMap<>(Collections.reverseOrder());
        copymap.putAll(map);
        for(Map.Entry<Integer, Integer> e : copymap.entrySet()){
            if(e.getKey() <= sum && e.getValue() > 0){
                sum -= e.getKey();
                copymap.put(e.getKey(), e.getValue() - 1);

                if(result.containsKey(e.getKey())){
                    result.put(e.getKey(), result.get(e.getKey())  + 1);
                } else {
                    result.put(e.getKey(), 1);
                }

                if(sum <= 0) return sum;
                return withdraw(copymap, result, sum);
            }
        }
        return sum;
    }

}
