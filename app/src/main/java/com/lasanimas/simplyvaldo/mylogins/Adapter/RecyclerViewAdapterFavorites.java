package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.Model.CategoriesHelper;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterFavorites extends RecyclerView.Adapter<RecyclerViewAdapterFavorites.FavortiessHolder>
{
    private ArrayList<String> logins;
    private List<Boolean> favorites;
    private CategoriesHelper categoriesHelper;
    private Context mContext;

    public RecyclerViewAdapterFavorites(Context context, ArrayList<String> logins, List<Boolean> favorites, CategoriesHelper categoriesHelper) {
        this.favorites = favorites;
        this.logins = logins;
        this.categoriesHelper = categoriesHelper;
        mContext = context;
    }

    @Override
    public FavortiessHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_favorites, parent, false);
        return new FavortiessHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FavortiessHolder holder, int position) {

        if(favorites.get(position))
            holder.favoriteLogins.setText(logins.get(position));
    }

    @Override
    public int getItemViewType(int position) {

        Log.i("PositionLogins", Integer.toString(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        int total = 0;

        for(Boolean each: favorites)
        {
            if(each)
                total++;
        }
        return total;
    }

    public class FavortiessHolder extends RecyclerView.ViewHolder {

        //public View container;
        public TextView favoriteLogins;

        public FavortiessHolder(View itemView) {

            super(itemView);

            favoriteLogins = (TextView)itemView.findViewById(R.id.TextViewFavorites);

        }
    }

}


