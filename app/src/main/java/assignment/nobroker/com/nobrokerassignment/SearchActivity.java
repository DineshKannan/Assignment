package assignment.nobroker.com.nobrokerassignment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import assignment.nobroker.com.nobrokerassignment.util.ListItemClickListener;

public class SearchActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        mFragmentTransaction.add(R.id.fragment_placeholder,new SearchActivityFragment());
        mFragmentTransaction.addToBackStack("Search");
        mFragmentTransaction.commit();
    }

    private void instantiateFilterFragment() {
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        mFragmentTransaction.add(R.id.fragment_placeholder,new SearchFilterFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }


}
