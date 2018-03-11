package assignment.nobroker.com.nobrokerassignment.repository.modelRepository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import java.util.concurrent.Executor;

public class NobroakerDataSourceFactory implements DataSource.Factory {

    MutableLiveData<NobroakerPageKeyedPropertiesSource> mutableLiveData;
    NobroakerPageKeyedPropertiesSource itemKeyedUserDataSource;
    Executor executor;

    public NobroakerDataSourceFactory(Executor executor) {
        this.mutableLiveData = new MutableLiveData<NobroakerPageKeyedPropertiesSource>();
        this.executor = executor;
    }


    @Override
    public DataSource create() {
        itemKeyedUserDataSource = new NobroakerPageKeyedPropertiesSource(executor);
        mutableLiveData.postValue(itemKeyedUserDataSource);
        return itemKeyedUserDataSource;
    }

    public MutableLiveData<NobroakerPageKeyedPropertiesSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
