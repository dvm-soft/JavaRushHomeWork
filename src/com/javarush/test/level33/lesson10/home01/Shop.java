package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Дмитрий on 18.01.2017.
 */
@XmlRootElement
public class Shop
{
    @XmlElementWrapper(name="goods")
    @XmlElements({
            @XmlElement(name="names") }
    )
    public List<String> goods;

    @XmlElement
    public int count;

    @XmlElement
    public double profit;

    @XmlElements({
            @XmlElement(name="secretData") })
    public List<String> secretData;
}


