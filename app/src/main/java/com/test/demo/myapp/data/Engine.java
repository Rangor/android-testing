package com.test.demo.myapp.data;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ElectricEngine.class), @JsonSubTypes.Type(value = PetrolEngine.class) })
public abstract class Engine {
    public String type;
}
