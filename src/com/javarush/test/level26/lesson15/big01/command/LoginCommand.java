package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * Created by Дмитрий on 06.12.2016.
 */
public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private static ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login", Locale.getDefault());
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while(true)
        {
            String inputCard = ConsoleHelper.readString();
            String inputPIN = ConsoleHelper.readString();

            if (!inputCard.matches("^\\d{12}$") | !inputPIN.matches("^\\d{4}$"))
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            } else
            {
                String pin = validCreditCards.getString(inputCard);
                if (inputPIN.equals(pin))
                {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),inputCard));
                    break;
                }
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), inputCard));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
