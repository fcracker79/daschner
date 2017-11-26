package io.mirko.entity;

public enum Country {
    ITALY ("IT"),
    UNITED_STATES ("US"),
    UNITED_KINGDOM ("UK"),
    FRANCE ("FR"),
    GERMANY ("DE");

    private final String strCountry;
    Country(String strCountry) {
        this.strCountry = strCountry;
    }

    public String toString() {
        return this.strCountry;
    }
}
