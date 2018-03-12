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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

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
        ActionBar mActionBar=getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("Filter");

        Bundle bundle=getArguments();
        options= (HashMap<String, List<String>>) bundle.getSerializable("options");
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view= inflater.inflate(R.layout.fragment_filter, container, false);
        mAutocompleteView = (AutoCompleteTextView) root_view.findViewById(R.id.autocomplete_places);
        setFilters((ViewGroup) root_view);
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
        LatLng north_east=new LatLng(13.465390871221931, 78.00780566406252);
        LatLng south_west=new LatLng(12.749336958395071, 77.17558496093739);
        LatLngBounds bangaloreBounds=new LatLngBounds(south_west,north_east);

        mAdapter = new PlaceAutocompleteAdapter(getContext(), mGeoDataClient, bangaloreBounds, null);
        mAutocompleteView.setAdapter(mAdapter);
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
            Log.e(SearchFilterFragment.class.getSimpleName(),"Tag in Iteration is "+tagObj);
            for(String key:options.keySet()){
                for(int k=0;k<options.get(key).size();k++){
                    String tmp_tag=key+":"+options.get(key).get(k)+":";
                    Log.e(SearchFilterFragment.class.getSimpleName(),"Temp Tag in Iteration is "+tmp_tag);
                    if (tagObj != null && tagObj.contains(tmp_tag)) {
                        updateTheme((Button) child,true);
                        child.setTag(tmp_tag+"1");
                    }
                }
            }
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
