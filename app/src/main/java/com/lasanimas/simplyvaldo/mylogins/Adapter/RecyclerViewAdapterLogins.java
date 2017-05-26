package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lasanimas.simplyvaldo.mylogins.Holder.CategoriesHolder;
import com.lasanimas.simplyvaldo.mylogins.Holder.loginsHolder;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<String> logins;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> logins) {
        this.logins = logins;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView;

        switch (viewType) {
            case 0:
                inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_logins, parent, false);
                return new loginsHolder(inflatedView);
            case 2:
                inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_categories, parent, false);
                return new CategoriesHolder(inflatedView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0:
                loginsHolder viewHolder0 = (loginsHolder)holder;
                viewHolder0.textView.setText(logins.get(position));
                break;

            case 2:
                CategoriesHolder viewHolder2 = (CategoriesHolder)holder;
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        Log.i("Position", Integer.toString(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        return logins.size();
    }

    private Context getContext() {
        return mContext;
    }
}
