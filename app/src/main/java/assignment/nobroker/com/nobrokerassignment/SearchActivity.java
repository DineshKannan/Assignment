package assignment.nobroker.com.nobrokerassignment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import assignment.nobroker.com.nobrokerassignment.util.FilterDataCallback;

public class SearchActivity extends AppCompatActivity implements FilterDataCallback{

    private FragmentManager fragmentManager;
    private HashMap<String,List<String>> options=new HashMap<>();
    private static final String TAG=SearchActivity.class.getSimpleName();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("NoBroker");

        fragmentManager = getSupportFragmentManager();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instantiateFilterFragment();
            }
        });

        instantiateSearchFragment();
    }

    private void instantiateSearchFragment() {
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        SearchActivityFragment searchActivityFragment=new SearchActivityFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("options",options);
        searchActivityFragment.setArguments(bundle);
        mFragmentTransaction.add(R.id.fragment_placeholder,searchActivityFragment);
        mFragmentTransaction.commit();
    }

    private void instantiateFilterFragment() {
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        SearchFilterFragment searchFilterFragment=new SearchFilterFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("options",options);
        searchFilterFragment.setArguments(bundle);
        mFragmentTransaction.replace(R.id.fragment_placeholder,searchFilterFragment,"FilterFragment");
        mFragmentTransaction.addToBackStack("Search");
        mFragmentTransaction.commit();
        displayFilterActions();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            fab.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("NoBroker");
        }
        else{
            super.onBackPressed();
        }
    }

    public void updateTheme(Button v, boolean is_selected){
        if(is_selected){
            v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            v.setTextColor(getResources().getColor(R.color.card_background_color));
        }
        else{
            v.setBackgroundColor(getResources().getColor(R.color.button_background_color));
            v.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    public void addFilter(View v){
        String tag_mapping= (String) v.getTag();
        if(tag_mapping!=null){
            String[] split=tag_mapping.split(":");
            int is_selected=Integer.parseInt(split[2]);
            String filter_type=split[0];
            String filter_value=split[1];

            if(is_selected==0){
                if(!options.containsKey(filter_type)){
                    options.put(filter_type,new ArrayList<String>());
                }
                options.get(filter_type).add(split[1]);
                v.setTag(filter_type+":"+filter_value+":"+"1");
                updateTheme((Button) v,true);
            }
            else{
                for(int i=0;i<options.get(filter_type).size();i++){
                    if(options.get(filter_type).get(i).equals(filter_value)){
                        options.get(filter_type).remove(i);
                    }
                }
                v.setTag(filter_type+":"+filter_value+":"+"0");
                updateTheme((Button) v,false);
            }

        }
        else{
            Log.e(TAG,"Filter Option Tag is Empty");
        }
        Log.e(TAG,"Serialized Filter Mapping is : "+options.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void notify_filter_data(String key, String value) {
        options.put(key,new ArrayList<>());
        options.get(key).add(value);
    }

    @Override
    public void back(){
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
        displaySearchActions();
    }

    public void displaySearchActions(){
        fab.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle("NoBroker");
    }

    public void displayFilterActions(){
        fab.setVisibility(View.GONE);
        getSupportActionBar().setTitle("Filter By");
    }

    @Override
    public void reset_filters(){
        options.clear();
        options=new HashMap<>();
    }


    public void applyFilter(View v){
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }

        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        SearchActivityFragment searchActivityFragment=new SearchActivityFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("options",options);
        searchActivityFragment.setArguments(bundle);
        mFragmentTransaction.replace(R.id.fragment_placeholder,searchActivityFragment);
        mFragmentTransaction.commit();
        displaySearchActions();
    }


}
