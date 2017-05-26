package com.lasanimas.simplyvaldo.mylogins.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.R;

public class CategoriesHolder extends RecyclerView.ViewHolder
{
    public TextView textView;

    public CategoriesHolder(View itemView)
    {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.numID);
    }
}
