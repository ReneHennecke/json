package de.tab.json.model;

public enum Berechnungsmodell {
    VKO("VKO", "Vereinfachtes Kostenmodell"),
    PAUSCH_BETR("PAUSCH_BETR", "Pauschalbetrag (Gesamtausgaben)"),
    PAUSCH_KAT("PAUSCH_KAT", "Pauschalbetrag (Größenklasse)");

    private final String key;
    private final String beschreibung;

    Berechnungsmodell(final String key, final String beschreibung) {
        this.key = key;
        this.beschreibung = beschreibung;
    }

    public String getKey() {
        return this.key;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }
}
