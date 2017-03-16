package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.*;


public class FileConsoleWriter extends FileWriter{

/*    public static void main(String[]args) throws Exception{
        FileConsoleWriter fileWriter = new FileConsoleWriter("c:/!/1.txt");
        CharSequence obj = "hello";
        fileWriter.append(obj);
        fileWriter.close();
    }
*/

    public OutputStreamWriter console = new OutputStreamWriter(System.out);

    public FileConsoleWriter(String fileName) throws IOException
    {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException
    {
        super(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException
    {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException
    {
        super(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd)
    {
        super(fd);
    }

    @Override
    public void write(int c) throws IOException
    {
        super.write(c);
        console.write(c);
        console.flush();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        super.write(cbuf, off, len);
        console.write(cbuf, off, len);
        console.flush();
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        super.write(str, off, len);
        console.write(str, off, len);
        console.flush();
    }

}
