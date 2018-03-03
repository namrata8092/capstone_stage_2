package com.nds.pmc.tos.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Namrata on 2/28/2018.
 */

public class ReviewObject {
    @SerializedName("author_name")
    private String authorName;
    @SerializedName("author_url")
    private String authorURL;
    @SerializedName("language")
    private String language;
    @SerializedName("profile_photo_url")
    private String profilePhotoURL;
    @SerializedName("relative_time_description")
    private String relativeTimeDescription;
    @SerializedName("text")
    private String text;
    @SerializedName("time")
    private double time;
    @SerializedName("rating")
    private double rating;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorURL() {
        return authorURL;
    }

    public void setAuthorURL(String authorURL) {
        this.authorURL = authorURL;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public String getRelativeTimeDescription() {
        return relativeTimeDescription;
    }

    public void setRelativeTimeDescription(String relativeTimeDescription) {
        this.relativeTimeDescription = relativeTimeDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewObject that = (ReviewObject) o;

        if (time != that.time) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (!authorName.equals(that.authorName)) return false;
        if (!authorURL.equals(that.authorURL)) return false;
        if (!language.equals(that.language)) return false;
        if (!profilePhotoURL.equals(that.profilePhotoURL)) return false;
        if (!relativeTimeDescription.equals(that.relativeTimeDescription)) return false;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = authorName.hashCode();
        result = 31 * result + authorURL.hashCode();
        result = 31 * result + language.hashCode();
        result = 31 * result + profilePhotoURL.hashCode();
        result = 31 * result + relativeTimeDescription.hashCode();
        result = 31 * result + text.hashCode();
        temp = Double.doubleToLongBits(time);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ReviewObject{" +
                "authorName='" + authorName + '\'' +
                ", authorURL='" + authorURL + '\'' +
                ", language='" + language + '\'' +
                ", profilePhotoURL='" + profilePhotoURL + '\'' +
                ", relativeTimeDescription='" + relativeTimeDescription + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", rating=" + rating +
                '}';
    }
}
