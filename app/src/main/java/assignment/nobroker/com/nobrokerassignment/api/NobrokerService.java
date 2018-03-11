package assignment.nobroker.com.nobrokerassignment.api;

import java.util.Map;

import assignment.nobroker.com.nobrokerassignment.models.PropertiesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface NobrokerService {
    @GET("api/v1/property/filter/region/ChIJLfyY2E4UrjsRVq4AjI7zgRY/")
    Call<PropertiesResponse> getProperties(@Query("pageNo") int pageNo);
}
