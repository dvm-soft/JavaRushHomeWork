package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Дмитрий on 02.01.2017.
 */
public class BotClient extends Client
{
    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        return String.format("date_bot_%02d", (int) (Math.random() * 100));
    }

    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends Client.SocketThread
    {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
            if (message.split(": ").length < 2)
                return;
            String userName = message.split(": ")[0];
            String messageText = message.split(": ")[1].toLowerCase();
            String format = "";
            switch (messageText)
            {
                case "дата": format = "d.MM.YYYY"; break;
                case "день": format = "d"; break;
                case "месяц": format = "MMMM"; break;
                case "год": format = "YYYY"; break;
                case "время": format = "H:mm:ss"; break;
                case "час": format = "H"; break;
                case "минуты": format = "m"; break;
                case "секунды": format = "s"; break;
            }
            if (!format.isEmpty())
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                sendTextMessage("Информация для " + userName + ": " + simpleDateFormat.format(Calendar.getInstance().getTime()));
            }

        }
    }
}
