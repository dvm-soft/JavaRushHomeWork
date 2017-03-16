package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;


/**
 * Created by 1 on 05.12.2016.
 */
class WithdrawCommand implements Command
{
    private static ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw", Locale.getDefault());
    @Override
    public void execute() throws InterruptOperationException
    {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int amount = 0;
        Map<Integer, Integer> resultWithdrawMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        while (true)
        {
            try
            {
                amount = Integer.parseInt(((String) ConsoleHelper.readString()).trim());
                if (amount < 0)
                {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
                else if (!currencyManipulator.isAmountAvailable(amount))
                {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
                else
                    break;
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
        if(amount > 0)
        {
            try
            {
                resultWithdrawMap = currencyManipulator.withdrawAmount(amount);
                if (resultWithdrawMap.size() > 0)
                {
                    for (Map.Entry<Integer, Integer> pair : resultWithdrawMap.entrySet())
                        ConsoleHelper.writeMessage(String.format("\t%s - %s", pair.getKey(), pair.getValue()));
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
                }
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
            catch (ConcurrentModificationException ignore)
            {
            }
        }


    }
}

