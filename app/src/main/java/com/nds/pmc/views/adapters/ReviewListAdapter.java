package com.nds.pmc.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nds.pmc.R;
import com.nds.pmc.model.ReviewDetails;
import com.nds.pmc.tos.responses.ReviewObject;

import java.util.List;

/**
 * Created by Namrata on 3/3/2018.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder> {

    private Context mContext;
    private List<ReviewDetails> mReviewDetails;


    public ReviewListAdapter(Context context, List<ReviewDetails> reviews) {
        this.mContext = context;
        this.mReviewDetails = reviews;
    }

    @Override
    public ReviewListAdapter.ReviewListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                View searchResultListView = layoutInflater.inflate(R.layout.review_list_row, parent, false);
        return new ReviewListAdapter.ReviewListViewHolder(searchResultListView);
    }

    @Override
    public void onBindViewHolder(ReviewListViewHolder holder, int position) {
        ReviewDetails review = mReviewDetails.get(position);
        holder.mReviewerName.setText(review.getReviewerName());
        holder.mTimeLine.setText(review.getRelativeTime());
        holder.mReviewText.setText(review.getReviewText());
        holder.mRating.setRating((float) review.getRating());
        Glide.with(mContext).load(review.getReviewerPhotoRawData()).placeholder(R.drawable.loading).error(R.drawable.error).into(holder.mReviewerPhoto);
    }

    @Override
    public int getItemCount() {
        if(mReviewDetails == null)
            return 0;
        return mReviewDetails.size();
    }

    public class ReviewListViewHolder extends RecyclerView.ViewHolder {

        ImageView mReviewerPhoto;
        TextView mReviewerName;
        TextView mTimeLine;
        TextView mReviewText;
        RatingBar mRating;


        public ReviewListViewHolder(View itemView) {
            super(itemView);
            mReviewerPhoto = (ImageView)itemView.findViewById(R.id.reviewerPhoto);
            mReviewerName = (TextView) itemView.findViewById(R.id.reviewerName);
            mTimeLine = (TextView) itemView.findViewById(R.id.reviewerTimeline);
            mReviewText = (TextView) itemView.findViewById(R.id.reviewerText);
            mRating = (RatingBar) itemView.findViewById(R.id.reviewer_rating);
        }
    }
}
