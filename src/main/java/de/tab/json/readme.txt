Abhängigkeiten
    victools    = Schema, Validierungsmodule
    imifou      = Validierungsmodul


SchemaGenerator
    Annotation für Properties
        @JsonProperty=          Umwidmung, required
        @NotNull, @NotEmpty
        @Size=                  Array, Strings
        @Json..Description=     Dokumentation, keine Auswirkung auf Validierung
        @Jsonschema(pattern=    String match pattern

        Generierung MasterSchema mit SubSchema (0..n) möglich


Validierung
    a) Laden des Schemas als Masterschema, Austausch der Subschemata, Validierung oder
    b) Laden des Schemas welches bereits aus Masterschema + Subschemata besteht, Validierung


Weitere Prüfungen
    Austausch Class in required array
    Prüfung Enum
    Prüfung Date, Time, Datetime, Timestamps



