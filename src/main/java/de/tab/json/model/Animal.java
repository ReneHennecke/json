package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

//@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public abstract class Animal implements Serializable {
    public abstract String getName();

}
