package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class Dog extends Animal implements Serializable {
    @JsonProperty(value= "name", required = false)
    @Size(min= 4)
    private String name = "Nala";

    @Override
    public String getName() {
        return this.name;
    }
}
