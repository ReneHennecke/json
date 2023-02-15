package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class Dog extends Animal {
    private String name = "Nala";


    @Override
    @JsonProperty(value= "name", required = false)
    public String getName() {
        return this.name;
    }
}
