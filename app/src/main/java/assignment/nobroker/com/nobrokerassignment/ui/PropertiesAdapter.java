package assignment.nobroker.com.nobrokerassignment.ui;

import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import assignment.nobroker.com.nobrokerassignment.R;
import assignment.nobroker.com.nobrokerassignment.models.Data;
import assignment.nobroker.com.nobrokerassignment.repository.NetworkState;
import assignment.nobroker.com.nobrokerassignment.repository.Status;
import assignment.nobroker.com.nobrokerassignment.util.ListItemClickListener;


public class PropertiesAdapter extends PagedListAdapter<Data, RecyclerView.ViewHolder> {

    private static final String TAG = PropertiesAdapter.class.getSimpleName();
    private NetworkState networkState;
    private ListItemClickListener itemClickListener;

    public PropertiesAdapter(ListItemClickListener itemClickListener) {
        super(Data.DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == R.layout.properties_card_view) {
            view = layoutInflater.inflate(R.layout.properties_card_view, parent, false);
            return new UserItemViewHolder(view);
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
        TextView userName, userId;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.propertyTitle);
            userName = itemView.findViewById(R.id.propertySize);
        }

        public void bindTo(Data data) {
            if(data.getTitle()!=null)
                userName.setText(data.getTitle());
            if(data.getPropertySize()!=0)
                userId.setText(String.valueOf(data.getPropertySize()));
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
