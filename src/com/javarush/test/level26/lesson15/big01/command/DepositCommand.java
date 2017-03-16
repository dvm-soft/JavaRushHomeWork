package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Arrays;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * Created by 1 on 05.12.2016.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit", Locale.getDefault());
    private ResourceBundle resCommon = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common", Locale.getDefault());

    @Override
    public void execute() throws InterruptOperationException
    {

        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();

        ConsoleHelper.writeMessage(String.format(resCommon.getString("choose.denomination.and.count.format"), currencyCode));
        String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);

        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).addAmount(Integer.parseInt(twoDigits[0]), Integer.parseInt(twoDigits[1]));

        System.out.println(String.format(res.getString("success.format"), Integer.parseInt(twoDigits[0]) * Integer.parseInt(twoDigits[1]), currencyCode));
    }
}
