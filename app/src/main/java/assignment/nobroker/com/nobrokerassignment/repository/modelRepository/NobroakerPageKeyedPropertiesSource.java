package assignment.nobroker.com.nobrokerassignment.repository.modelRepository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

import assignment.nobroker.com.nobrokerassignment.api.NobrokerApi;
import assignment.nobroker.com.nobrokerassignment.api.NobrokerService;
import assignment.nobroker.com.nobrokerassignment.models.Data;
import assignment.nobroker.com.nobrokerassignment.models.PropertiesResponse;
import assignment.nobroker.com.nobrokerassignment.repository.NetworkState;
import assignment.nobroker.com.nobrokerassignment.repository.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NobroakerPageKeyedPropertiesSource extends PageKeyedDataSource<Integer,Data> {

    public static final String TAG = NobroakerPageKeyedPropertiesSource.class.getSimpleName();
    NobrokerService nobrokerService;
    LoadInitialParams<Integer> initialParams;
    LoadParams<Integer> afterParams;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;
    private Executor retryExecutor;
    private String filter;
    HashMap<String,String> options_formatted=new HashMap<>();

    public NobroakerPageKeyedPropertiesSource(Executor retryExecutor) {
        nobrokerService = NobrokerApi.createNobroakerService();
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        this.retryExecutor = retryExecutor;
        options_formatted.put("pageNo","0");
    }

    public void updateFilter(HashMap<String,String> options_formatted){
        this.options_formatted=options_formatted;
        options_formatted.put("pageNo","0");
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    public void assignImageUrl(Response<PropertiesResponse> response){
        for(int i=0;i<response.body().getData().size();i++){
            if(response.body().getData().get(i).getPhotos().size()>0)
                response.body().getData().get(i).setImageUrl(response.body().getData().get(i).getPhotos().get(0).getImagesMap().getMedium());
        }

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        Log.i(TAG, "Loading Rang " + 1 + " Count " + params.requestedLoadSize);
        final List<Data> propertiesList = new ArrayList();
        initialParams = params;
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        nobrokerService.getProperties(options_formatted).enqueue(new Callback<PropertiesResponse>() {
            @Override
            public void onResponse(Call<PropertiesResponse> call, Response<PropertiesResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    assignImageUrl(response);
                    propertiesList.addAll(response.body().getData());
                    callback.onResult(propertiesList,0,1);
                    Log.e("API CALL RES", propertiesList.toString());
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                    initialParams = null;
                } else {
                    Log.e("API CALL", response.message());
                    initialLoading.postValue(new NetworkState(Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<PropertiesResponse> call, Throwable t) {
                String errorMessage;
                errorMessage = t.getMessage();
                if (t == null) {
                    errorMessage = "unknown error";
                }
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);
        final List<Data> propertiesList = new ArrayList();
        afterParams = params;

        networkState.postValue(NetworkState.LOADING);
        nobrokerService.getProperties(options_formatted).enqueue(new Callback<PropertiesResponse>() {
            @Override
            public void onResponse(Call<PropertiesResponse> call, Response<PropertiesResponse> response) {
                if (response.isSuccessful()) {
                    assignImageUrl(response);
                    propertiesList.addAll(response.body().getData());
                    callback.onResult(propertiesList,params.key+1);
                    networkState.postValue(NetworkState.LOADED);
                    afterParams = null;
                } else {
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                    Log.e("API CALL", response.message());
                }
            }

            @Override
            public void onFailure(Call<PropertiesResponse> call, Throwable t) {
                String errorMessage;
                errorMessage = t.getMessage();
                if (t == null) {
                    errorMessage = "unknown error";
                }
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });

    }
}
