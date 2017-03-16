package com.javarush.test.level26.lesson15.big01;


import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * Created by Дмитрий on 05.12.2016.
 */
public class ConsoleHelper
{

    private static ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common", Locale.getDefault());

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException
    {
        String string = "";
        try
        {
            string = reader.readLine();
        }
        catch (Exception e) {}
        if ("EXIT".equals(string.toUpperCase()))
            throw new InterruptOperationException("Операция прервана.");

        return string;
    }

    public static String askCurrencyCode() throws InterruptOperationException
     {
        String currency = null;
            while (true)
            {
                writeMessage(res.getString("choose.currency.code"));
                currency = readString();
                if (currency.length() == 3)
                {
                    currency = currency.toUpperCase();
                    break;
                }
                writeMessage(res.getString("invalid.data"));
            }

        return currency;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        String[] twoDigits;
            String line = "";
            while (true)
            {
                line = readString();
                twoDigits = line.split("\\s");
                if (twoDigits.length == 2)
                {
                    try
                    {
                        long i = Long.parseLong(twoDigits[0]) + Long.parseLong(twoDigits[1]);
                        break;
                    } catch (Exception e) {}
                }
                writeMessage(res.getString("invalid.data"));
            }

        return twoDigits;
    }



    public static Operation askOperation() throws InterruptOperationException
    {
        Operation operation;
        while (true)
        {
            writeMessage(res.getString("choose.operation"));
            writeMessage(String.format("1 - %s, 2 - %s, 3 - %s, 4 - %s: ", res.getString("operation.INFO"),
                    res.getString("operation.DEPOSIT"), res.getString("operation.WITHDRAW"), res.getString("operation.EXIT")));
            try
            {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
                break;
            } catch (IllegalArgumentException e1)
            {}
            writeMessage(res.getString("invalid.data"));
        }
//        System.out.println(operation);
        return operation;

    }

    public static void printExitMessage()
    {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }





}
