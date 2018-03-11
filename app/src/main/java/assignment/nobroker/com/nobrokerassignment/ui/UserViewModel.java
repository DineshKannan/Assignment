package assignment.nobroker.com.nobrokerassignment.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import assignment.nobroker.com.nobrokerassignment.models.Data;
import assignment.nobroker.com.nobrokerassignment.repository.NetworkState;
import assignment.nobroker.com.nobrokerassignment.repository.modelRepository.NobroakerDataSourceFactory;
import assignment.nobroker.com.nobrokerassignment.repository.modelRepository.NobroakerPageKeyedPropertiesSource;

/**
 * Created by brijesh on 18/9/17.
 */

public class UserViewModel extends ViewModel {

    public LiveData<PagedList<Data>> propertiesList;
    public LiveData<NetworkState> networkState;
    Executor executor;
    LiveData<NobroakerPageKeyedPropertiesSource> tDataSource;

    public UserViewModel() {
        executor = Executors.newFixedThreadPool(5);
        NobroakerDataSourceFactory nobroakerDataSourceFactory = new NobroakerDataSourceFactory(executor);

        tDataSource = nobroakerDataSourceFactory.getMutableLiveData();

        networkState = Transformations.switchMap(nobroakerDataSourceFactory.getMutableLiveData(), dataSource -> {
            return dataSource.getNetworkState();
        });

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(21)
                        .setPageSize(21).build();

        propertiesList = (new LivePagedListBuilder(nobroakerDataSourceFactory, pagedListConfig))
                .setBackgroundThreadExecutor(executor)
                .build();
    }
}
