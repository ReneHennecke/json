package de.tab.json.model;

import java.math.BigDecimal;
import java.util.Optional;

public class AusgabenBerechnungenPauschalbetrag extends AusgabenBerechnungen implements SerializableAusgabenBerechnungenPauschalbetrag {
    private BigDecimal summeAusgabenGesamt;
    private BigDecimal summeAusgabenGesamtBewilligt;

    //private ZuschussPauschalbetrag zuschussPauschalBetrag;
    //private ZuschussPauschalbetrag zuschussPauschalBetragNachPruefung;

    private BigDecimal summeZuschussGesamt;
    private BigDecimal summeZuschussGesamtBewilligt;

    //private List<Teilziel> teilziele;
    private BigDecimal teilzieleGesamt;

    private Optional<String> bemerkungAusgaben;

    //@Override
    public void setSummeAusgabenGesamt(BigDecimal summeAusgabenGesamt) {
        this.summeAusgabenGesamt = summeAusgabenGesamt;
    }

    //@Override
    public void setSummeAusgabenGesamtBewilligt(BigDecimal summeAusgabenGesamt) {
        this.summeAusgabenGesamtBewilligt = summeAusgabenGesamt;
    }

    //@Override
    public void setSummeZuschussGesamt(BigDecimal summeZuschussGesamt) {
        this.summeZuschussGesamt = summeZuschussGesamt;
    }

    //@Override
    public void setSummeZuschussGesamtBewilligt(BigDecimal summeZuschussGesamt) {
        this.summeZuschussGesamtBewilligt = summeZuschussGesamt;
    }

    //@Override
    public BigDecimal getTeilzieleGesamt() {
        return null;
    }

    //@Override
    public void setTeilzieleGesamt(BigDecimal teilzieleGesamt) {

    }

    //@Override
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
        BigDecimal money1 = new BigDecimal("100.26");
        BigDecimal money2 = new BigDecimal("59.75");
        return summeZuschussGesamt.add(money2); //summeZuschussGesamt;
    }

    @Override
    public BigDecimal getSummeZuschussGesamtBewilligt() {
        return summeZuschussGesamtBewilligt;
    }

    @Override
    public Optional<String> getBemerkungAusgaben() {
        return bemerkungAusgaben;
    }

    //@Override
    //public SerializableAusgabenBerechnungenPauschalbetrag as() {
//        return this;
    //  }
}
