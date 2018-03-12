package assignment.nobroker.com.nobrokerassignment.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import assignment.nobroker.com.nobrokerassignment.R;

public class ImagesMap extends BaseObservable implements Serializable
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

    @Bindable
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