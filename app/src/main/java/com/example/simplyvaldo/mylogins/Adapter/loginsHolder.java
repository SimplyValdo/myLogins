package com.example.simplyvaldo.mylogins.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.simplyvaldo.mylogins.R;

public class loginsHolder extends RecyclerView.ViewHolder
{
    public TextView textView;

    public loginsHolder(View itemView) {

        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.numID);
    }
}
