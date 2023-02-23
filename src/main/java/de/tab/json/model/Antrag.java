package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Optional;

public class Antrag implements Serializable {
    //@JsonPropertyDescription("Förderfall-ID")
    @JsonProperty(value = "id", required = true)
    private Long id;
    //@JsonPropertyDescription("Benutzername")
    @JsonProperty(value = "username", required = false)
    private Optional<String> username;
    //@JsonPropertyDescription("Förderprogramm")
    @JsonProperty(value = "foerderprogramm", required = true)
    private String foerderprogramm;
    //@JsonPropertyDescription("Fördergegenstand")
    @JsonProperty(value = "foerdergegenstand", required = true)
    private String foerdergegenstand;
    //@JsonPropertyDescription("Berechnungen")
    @JsonProperty(value = "AusgabenBerechnungen", required = false)
    private AusgabenBerechnungen ausgabenBerechnungen;

    @JsonProperty(value= "email", required = false)
    @Size(min= 6, max=127)
    //@JsonSchema(pattern="[a-z0-9\\._%+!$&*=^|~#%{}/\\-]+@([a-z0-9\\-]+\\.){1,}([a-z]{2,22})")
    @JsonSchema(pattern="^(.+)@(.+)$")
    private String email;

    public Optional<String> getUsername() {
        return username;
    }



    public void setUsername(Optional<String> username) {
        if (username.isPresent())
            this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoerderprogramm() {
        return foerderprogramm;
    }

    public void setFoerderprogramm(String foerderprogramm) {
        this.foerderprogramm = foerderprogramm;
    }

    public String getFoerdergegenstand() {
        return foerdergegenstand;
    }

    public void setFoerdergegenstand(String foerdergegenstand) {
        this.foerdergegenstand = foerdergegenstand;
    }

    public AusgabenBerechnungen getAusgabenBerechnungen() {
        return ausgabenBerechnungen;
    }

    public void setAusgabenBerechnungen(AusgabenBerechnungen ausgabenBerechnungen) {
        this.ausgabenBerechnungen = ausgabenBerechnungen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
