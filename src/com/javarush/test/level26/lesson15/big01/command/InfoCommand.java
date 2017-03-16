package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.*;


/**
 * Created by 1 on 05.12.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info", Locale.getDefault());
    @Override
    public void execute()
    {

        ConsoleHelper.writeMessage(res.getString("before"));
        int amount = 0;
        Collection<CurrencyManipulator> collection = CurrencyManipulatorFactory.getAllCurrencyManipulators();

        for (CurrencyManipulator cm : collection)
        {
            if (cm.hasMoney())
            {
                System.out.println(String.format("%s - %s", cm.getCurrencyCode(), cm.getTotalAmount()));
                amount += cm.getTotalAmount();
            }
//            else
//                System.out.println(String.format("%s - %s", cm.getCurrencyCode(), "No money available."));
        }
        if (amount == 0)
            ConsoleHelper.writeMessage(res.getString("no.money"));

    }
}
