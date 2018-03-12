package assignment.nobroker.com.nobrokerassignment.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

public class Photo extends BaseObservable implements Serializable
{
    private ImagesMap imagesMap;

    @Bindable
    public ImagesMap getImagesMap() { return this.imagesMap; }

    public void setImagesMap(ImagesMap imagesMap) { this.imagesMap = imagesMap; }

    private boolean displayPic;

    public boolean getDisplayPic() { return this.displayPic; }

    public void setDisplayPic(boolean displayPic) { this.displayPic = displayPic; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Photo{" +
                "imagesMap=" + imagesMap +
                ", displayPic=" + displayPic +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}