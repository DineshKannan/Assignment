package assignment.nobroker.com.nobrokerassignment.ui;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import assignment.nobroker.com.nobrokerassignment.BR;
import assignment.nobroker.com.nobrokerassignment.R;
import assignment.nobroker.com.nobrokerassignment.models.Data;
import assignment.nobroker.com.nobrokerassignment.repository.NetworkState;
import assignment.nobroker.com.nobrokerassignment.repository.Status;
import assignment.nobroker.com.nobrokerassignment.util.ListItemClickListener;


public class PropertiesAdapter extends PagedListAdapter<Data, RecyclerView.ViewHolder> {

    private static final String TAG = PropertiesAdapter.class.getSimpleName();
    private NetworkState networkState;
    private ListItemClickListener itemClickListener;

    private static String cdn_resource_domain="http://d3snwcirvb4r88.cloudfront.net/images/";

    public PropertiesAdapter(ListItemClickListener itemClickListener) {
        super(Data.DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == R.layout.properties_card_view) {
            ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.properties_card_view, parent, false);
            return new UserItemViewHolder(binding);
        } else if (viewType == R.layout.progress_view_footer) {
            view = layoutInflater.inflate(R.layout.progress_view_footer, parent, false);
            return new NetworkStateItemViewHolder(view, itemClickListener);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.properties_card_view:
                ((UserItemViewHolder) holder).bindTo(getItem(position));
                break;
            case R.layout.progress_view_footer:
                ((NetworkStateItemViewHolder) holder).bindView(networkState);
                break;
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.progress_view_footer;
        } else {
            return R.layout.properties_card_view;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    static class UserItemViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        CardView property_card_view;
        LinearLayout property_detail_top_container;
        TextView propertyTitle,secondaryTitle,rent,furnishing,propertySize,built_up_area;
        ImageView fav_button,call_button,image_container;

        public UserItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bindTo(Data property) {
            binding.setVariable(BR.property,property);
            binding.executePendingBindings();
        }

       /* public UserItemViewHolder(View itemView) {
            super(itemView);
            property_card_view = (CardView)itemView.findViewById(R.id.property_card_view);
            property_detail_top_container = (LinearLayout) itemView.findViewById(R.id.property_detail_top_container);
            propertyTitle = (TextView)itemView.findViewById(R.id.propertyTitle);
            secondaryTitle = (TextView)itemView.findViewById(R.id.secondaryTitle);
            rent = (TextView)itemView.findViewById(R.id.rent);
            furnishing = (TextView)itemView.findViewById(R.id.furnishing);
            propertySize = (TextView)itemView.findViewById(R.id.propertySize);
            built_up_area = (TextView)itemView.findViewById(R.id.built_up_area);
            image_container = (ImageView)itemView.findViewById(R.id.image_container);
            fav_button = (ImageView)itemView.findViewById(R.id.fav_button);
            call_button = (ImageView)itemView.findViewById(R.id.call_button);
        }

        public void bindTo(Data data) {
            if (data.getPropertyTitle() != null) {
                propertyTitle.setVisibility(View.VISIBLE);
                propertyTitle.setText(data.getPropertyTitle());
            } else {
                propertyTitle.setVisibility(View.GONE);
            }


            if (data.getSecondaryTitle() != null) {
                secondaryTitle.setVisibility(View.VISIBLE);
                secondaryTitle.setText(data.getSecondaryTitle());
            } else {
                secondaryTitle.setVisibility(View.GONE);
            }

            if (data.getRent() != 0) {
                rent.setVisibility(View.VISIBLE);
                rent.setText(String.valueOf(data.getRent()));
            } else {
                rent.setVisibility(View.GONE);
            }

            if (data.getFurnishing() != null) {
                furnishing.setVisibility(View.VISIBLE);
                furnishing.setText(data.getFurnishing());
            } else {
                furnishing.setVisibility(View.GONE);
            }

            if (data.getPropertySize() != 0) {
                propertySize.setVisibility(View.VISIBLE);
                propertySize.setText(String.valueOf(data.getPropertySize()));
            } else {
                propertySize.setVisibility(View.GONE);
            }

            if(data.getPhotos().size()>0){
                Picasso.with(image_container.getContext()).load(get_image_url(data.getPhotos().get(0).getImagesMap().getMedium())).fit().centerCrop().into(image_container);
            }
            else{
                Picasso.with(image_container.getContext()).load("https://images.nobroker.in/static/img/nopic_1bhk.jpg").fit().centerCrop().into(image_container);
            }

        }*/

        public static String get_image_url(String image_url){
            if(image_url!=null && image_url.contains("_")){
                String folder_directory=image_url.substring(0,image_url.indexOf("_"));
                String final_url=cdn_resource_domain+folder_directory+"/"+image_url;
                Log.e(PropertiesAdapter.class.getSimpleName(),"Resolved url is "+final_url);
                return final_url;
            }
            else{
                return null;
            }
        }
    }

    static class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private final ProgressBar progressBar;


        public NetworkStateItemViewHolder(View itemView, ListItemClickListener listItemClickListener) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);

        }


        public void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == Status.RUNNING) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == Status.FAILED) {

            } else {

            }
        }
    }
}
