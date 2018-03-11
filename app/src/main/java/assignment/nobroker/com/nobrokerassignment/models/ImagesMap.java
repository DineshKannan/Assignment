package assignment.nobroker.com.nobrokerassignment.models;

import java.io.Serializable;

public class ImagesMap implements Serializable
{
    private String thumbnail;

    public String getThumbnail() { return this.thumbnail; }

    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    private String original;

    public String getOriginal() { return this.original; }

    public void setOriginal(String original) { this.original = original; }

    private String large;

    public String getLarge() { return this.large; }

    public void setLarge(String large) { this.large = large; }

    private String medium;

    public String getMedium() { return this.medium; }

    public void setMedium(String medium) { this.medium = medium; }

    @Override
    public String toString() {
        return "ImagesMap{" +
                "thumbnail='" + thumbnail + '\'' +
                ", original='" + original + '\'' +
                ", large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}