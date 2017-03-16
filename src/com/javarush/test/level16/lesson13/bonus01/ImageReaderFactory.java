package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by 1 on 25.10.2016.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes type)
    {
        if(type == ImageTypes.JPG)
            return new JpgReader();
        if(type == ImageTypes.BMP)
            return new BmpReader();
        if(type == ImageTypes.PNG)
            return new PngReader();
        else
            throw new IllegalArgumentException();
    }
}
