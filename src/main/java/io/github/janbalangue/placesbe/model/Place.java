package io.github.janbalangue.placesbe.model;

public class Place {
    private String formattedAddress;
    private String websiteUri;
    private DisplayName displayName;
    private String priceLevel;
    public Place(String formattedAddress, String websiteUri, DisplayName displayName) {
        this.formattedAddress = formattedAddress;
        this.websiteUri = websiteUri;
        this.displayName = displayName;
    }

    public Place(String formattedAddress, String websiteUri, DisplayName displayName, String priceLevel) {
        this(formattedAddress, websiteUri, displayName);
        this.priceLevel = priceLevel;
    }

    public Place getPlace() {
        return this;
    }

    public String getFormattedAddress() {
        return this.formattedAddress;
    }

    public String getName() {
        return this.displayName.getText();
    }

    public String getWebsiteUri() {
        return this.websiteUri;
    }

    public String getPriceLevel() {
        return (this.priceLevel != null) ? this.priceLevel : null;
    }
}


