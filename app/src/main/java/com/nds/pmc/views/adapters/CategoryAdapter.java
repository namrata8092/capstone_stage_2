package com.nds.pmc.views.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nds.pmc.R;

/**
 * Created by Namrata on 11/5/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mContext;
    private AdapterView.OnItemClickListener mClickListener;
    private String[] categoryName;
    private TypedArray categoryIcon;

    public CategoryAdapter(Context context, AdapterView.OnItemClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
        this.categoryName = context.getResources().getStringArray(R.array.category_name);
        this.categoryIcon = context.getResources().obtainTypedArray(R.array.category_icon);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View categoryView = layoutInflater.inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.categoryName.setText(categoryName[position]);
        holder.categoryIcon.setImageResource(categoryIcon.getResourceId(position, -1));
        holder.categoryIcon.setContentDescription(categoryName[position]+R.string.reader_text_category_icon);
    }

    @Override
    public int getItemCount() {
        if (categoryName == null)
            return 0;
        return categoryName.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView categoryIcon;
        TextView categoryName;
        RelativeLayout container;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryIcon = (ImageView) itemView.findViewById(R.id.categoryIcon);
            categoryName = (TextView) itemView.findViewById(R.id.categoryName);
            container = (RelativeLayout)itemView.findViewById(R.id.container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(null, v, getAdapterPosition(), v.getId());
        }
    }
}
