package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 2/28/2018.
 */

public class DetailedSearchResult {

    @SerializedName("address_components")
    private List<AddressComponent> addressComponents;
    @SerializedName("adr_address")
    private String adrAddress;
    @SerializedName("geometry")
    private GeometryObject geometry;
    @SerializedName("formatted_address")
    private String formattedAddress;
    @SerializedName("formatted_phone_number")
    private String formattedPhoneNumber;
    @SerializedName("international_phone_number")
    private String ISDPhoneNumber;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("place_id")
    private String placeID;
    @SerializedName("reference")
    private String reference;
    @SerializedName("scope")
    private String scope;
    @SerializedName("url")
    private String url;
    @SerializedName("vicinity")
    private String vicinity;
    @SerializedName("website")
    private String website;
    @SerializedName("types")
    private List<String> types;
    @SerializedName("rating")
    private double rating;
    @SerializedName("utc_offset")
    private double utcOffset;
    @SerializedName("reviews")
    private List<ReviewObject> reviews;
    @SerializedName("photos")
    private List<PhotoObject> photos;
    @SerializedName("opening_hours")
    private OpeningHourObject openingHours;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getAdrAddress() {
        return adrAddress;
    }

    public void setAdrAddress(String adrAddress) {
        this.adrAddress = adrAddress;
    }

    public GeometryObject getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryObject geometry) {
        this.geometry = geometry;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getISDPhoneNumber() {
        return ISDPhoneNumber;
    }

    public void setISDPhoneNumber(String ISDPhoneNumber) {
        this.ISDPhoneNumber = ISDPhoneNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(double utcOffset) {
        this.utcOffset = utcOffset;
    }

    public List<ReviewObject> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewObject> reviews) {
        this.reviews = reviews;
    }

    public List<PhotoObject> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoObject> photos) {
        this.photos = photos;
    }

    public OpeningHourObject getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHourObject openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetailedSearchResult that = (DetailedSearchResult) o;

        if (Double.compare(that.rating, rating) != 0) return false;
        if (Double.compare(that.utcOffset, utcOffset) != 0) return false;
        if (!addressComponents.equals(that.addressComponents)) return false;
        if (!adrAddress.equals(that.adrAddress)) return false;
        if (!geometry.equals(that.geometry)) return false;
        if (!formattedAddress.equals(that.formattedAddress)) return false;
        if (!formattedPhoneNumber.equals(that.formattedPhoneNumber)) return false;
        if (!ISDPhoneNumber.equals(that.ISDPhoneNumber)) return false;
        if (!icon.equals(that.icon)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!placeID.equals(that.placeID)) return false;
        if (!reference.equals(that.reference)) return false;
        if (!scope.equals(that.scope)) return false;
        if (!url.equals(that.url)) return false;
        if (!vicinity.equals(that.vicinity)) return false;
        if (!website.equals(that.website)) return false;
        if (!types.equals(that.types)) return false;
        if (!reviews.equals(that.reviews)) return false;
        if (!photos.equals(that.photos)) return false;
        return openingHours.equals(that.openingHours);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = addressComponents.hashCode();
        result = 31 * result + adrAddress.hashCode();
        result = 31 * result + geometry.hashCode();
        result = 31 * result + formattedAddress.hashCode();
        result = 31 * result + formattedPhoneNumber.hashCode();
        result = 31 * result + ISDPhoneNumber.hashCode();
        result = 31 * result + icon.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + placeID.hashCode();
        result = 31 * result + reference.hashCode();
        result = 31 * result + scope.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + vicinity.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + types.hashCode();
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(utcOffset);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + reviews.hashCode();
        result = 31 * result + photos.hashCode();
        result = 31 * result + openingHours.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DetailedSearchResult{" +
                "addressComponents=" + addressComponents +
                ", adrAddress='" + adrAddress + '\'' +
                ", geometry=" + geometry +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", formattedPhoneNumber='" + formattedPhoneNumber + '\'' +
                ", ISDPhoneNumber='" + ISDPhoneNumber + '\'' +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", placeID='" + placeID + '\'' +
                ", reference='" + reference + '\'' +
                ", scope='" + scope + '\'' +
                ", url='" + url + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", website='" + website + '\'' +
                ", types=" + types +
                ", rating=" + rating +
                ", utcOffset=" + utcOffset +
                ", reviews=" + reviews +
                ", photos=" + photos +
                ", openingHours=" + openingHours +
                '}';
    }
}
