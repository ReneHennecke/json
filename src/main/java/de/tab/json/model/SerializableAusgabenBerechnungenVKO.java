package de.tab.json.model;

import java.math.BigDecimal;
import java.util.Optional;

public interface SerializableAusgabenBerechnungenVKO {
    Berechnungsmodell getBerechnungsmodell();

    void setBerechnungsmodell(Berechnungsmodell berechnungsmodell);

    //List<VorhabenskategorieAusgaben> getVorhabenskategorieAusgaben();

    //void setVorhabenskategorieAusgaben(List<VorhabenskategorieAusgaben> vorhabenskategorieAusgaben);

    BigDecimal getSummeAusgabenGesamt();

    void setSummeAusgabenGesamt(BigDecimal summeAusgabenGesamt);

    BigDecimal getSummeAusgabenGesamtBewilligt();

    void setSummeAusgabenGesamtBewilligt(BigDecimal summeAusgabenGesamtBewilligt);

    //List<VorhabenskategorieZuschuesse> getVorhabenskategorieZuschuesse();

    //void setVorhabenskategorieZuschuesse(List<VorhabenskategorieZuschuesse> vorhabenskategorieZuschuesse);

    BigDecimal getSummeZuschussGesamt();

    void setSummeZuschussGesamt(BigDecimal summeZuschussGesamt);

    BigDecimal getSummeZuschussGesamtBewilligt();

    void setSummeZuschussGesamtBewilligt(BigDecimal summeZuschussGesamtBewilligt);

    //List<VorhabenskategorieAufteilungen> getVorhabenskategorieAufteilungen();

    //void setVorhabenskategorieAufteilungen(List<VorhabenskategorieAufteilungen> vorhabenskategorieAufteilungen);

    BigDecimal getSummeAufteilungenGesamt();

    void setSummeAufteilungenGesamt(BigDecimal summeAufteilungenGesamt);

    BigDecimal getSummeAufteilungenGesamtBewilligt();

    void setSummeAufteilungenGesamtBewilligt(BigDecimal summeAufteilungenGesamtBewilligt);

    //VorhabenskategorieFinanzierungen getVorhabenskategorieFinanzierungen();

    //void setVorhabenskategorieFinanzierungen(VorhabenskategorieFinanzierungen vorhabenskategorieFinanzierungen);

    BigDecimal getSummeFinanzierungenGesamt();

    void setSummeFinanzierungenGesamt(BigDecimal summeFinanzierungenGesamt);

    BigDecimal getSummeFinanzierungenGesamtBewilligt();

    void setSummeFinanzierungenGesamtBewilligt(BigDecimal summeFinanzierungenGesamtBewilligt);

    Optional<String> getBemerkungAusgaben();

    void setBemerkungAusgaben(Optional<String> bemerkungAusgaben);

    //BezuschussteAusgaben getBezuschussteAusgaben();

    //void setBezuschussteAusgaben(BezuschussteAusgaben bezuschussteAusgaben);
}
