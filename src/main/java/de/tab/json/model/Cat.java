package de.tab.json.model;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

//@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class Cat extends Animal implements Serializable {
    @JsonProperty(value= "name", required = true)
    @NotEmpty
    private String name = "Cat Ballou";
    @JsonProperty(value= "weight", required = false)
    private BigDecimal weight;

    @JsonProperty(value= "length", required = false)
    private Optional<BigDecimal> length;

    public Cat() {

    }

    @Override
    public String getName() {

        return this.name;
    }

    public void setName(String name) {

    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Optional<BigDecimal> getLength() {
        return length;
    }

    public void setLength(Optional<BigDecimal> length) {
        this.length = length;
    }
}
