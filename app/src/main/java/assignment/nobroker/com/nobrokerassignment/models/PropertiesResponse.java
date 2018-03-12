package assignment.nobroker.com.nobrokerassignment.models;

import android.databinding.BaseObservable;

import java.io.Serializable;
import java.util.ArrayList;

public class PropertiesResponse extends BaseObservable implements Serializable{
    private int status;

    public int getStatus() { return this.status; }

    public void setStatus(int status) { this.status = status; }

    private int statusCode;

    public int getStatusCode() { return this.statusCode; }

    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private OtherParams otherParams;

    public OtherParams getOtherParams() { return this.otherParams; }

    public void setOtherParams(OtherParams otherParams) { this.otherParams = otherParams; }

    private ArrayList<Data> data;

    public ArrayList<Data> getData() { return this.data; }

    public void setData(ArrayList<Data> data) { this.data = data; }

    @Override
    public String toString() {
        return "PropertiesResponse{" +
                "status=" + status +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", otherParams=" + otherParams.toString() +
                ", data=" + data.toString() +
                '}';
    }
}
