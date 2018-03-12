package assignment.nobroker.com.nobrokerassignment.models;

import android.databinding.BaseObservable;

import java.io.Serializable;

public class Score extends BaseObservable implements Serializable
{
    private long lastUpdatedDate;

    public long getLastUpdatedDate() { return this.lastUpdatedDate; }

    public void setLastUpdatedDate(long lastUpdatedDate) { this.lastUpdatedDate = lastUpdatedDate; }

    private float transit;

    public float getTransit() { return this.transit; }

    public void setTransit(float transit) { this.transit = transit; }

    private float lifestyle;

    public float getLifestyle() { return this.lifestyle; }

    public void setLifestyle(float lifestyle) { this.lifestyle = lifestyle; }

    @Override
    public String toString() {
        return "Score{" +
                "lastUpdatedDate=" + lastUpdatedDate +
                ", transit=" + transit +
                ", lifestyle=" + lifestyle +
                '}';
    }
}
