package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public abstract class AusgabenBerechnungen implements Serializable {
    public abstract BigDecimal getSummeAusgabenGesamt();

    public abstract BigDecimal getSummeAusgabenGesamtBewilligt();

    public abstract BigDecimal getSummeZuschussGesamt();

    public abstract BigDecimal getSummeZuschussGesamtBewilligt();

    public abstract Optional<String> getBemerkungAusgaben();

    @JsonIgnore
    private Antrag vorgang;
    private Long id;

    public Antrag getVorhaben() {
        return this.vorgang;
    }

    public void setVorhaben(final Antrag vorgang) {
        this.vorgang = vorgang;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    // TODO Identity?
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        //if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
        //    return false;
        //}
        final AusgabenBerechnungen ausgaben = (AusgabenBerechnungen) o;
        return this.id != null && Objects.equals(this.id, ausgaben.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
