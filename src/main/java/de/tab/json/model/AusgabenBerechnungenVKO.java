package de.tab.json.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

public class AusgabenBerechnungenVKO extends AusgabenBerechnungen implements SerializableAusgabenBerechnungenVKO {
    //@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
    private Berechnungsmodell berechnungsmodell;

    //private List<VorhabenskategorieAusgaben> vorhabenskategorieAusgaben;
    private BigDecimal summeAusgabenGesamt;
    private BigDecimal summeAusgabenGesamtBewilligt;

    //private List<VorhabenskategorieZuschuesse> vorhabenskategorieZuschuesse;
    private BigDecimal summeZuschussGesamt;
    private BigDecimal summeZuschussGesamtBewilligt;

    //private List<VorhabenskategorieAufteilungen> vorhabenskategorieAufteilungen;
    private BigDecimal summeAufteilungenGesamt;
    private BigDecimal summeAufteilungenGesamtBewilligt;

    //private VorhabenskategorieFinanzierungen vorhabenskategorieFinanzierungen;
    private BigDecimal summeFinanzierungenGesamt;
    private BigDecimal summeFinanzierungenGesamtBewilligt;

    //private BezuschussteAusgaben bezuschussteAusgaben;

    private Optional<String> bemerkungAusgaben;

    public AusgabenBerechnungenVKO() {
        berechnungsmodell = Berechnungsmodell.VKO;
    }

//    @Override
    public void setSummeAusgabenGesamt(BigDecimal summeAusgabenGesamt) {
        this.summeAusgabenGesamt = summeAusgabenGesamt;
    }

//    @Override
    public void setSummeAusgabenGesamtBewilligt(BigDecimal summeAusgabenGesamtBewilligt) {
        this.summeAusgabenGesamtBewilligt = summeAusgabenGesamtBewilligt;
    }

//    @Override
    public void setSummeZuschussGesamt(BigDecimal summeZuschussGesamt) {
        this.summeZuschussGesamt = summeZuschussGesamt;
    }

//    @Override
    public void setSummeZuschussGesamtBewilligt(BigDecimal summeZuschussGesamtBewilligt) {
        this.summeZuschussGesamtBewilligt = summeZuschussGesamtBewilligt;
    }

//    @Override
    public BigDecimal getSummeAufteilungenGesamt() {
        return new BigDecimal(10700);
    }

//    @Override
    public void setSummeAufteilungenGesamt(BigDecimal summeAufteilungenGesamt) {
        this.summeAufteilungenGesamt = summeAufteilungenGesamt;
    }

//    @Override
    public BigDecimal getSummeAufteilungenGesamtBewilligt() {
        return summeAufteilungenGesamtBewilligt;
    }

//    @Override
    public void setSummeAufteilungenGesamtBewilligt(BigDecimal summeAufteilungenGesamtBewilligt) {
        this.summeAufteilungenGesamtBewilligt = summeAufteilungenGesamtBewilligt;
    }

//    @Override
    public BigDecimal getSummeFinanzierungenGesamt() {
        return summeFinanzierungenGesamt;
    }

//    @Override
    public void setSummeFinanzierungenGesamt(BigDecimal summeFinanzierungenGesamt) {
        this.summeFinanzierungenGesamt = summeFinanzierungenGesamt;
    }

//    @Override
    public BigDecimal getSummeFinanzierungenGesamtBewilligt() {
        return summeFinanzierungenGesamtBewilligt;
    }

//    @Override
    public void setSummeFinanzierungenGesamtBewilligt(BigDecimal summeFinanzierungenGesamtBewilligt) {
        this.summeFinanzierungenGesamtBewilligt = summeFinanzierungenGesamtBewilligt;
    }

//    @Override
    public void setBemerkungAusgaben(Optional<String> bemerkungAusgaben) {
        this.bemerkungAusgaben = bemerkungAusgaben;
    }

    @Override
    public BigDecimal getSummeAusgabenGesamt() {
        return summeAusgabenGesamt;
    }

    @Override
    public BigDecimal getSummeAusgabenGesamtBewilligt() {
        return summeAusgabenGesamtBewilligt;
    }

    @Override
    public BigDecimal getSummeZuschussGesamt() {
        return summeZuschussGesamt;
    }

    @Override
    public BigDecimal getSummeZuschussGesamtBewilligt() {
        return summeZuschussGesamtBewilligt;
    }

    @Override
    public Optional<String> getBemerkungAusgaben() {
        return bemerkungAusgaben;

    }

    //   @Override
    public SerializableAusgabenBerechnungenVKO as() {
        return this;
    }

    public Berechnungsmodell getBerechnungsmodell() {
        return berechnungsmodell;
    }

    public void setBerechnungsmodell(Berechnungsmodell berechnungsmodell) {
        this.berechnungsmodell = berechnungsmodell;
    }
}
