package assignment.nobroker.com.nobrokerassignment.ui;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.HashMap;
import java.util.List;

public class PropertyViewModelfactory extends ViewModelProvider.NewInstanceFactory {
    private String mParam;
    private HashMap<String,String> options;

    public PropertyViewModelfactory(HashMap<String,String> options) {
        this.options = options;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new UserViewModel(options);
    }
}