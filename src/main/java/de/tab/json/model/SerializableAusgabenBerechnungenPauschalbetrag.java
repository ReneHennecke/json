package de.tab.json.model;

import java.math.BigDecimal;
import java.util.Optional;

public interface SerializableAusgabenBerechnungenPauschalbetrag {
    BigDecimal getSummeAusgabenGesamt();

    void setSummeAusgabenGesamt(BigDecimal summeAusgabenGesamt);

    BigDecimal getSummeAusgabenGesamtBewilligt();

    void setSummeAusgabenGesamtBewilligt(BigDecimal summeAusgabenGesamt);

    //ZuschussPauschalbetrag getZuschussPauschalBetrag();
    //ZuschussPauschalbetrag getZuschussPauschalBetragNachPruefung();

    //void setZuschussPauschalBetrag(ZuschussPauschalbetrag zuschussPauschalbetrag);
    //void setZuschussPauschalBetragNachPruefung(ZuschussPauschalbetrag zuschussPauschalbetrag);

    BigDecimal getSummeZuschussGesamt();

    void setSummeZuschussGesamt(BigDecimal summeZuschussGesamt);

    BigDecimal getSummeZuschussGesamtBewilligt();

    void setSummeZuschussGesamtBewilligt(BigDecimal summeZuschussGesamt);

    //List<Teilziel> getTeilziele();

    //void setTeilziele(List<Teilziel> teilziele);

    BigDecimal getTeilzieleGesamt();

    void setTeilzieleGesamt(BigDecimal teilzieleGesamt);

    Optional<String> getBemerkungAusgaben();

    void setBemerkungAusgaben(Optional<String> bemerkungAusgaben);

}
