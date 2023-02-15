package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Home {
    private Integer id;
    @JsonProperty(value="Animal", required = false)
    private Animal animal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
