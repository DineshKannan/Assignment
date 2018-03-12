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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import assignment.nobroker.com.nobrokerassignment.repository.Status;
import assignment.nobroker.com.nobrokerassignment.ui.PropertiesAdapter;
import assignment.nobroker.com.nobrokerassignment.ui.PropertyViewModelfactory;
import assignment.nobroker.com.nobrokerassignment.ui.UserViewModel;
import assignment.nobroker.com.nobrokerassignment.util.ListItemClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchActivityFragment extends Fragment implements ListItemClickListener {

    private UserViewModel viewModel;
    private String TAG = SearchActivityFragment.class.getSimpleName();
    private HashMap<String,List<String>> options=new HashMap<String, List<String>>();
    private HashMap<String,String> options_formatted=new HashMap<>();
    private ProgressBar progress_bar;
    private TextView no_properties_found;

    public SearchActivityFragment() {
    }

    public ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        options= (HashMap<String, List<String>>) bundle.getSerializable("options");
        options_formatted=updateFilter(options);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view= inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = root_view.findViewById(R.id.userList);
        progress_bar=root_view.findViewById(R.id.progress_bar);
        no_properties_found=root_view.findViewById(R.id.no_properties_found);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        viewModel = ViewModelProviders.of(this,new PropertyViewModelfactory(options_formatted)).get(UserViewModel.class);

        final PropertiesAdapter userUserAdapter = new PropertiesAdapter(this);

        viewModel.propertiesList.observe(this, pagedList -> {
            userUserAdapter.submitList(pagedList);
        });

        viewModel.networkState.observe(this, networkState -> {
            userUserAdapter.setNetworkState(networkState);
            Log.d(TAG, "Network State Change");
        });

        viewModel.initialState.observe(this, initialState -> {

            if (initialState != null && initialState.getStatus() == Status.RUNNING) {
                progress_bar.setVisibility(View.VISIBLE);
            } else {
                progress_bar.setVisibility(View.GONE);
            }

            if (initialState != null && initialState.getStatus() == Status.FAILED) {
                no_properties_found.setVisibility(View.VISIBLE);
            } else {
                no_properties_found.setVisibility(View.GONE);
            }


            Log.d(TAG, "Network State Change");
        });

        recyclerView.setAdapter(userUserAdapter);
        return root_view;
    }

    @Override
    public void onRetryClick(View view, int position) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_reset).setVisible(false);
        menu.findItem(R.id.action_clear).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                //mFilterDataCallback.back();
                return true;
            case R.id.action_reset:
                //mFilterDataCallback.reset_filters();
                //options.clear();
                //setFilters((ViewGroup) root_view);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public HashMap<String,String> updateFilter(HashMap<String,List<String>> options){
        HashMap<String,String> options_formatted=new HashMap<>();
        for(String key:options.keySet()){
            String filter_string=null;
            for(int i=0;i<options.get(key).size();i++){
                if(filter_string!=null){
                    filter_string+=","+options.get(key).get(i);
                }
                else{
                    filter_string=options.get(key).get(i);
                }
            }
            if(filter_string!=null)
                options_formatted.put(key,filter_string);
        }
        return options_formatted;
    }
}
