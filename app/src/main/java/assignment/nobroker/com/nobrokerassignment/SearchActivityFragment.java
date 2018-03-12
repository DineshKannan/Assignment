package assignment.nobroker.com.nobrokerassignment;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import assignment.nobroker.com.nobrokerassignment.ui.PropertiesAdapter;
import assignment.nobroker.com.nobrokerassignment.ui.UserViewModel;
import assignment.nobroker.com.nobrokerassignment.util.ListItemClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchActivityFragment extends Fragment implements ListItemClickListener {

    private UserViewModel viewModel;
    private String TAG = SearchActivityFragment.class.getSimpleName();

    public SearchActivityFragment() {
    }

    public ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar mActionBar=getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("NoBroker");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view= inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = root_view.findViewById(R.id.userList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        final PropertiesAdapter userUserAdapter = new PropertiesAdapter(this);

        viewModel.propertiesList.observe(this, pagedList -> {
            userUserAdapter.submitList(pagedList);
        });

        viewModel.networkState.observe(this, networkState -> {
            userUserAdapter.setNetworkState(networkState);
            Log.d(TAG, "Network State Change");
        });

        recyclerView.setAdapter(userUserAdapter);
        return root_view;
    }

    @Override
    public void onRetryClick(View view, int position) {

    }

    public void updateFilter(HashMap<String,List<String>> options){
        HashMap<String,String> options_formatted=new HashMap<>();
        for(String key:options.keySet()){
            String filter_string="";
            for(int i=0;i<options.get(key).size();i++){
                filter_string+=","+options.get(key).get(i);
            }
            options_formatted.put(key,filter_string);
        }
        Log.e(TAG,"Filter Serialized in Search Fragment "+options_formatted);
        viewModel.updateFilter(options_formatted);
    }
}
