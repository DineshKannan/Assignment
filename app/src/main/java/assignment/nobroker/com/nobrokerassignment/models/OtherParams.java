package assignment.nobroker.com.nobrokerassignment.models;

import android.databinding.BaseObservable;

import java.io.Serializable;

public class OtherParams extends BaseObservable implements Serializable
{
    private String city;

    public String getCity() { return this.city; }

    public void setCity(String city) { this.city = city; }

    private String topPropertyId;

    public String getTopPropertyId() { return this.topPropertyId; }

    public void setTopPropertyId(String topPropertyId) { this.topPropertyId = topPropertyId; }

    private int total_count;

    public int getTotalCount() { return this.total_count; }

    public void setTotalCount(int total_count) { this.total_count = total_count; }

    private int count;

    public int getCount() { return this.count; }

    public void setCount(int count) { this.count = count; }

    private String region_id;

    public String getRegionId() { return this.region_id; }

    public void setRegionId(String region_id) { this.region_id = region_id; }

    private String searchToken;

    public String getSearchToken() { return this.searchToken; }

    public void setSearchToken(String searchToken) { this.searchToken = searchToken; }

    @Override
    public String toString() {
        return "OtherParams{" +
                "city='" + city + '\'' +
                ", topPropertyId='" + topPropertyId + '\'' +
                ", total_count=" + total_count +
                ", count=" + count +
                ", region_id='" + region_id + '\'' +
                ", searchToken='" + searchToken + '\'' +
                '}';
    }
}
