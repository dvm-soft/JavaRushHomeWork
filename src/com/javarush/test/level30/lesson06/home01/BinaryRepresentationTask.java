package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Дмитрий on 29.12.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask
{
    final int x;
    public BinaryRepresentationTask(int x)
    {
        this.x = x;
    }

    @Override
    protected String compute()
    {
        int a = x % 2;
        int b = x / 2;
        if(b == 0)
            return String.valueOf(a);
        BinaryRepresentationTask t1 = new BinaryRepresentationTask(b);

        return t1.compute() + String.valueOf(a);
    }
}

/*
final int n;
   Fibonacci(int n) { this.n = n; }
   Integer compute() {
     if (n <= 1)
        return n;
     Fibonacci f1 = new Fibonacci(n - 1);
     f1.fork();
     Fibonacci f2 = new Fibonacci(n - 2);
     return f2.compute() + f1.join();
   }
 */
/*
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return binaryRepresentationMethod(b) + result;
        }
        return result;
 */