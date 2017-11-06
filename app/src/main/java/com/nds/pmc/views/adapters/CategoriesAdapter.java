package com.nds.pmc.views.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nds.pmc.R;

/**
 * Created by Namrata on 11/5/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private Context mContext;
    private AdapterView.OnItemClickListener mClickListener;
    private String[] categoryName;
    private TypedArray categoryIcon;

    public CategoriesAdapter(Context context, AdapterView.OnItemClickListener clickListener) {
        this.mContext = context;
        this.mClickListener = clickListener;
        this.categoryName = context.getResources().getStringArray(R.array.category_name);
        this.categoryIcon = context.getResources().obtainTypedArray(R.array.category_icon);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View categoryView = layoutInflater.inflate(R.layout.category_card, parent);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.categoryName.setText(categoryName[position]);
        holder.categoryIcon.setImageResource(categoryIcon.getResourceId(position, -1));
    }

    @Override
    public int getItemCount() {
        if (categoryName == null)
            return 0;
        return categoryName.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        ImageView categoryIcon;
        TextView categoryName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryIcon = (ImageView) itemView.findViewById(R.id.categoryIcon);
            categoryName = (TextView) itemView.findViewById(R.id.categoryName);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mClickListener.onItemClick(parent, view, position, id);
        }
    }
}
