package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lasanimas.simplyvaldo.mylogins.Holder.CategoriesHolder;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;

public class RecyclerViewAdapterCategories extends RecyclerView.Adapter<CategoriesHolder>
{
    private ArrayList<String> categories;
    private Context mContext;

    public RecyclerViewAdapterCategories(Context context, ArrayList<String> categories) {
        this.categories = categories;
        mContext = context;
    }

    @Override
    public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_categories, parent, false);
        return new CategoriesHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(CategoriesHolder holder, int position) {

        holder.textView.setText(categories.get(position));
    }

    @Override
    public int getItemViewType(int position) {

        Log.i("Position", Integer.toString(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        return categories.size();
    }
}
