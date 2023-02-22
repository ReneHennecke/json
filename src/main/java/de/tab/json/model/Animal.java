package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public abstract class Animal implements Serializable {

    public abstract String getName();

    /*
    private static Animal[] animals= null;
    public static Animal[] getAnimals(){
        if (animals==null){
            animals = new Animal[]{
                new Dog(),
                new Cat(),
                new Lion()
            };
        }
        return animals;
    }
     */
}
