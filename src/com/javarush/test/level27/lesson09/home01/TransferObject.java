package com.javarush.test.level27.lesson09.home01;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        try
        {
            if (!isValuePresent)
                wait();
            System.out.println("Got: " + value);
            isValuePresent = false;
            notifyAll();
        } catch (InterruptedException e) {}
        return value;
    }

    public synchronized void put(int value) {
        try
        {
            if (isValuePresent)
                wait();
            this.value = value;
            System.out.println("Put: " + value);
            isValuePresent = true;
            notifyAll();
        } catch (InterruptedException e) {}
    }
}
