package com.test.demo.myapp.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = Boat.class), @JsonSubTypes.Type(value = Car.class), @JsonSubTypes.Type(value = BoatCar.class, name = "Boat-Car") })
public abstract class Transportation {
    public String type;
}
