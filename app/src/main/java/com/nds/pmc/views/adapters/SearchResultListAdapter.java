package com.nds.pmc.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nds.pmc.R;
import com.nds.pmc.model.Place;

import java.util.List;

/**
 * Created by Namrata on 1/11/2018.
 */

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.SearchResultViewHolder> {

    private Context mContext;
    private List<Place> mPlacesList;
    private AdapterView.OnItemClickListener mClickListener;

    public SearchResultListAdapter(Context context, List<Place> places, AdapterView.OnItemClickListener clickListener){
        this.mContext = context;
        this.mPlacesList = places;
        this.mClickListener = clickListener;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View searchResultListView = layoutInflater.inflate(R.layout.fragment_search_result_row, parent, false);
        return new SearchResultViewHolder(searchResultListView);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        Place place = mPlacesList.get(position);
        holder.mTitle.setText(place.getName());
        holder.mRating.setRating((float) place.getRating());
    }

    @Override
    public int getItemCount() {
        if(mPlacesList == null)
        return 0;
        return mPlacesList.size();
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitle;
        RatingBar mRating;
        LinearLayout mContainer;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mRating = (RatingBar) itemView.findViewById(R.id.result_rating);
            mContainer = (LinearLayout) itemView.findViewById(R.id.row_container);
            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(null, v, getAdapterPosition(), v.getId());
        }
    }
}
