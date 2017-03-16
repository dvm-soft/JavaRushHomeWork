package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static
    {
        threads.add(new InfinityThread());
        threads.add(new IntExThread());
        threads.add(new HiThread());
        threads.add(new WarningThread());
        threads.add(new InputThread());

//        threads.get(4).start();
/*        threads.get(3).start();
        try
        {
            Thread.sleep(3000);
        } catch (Exception e) {}
        ((WarningThread)threads.get(3)).showWarning()   ;
*/

/*
        threads.get(2).start();
        threads.get(1).start();
        try
        {
            Thread.sleep(4000);
        } catch (Exception e) {}
        threads.get(1).interrupt();
*/

    }
    public static void main(String[] args)
    {

    }
    public static class InfinityThread extends Thread
    {
        public void run()
        {
            while (true) { }
        }
    }
    public static class IntExThread extends Thread
    {
        public void run()
        {
            try
            {
                while (true)
                {
                    sleep(500);
                }

            } catch (Exception e) {System.out.println("InterruptedException");}
        }
    }
    public static class HiThread extends Thread
    {
        public void run()
        {
            while (true) {
                try
                {
                    Thread.sleep(500);
                } catch (Exception e) {}
                System.out.println("Ура");
            }
        }
    }
    public static class WarningThread extends Thread implements Message
    {
        @Override
        public void showWarning()
        {
            interrupt();
            try
            {
                join();
            } catch (InterruptedException e) {}
        }

        public void run()
        {
            while (!isInterrupted()) {}
        }
    }
    public static class InputThread extends Thread
    {
        public void run()
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            int summa = 0;
            try
            {
            while (true)
                {
                        str = reader.readLine();
                        if ("N".equals(str))
                            break;
                        summa += Integer.parseInt(str);
                }
                reader.close();
            } catch (Exception e) {}
            System.out.println(summa);
        }
    }

}
