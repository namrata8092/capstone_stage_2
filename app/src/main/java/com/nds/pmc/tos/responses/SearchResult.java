package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Namrata on 11/12/2017.
 */

public class SearchResult {
    @SerializedName("geometry")
    private GeometryObject geometry;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("rating")
    private double rating;
    @SerializedName("reference")
    private String reference;
    @SerializedName("scope")
    private String scope;
    @SerializedName("vicinity")
    private String vicinity;
    @SerializedName("types")
    private List<String> types;
    @SerializedName("opening_hours")
    private OpeningHourObject openingHours;
    @SerializedName("photos")
    private List<PhotoObject> photos;

    public GeometryObject getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryObject geometry) {
        this.geometry = geometry;
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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public OpeningHourObject getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHourObject openingHours) {
        this.openingHours = openingHours;
    }

    public List<PhotoObject> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoObject> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        if (Double.compare(that.rating, rating) != 0) return false;
        if (!geometry.equals(that.geometry)) return false;
        if (!icon.equals(that.icon)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!placeId.equals(that.placeId)) return false;
        if (!reference.equals(that.reference)) return false;
        if (!scope.equals(that.scope)) return false;
        if (!vicinity.equals(that.vicinity)) return false;
        if (!types.equals(that.types)) return false;
        if (!openingHours.equals(that.openingHours)) return false;
        return photos.equals(that.photos);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = geometry.hashCode();
        result = 31 * result + icon.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + placeId.hashCode();
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + reference.hashCode();
        result = 31 * result + scope.hashCode();
        result = 31 * result + vicinity.hashCode();
        result = 31 * result + types.hashCode();
        result = 31 * result + openingHours.hashCode();
        result = 31 * result + photos.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", reference='" + reference + '\'' +
                ", scope='" + scope + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", types=" + types +
                ", openingHours=" + openingHours +
                ", photos=" + photos +
                '}';
    }
}
