package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewCategoriesToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapterCategories extends RecyclerView.Adapter<RecyclerViewAdapterCategories.CategoriesHolder>
{
    private ArrayList<String> categories;
    private HashMap<String, Object> category;
    private List<String> logins;
    private Context mContext;

    private RecyclerViewCategoriesToFragmentListener myListener;

    public RecyclerViewAdapterCategories(Context context, ArrayList<String> categories, RecyclerViewCategoriesToFragmentListener myListener ) {
        this.categories = categories;
        mContext = context;
        this.myListener = myListener;
    }

    public RecyclerViewAdapterCategories(Context context, HashMap category, RecyclerViewCategoriesToFragmentListener myListener ) {
        this.category = category;
        mContext = context;
        this.myListener = myListener;

        if(category != null)
            logins = new ArrayList(category.keySet());

    }

    @Override
    public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_categories, parent, false);
        return new CategoriesHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(CategoriesHolder holder, int position) {

        if( categories != null)
            holder.textView.setText(categories.get(position));
        else
            holder.textView.setText(logins.get(position));

    }

    @Override
    public int getItemViewType(int position) {

        Log.i("PositionCategories", Integer.toString(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        if(categories != null)
            return categories.size();
        else if(category != null)
            return category.size();
        else
            return 0;
    }

    public class CategoriesHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;

        public CategoriesHolder(View itemView)
        {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.TextViewCategory);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if( categories != null)
                        myListener.sendCategory(categories.get(getAdapterPosition()));
                    else
                        myListener.SendSelectLoginInfo(logins.get(getAdapterPosition()));;
                }
            });
        }
    }
}
