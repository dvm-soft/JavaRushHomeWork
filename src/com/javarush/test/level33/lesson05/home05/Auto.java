package com.javarush.test.level33.lesson05.home05;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "className")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Motorbike.class),
        @JsonSubTypes.Type(value = Car.class),
        @JsonSubTypes.Type(value = RaceBike.class)
})

public abstract class Auto {
    protected String name;
    protected String owner;
    protected int age;
}