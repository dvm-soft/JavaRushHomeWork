package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by 1 on 21.01.2017.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(null, null);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize()
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile())))
        {
            oos.writeObject(entry);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry()
    {
        if (path.toFile().length() == 0)
            return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            return (Entry) ois.readObject();
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        catch (ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public void remove()
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
}

