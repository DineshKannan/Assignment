package assignment.nobroker.com.nobrokerassignment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import assignment.nobroker.com.nobrokerassignment.ui.PlaceAutocompleteAdapter;
import assignment.nobroker.com.nobrokerassignment.util.FilterDataCallback;

public class SearchFilterFragment extends Fragment {

    protected GeoDataClient mGeoDataClient;
    private PlaceAutocompleteAdapter mAdapter;
    private AutoCompleteTextView mAutocompleteView;
    private HashMap<String,List<String>> options=new HashMap<>();
    private static final String TAG=SearchFilterFragment.class.getSimpleName();
    private FilterDataCallback mFilterDataCallback;
    private View root_view;
    private TextView min_range,max_range;
    private CrystalRangeSeekbar rangeSeekbar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mFilterDataCallback = (FilterDataCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle=getArguments();
        options= (HashMap<String, List<String>>) bundle.getSerializable("options");
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_reset).setVisible(true);
        menu.findItem(R.id.action_clear).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root_view= inflater.inflate(R.layout.fragment_filter, container, false);
        mAutocompleteView = (AutoCompleteTextView) root_view.findViewById(R.id.autocomplete_places);
        setFilters((ViewGroup) root_view);
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        LatLng north_east=new LatLng(13.465390871221931, 78.00780566406252);
        LatLng south_west=new LatLng(12.749336958395071, 77.17558496093739);
        LatLngBounds bangaloreBounds=new LatLngBounds(south_west,north_east);

        mAdapter = new PlaceAutocompleteAdapter(getContext(), mGeoDataClient, bangaloreBounds, null);
        mAutocompleteView.setAdapter(mAdapter);

        min_range=root_view.findViewById(R.id.min_range);
        max_range=root_view.findViewById(R.id.max_range);

        rangeSeekbar = (CrystalRangeSeekbar) root_view.findViewById(R.id.price_range_seekbar);

        if(options.containsKey("rent")){
            String[] price_range=options.get("rent").get(0).split(",");
            Log.e(SearchFilterFragment.class.getSimpleName(),"Price Range is "+price_range[0]+","+price_range[1]);
            if(price_range[0]!=null){
                min_range.setText(getResources().getString(R.string.rupees_symbol)+price_range[0]);
                rangeSeekbar.setMinStartValue(Float.parseFloat(price_range[0]));
                rangeSeekbar.apply();
            }

            if(price_range[1]!=null){
                max_range.setText(getResources().getString(R.string.rupees_symbol)+price_range[1]);
                rangeSeekbar.setMaxStartValue(Float.parseFloat(price_range[1]));
                rangeSeekbar.apply();
            }
        }
        else{
            Log.e(SearchFilterFragment.class.getSimpleName(),"Filters Range is "+options.toString());
        }

        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                mFilterDataCallback.notify_filter_data("rent",String.valueOf(minValue)+","+String.valueOf(maxValue));
                min_range.setText(getResources().getString(R.string.rupees_symbol)+String.valueOf(minValue));
                max_range.setText(" to  "+getResources().getString(R.string.rupees_symbol)+String.valueOf(maxValue));
            }
        });

        return root_view;
    }

    private void setFilters(ViewGroup root){
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                setFilters((ViewGroup) child);
            }

            final String tagObj = (String) child.getTag();
            for(String key:options.keySet()){
                for(int k=0;k<options.get(key).size();k++){
                    String tmp_tag=key+":"+options.get(key).get(k)+":";
                    if (tagObj != null && tagObj.contains(tmp_tag)) {
                        updateTheme((Button) child,true);
                        child.setTag(tmp_tag+"1");
                    }
                }
            }
        }
    }

    private void reSetFilters(ViewGroup root){
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                reSetFilters((ViewGroup) child);
            }

            final String tagObj = (String) child.getTag();
            if (tagObj != null) {
                updateTheme((Button) child,false);
                String[] split=tagObj.split(":");
                String filter_type=split[0];
                String filter_value=split[1];
                child.setTag(filter_type+":"+filter_value+":"+"0");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                mFilterDataCallback.back();
                return true;
            case R.id.action_reset:
                mFilterDataCallback.reset_filters();
                options.clear();
                options=new HashMap<>();
                reSetFilters((ViewGroup) root_view);
                resetPriceRange();
                resetPlaces();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetPlaces() {
        mAutocompleteView.setText("");
    }

    private void resetPriceRange() {
        min_range.setText(getResources().getString(R.string.rupees_symbol)+"0");
        max_range.setText(getResources().getString(R.string.rupees_symbol)+"500000");
        rangeSeekbar.setMinStartValue(0);
        rangeSeekbar.apply();
        rangeSeekbar.setMaxStartValue(500000);
        rangeSeekbar.apply();

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


    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final AutocompletePrediction item = mAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);

            Log.i(TAG, "Autocomplete item selected: " + primaryText);

            Task<PlaceBufferResponse> placeResult = mGeoDataClient.getPlaceById(placeId);
            placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallback);

            Toast.makeText(getContext(), "Clicked: " + primaryText,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
        }
    };

    private OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback
            = new OnCompleteListener<PlaceBufferResponse>() {
        @Override
        public void onComplete(Task<PlaceBufferResponse> task) {
            try {
                PlaceBufferResponse places = task.getResult();
                final Place place = places.get(0);
                mFilterDataCallback.notify_filter_data("lat_lng",place.getLatLng().latitude+","+place.getLatLng().longitude);
                places.release();
            } catch (RuntimeRemoteException e) {
                Log.e(TAG, "Place query did not complete.", e);
                return;
            }
        }
    };

}
