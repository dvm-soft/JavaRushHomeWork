package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Дмитрий on 05.12.2016.
 */
public class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> map = new HashMap<String, CurrencyManipulator>();
    private CurrencyManipulatorFactory()
    {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        if (!map.containsKey(currencyCode))
            map.put(currencyCode, new CurrencyManipulator(currencyCode));
        return map.get(currencyCode);
    }
    public static Collection getAllCurrencyManipulators()
    {
        return map.values();
    }

}
