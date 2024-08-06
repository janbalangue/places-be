package io.github.janbalangue.placesbe.model;

public class DisplayName {
    private final String text;
    private final String languageCode;
    public DisplayName(String text, String languageCode) {
        this.text = text;
        this.languageCode = languageCode;
    }
    public String getText() {
        return this.text;
    }
    public String getLanguageCode() {
        return this.languageCode;
    }
}

